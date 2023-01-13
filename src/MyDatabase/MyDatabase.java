package MyDatabase;

import SantaDatabase.AnnualChanges;
import SantaDatabase.Children;
import SantaDatabase.Input;
import SantaDatabase.SantaGiftsList;

import java.util.List;

public class MyDatabase {

    private static MyDatabase instance;
    private int numberOfYears;
    private int santaBudget;
    private List<Children> childrenData;
    private List<SantaGiftsList> giftsData;
    private List<AnnualChanges> annualChanges;
    private MyDatabase(Input input) {
        this.numberOfYears = input.getNumberOfYears();
        this.santaBudget = input.getSantaBudget();
        this.childrenData = input.getChildrenData();
        this.giftsData = input.getGiftsData();
        this.annualChanges = input.getAnnualChanges();
    }

    public static MyDatabase getInstance(Input input) {
        if (instance == null) {
            instance = new MyDatabase(input);
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
    public void removeDatabase() {
        instance = null;
    }
}
