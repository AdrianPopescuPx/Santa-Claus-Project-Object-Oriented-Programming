package main;

import MyDatabase.MyDatabase;
import MyDatabase.Operations;
import SantaDatabase.Input;
import SantaDatabase.InputLoader;
import SantaDatabase.Writer;
import checker.Checker;
import childrenCategory.*;
import common.Constants;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        JSONArray arrayResult = new JSONArray();
        MyDatabase database = MyDatabase.getInstance(input);
        Operations operations = new Operations(database.getChildrenData());
        operations.doAverageOperation();
        database.removeDatabase();
        fileWriter.closeJSON(arrayResult);
    }
}
