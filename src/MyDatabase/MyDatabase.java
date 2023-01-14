package MyDatabase;
import com.google.gson.Gson;
import SantaDatabase.AnnualChanges;
import SantaDatabase.Children;
import SantaDatabase.Input;
import SantaDatabase.SantaGiftsList;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class MyDatabase {

    private static MyDatabase instance;
    private int numberOfYears;
    private int santaBudget;
    private List<Children> childrenData;
    private List<SantaGiftsList> giftsData;
    private List<AnnualChanges> annualChanges;
    private MyDatabase(){}
    public void setInput(Input input) {
        this.numberOfYears = input.getNumberOfYears();
        this.santaBudget = input.getSantaBudget();
        this.childrenData = input.getChildrenData();
        this.giftsData = input.getGiftsData();
        this.annualChanges = input.getAnnualChanges();
    }

    public static synchronized MyDatabase getInstance() {
        if (instance == null) {
            instance = new MyDatabase();
        }
        return instance;
    }
    public int getNumberOfYears() {
        return numberOfYears;
    }

    public int getSantaBudget() {
        return santaBudget;
    }

    public List<Children> getChildrenData() {
        return childrenData;
    }

    public List<SantaGiftsList> getGiftsData() {
        return giftsData;
    }

    public List<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }
    public void roundZero(JSONArray arrayResult) {
        Double allAverageSum = 0.0;
        for (Children currentChild : getChildrenData()) {
            allAverageSum += currentChild.getAverageScore();
        }
        Double budgetUnit = getSantaBudget() / allAverageSum;
        for (Children currentChild : getChildrenData()) {
            currentChild.setAssignedBudget(currentChild.getAverageScore() * budgetUnit);
        }

        for (Children currentChild : getChildrenData()) {
            for (String preference : currentChild.getGiftsPreferences()) {
                SantaGiftsList finalGift = null;
                for (SantaGiftsList currentGift : giftsData) {
                    if (currentGift.getCategory().equals(preference) && finalGift == null) {
                        finalGift = currentGift;
                    }   else if (currentGift.getCategory().equals(preference)) {
                            if (currentGift.getPrice() < finalGift.getPrice()) {
                                finalGift = currentGift;
                            }
                    }
                }
                if (finalGift != null) {
                    if (santaBudget - finalGift.getPrice() > 0 && finalGift != null) {
                        currentChild.addGifts(finalGift);
                        santaBudget -= finalGift.getPrice();
                    }
                }
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("message", "sar result: " + 21);
        arrayResult.add(jsonObject);
    }
}
