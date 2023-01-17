package main;

import MyDatabase.MyDatabase;
import MyDatabase.Operations;
import SantaDatabase.*;
import checker.Checker;
import childrenCategory.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {

        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        File outputDirectory = new File(Constants.RESULT_PATH);
        Checker.deleteFiles(outputDirectory.listFiles());
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String myString = file.getName();
            String numbersOnly = myString.replaceAll("[^0-9]", "");
            String filepath = Constants.OUT_PATH + numbersOnly + ".json";
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }
        Checker.calculateScore();
    }

    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();
        Writer fileWriter = new Writer(filePath2);
        MyDatabase database = MyDatabase.getInstance();
        database.setInput(input);
        Operations operations = new Operations(database.getChildrenData());
        operations.doAverageOperation();
        database.roundZero(fileWriter);
        doAnnualChanges(database, fileWriter);
        fileWriter.closeJSON();
    }

    public static void doAnnualChanges(MyDatabase database, Writer fileWriter) throws IOException {
        for (int i = 0; i < database.getNumberOfYears(); ++i) {
            // I will not update santaBudget, I will work directly with newSantaBudget

            Operations operations = new Operations(database.getChildrenData());
            operations.doYearIncrease();
            // adding new child if is under 19 years old ( newChildren )
            if (!database.getAnnualChanges().get(i).getNewChildren().isEmpty()) {
                for (Children children : database.getAnnualChanges().get(i).getNewChildren()) {
                    database.getChildrenData().add(children);
                }
            }
            // adding new nice score
            if (!database.getAnnualChanges().get(i).getChildrenUpdates().isEmpty()) {
                for (ChildrenUpdate childUpdate : database.getAnnualChanges().get(i)
                        .getChildrenUpdates()) {
                    Children currentChild = null;
                    for (Children children : database.getChildrenData()) {
                        if (children.getId() == childUpdate.getId()) {
                            currentChild = children;
                            break;
                        }
                    }
                        if (childUpdate.getNiceScore() != null && currentChild != null) {
                            currentChild.addScoreToList(childUpdate.getNiceScore());
                        }
                    if (!childUpdate.getNewPreferences().isEmpty() && currentChild != null) {
                        ArrayList<String> newElements = childUpdate.getNewPreferences();
                        for (int j = 0; j < newElements.size() - 1; ++j) {
                            for (int q = j + 1; q < newElements.size(); ++q) {
                                if (newElements.get(j).equals(newElements.get(q))) {
                                    newElements.remove(q);
                                    q--;
                                }
                            }
                        }
                        for (String element : newElements) {
                            if (currentChild.getGiftsPreferences().contains(element)) {
                                currentChild.getGiftsPreferences().remove(element);
                            }
                        }
                        currentChild.getGiftsPreferences().addAll(0, newElements);
                    }
                }
            }

            // adding newGifts
            if (!database.getAnnualChanges().get(i).getNewGifts().isEmpty()) {
                for (SantaGiftsList newGift : database.
                        getAnnualChanges().get(i).getNewGifts()) {
                    if (!database.getGiftsData().contains(newGift)) {
                        database.getGiftsData().add(newGift);
                    }
                }
            }

            Double currentBudget = database.getAnnualChanges().get(i).getNewSantaBudget();
            operations.doAverageOperation();

            Double allAverageSum = 0.0;
            for (Children currentChild : database.getChildrenData()) {
                allAverageSum += currentChild.getAverageScore();
            }
            Double budgetUnit = currentBudget / allAverageSum;
            for (Children currentChild : database.getChildrenData()) {
                currentChild.setAssignedBudget(currentChild.getAverageScore() * budgetUnit);
            }

            for (Children currentChild : database.getChildrenData()) {
                Double assignedBudget = currentChild.getAssignedBudget();
                currentChild.getReceivedGifts().clear();
                for (String preference : currentChild.getGiftsPreferences()) {
//                    boolean toGive = false;
//                    for (SantaGiftsList giftsList : currentChild.getReceivedGifts()) {
//                        if (giftsList.getCategory().equals(preference)) {
//                            toGive = true;
//                            assignedBudget -= giftsList.getPrice();
//                            break;
//                        }
//                    }
//                    if (!hasGift) {
                        SantaGiftsList finalGift = null;
                        for (SantaGiftsList currentGift : database.getGiftsData()) {
                            if (currentGift.getCategory().equals(preference) && finalGift == null) {
                                finalGift = currentGift;
                            }   else if (currentGift.getCategory().equals(preference)) {
                                if (currentGift.getPrice() < finalGift.getPrice()) {
                                    finalGift = currentGift;
                                }
                            }
                        }
                        if (finalGift != null) {
                            if (currentBudget > finalGift.getPrice() &&
                                    assignedBudget > finalGift.getPrice()) {
                                currentChild.addGifts(finalGift);
                                currentBudget -= finalGift.getPrice();
                                assignedBudget -= finalGift.getPrice();
                                //database.getGiftsData().remove(finalGift);
                            }
                        }
//                    }
                }
            }
            fileWriter.writeFile(database.getChildrenData());
        }
    }
}
