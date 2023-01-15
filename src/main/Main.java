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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


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

            String filepath = Constants.OUT_PATH + file.getName();
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

        fileWriter.closeJSON();
    }

    public void doAnnualChanges(MyDatabase database, Writer fileWriter) {
        for (int i = 0; i < database.getNumberOfYears(); ++i) {
            Operations operations = new Operations(database.getChildrenData());
            operations.doAnnualChanges();
            // adding new child if is under 19 years old
            if (!database.getAnnualChanges().get(i).getNewChildren().isEmpty()) {
                for (Children newChild : database.getAnnualChanges().get(i)
                        .getNewChildren()) {
                    if (newChild.getAge() > 18) {
                        continue;
                    }
                    database.getChildrenData().add(newChild);
                }
            }
            // updating children data
            if (!database.getAnnualChanges().get(i).getChildrenUpdates().isEmpty()) {
                for (ChildrenUpdate childUpdate : database.getAnnualChanges().get(i)
                        .getChildrenUpdates()) {
                    Children currentChild = database.getChildrenData().get(childUpdate.getId());
                    if (database.getChildrenData().contains(childUpdate.getId())) {
                        if (childUpdate.getNiceScore() != null) {
                            currentChild.addScoreToList(childUpdate.getNiceScore());
                        }
                    }
                    if (!childUpdate.getNewPreferences().isEmpty()) {
                        ArrayList<String> newElements = childUpdate.getNewPreferences();
                        for (String element : newElements) {
                            if (currentChild.getGiftsPreferences().contains(element)) {
                                currentChild.getGiftsPreferences().remove(element);
                            }
                        }
                        currentChild.getGiftsPreferences().addAll(0, newElements);
                    }
                }
            }
        }
    }
}
