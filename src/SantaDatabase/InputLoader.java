package SantaDatabase;

import Utils.Utils;
import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class InputLoader {

    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }
    public String getInputPath() {
        return inputPath;
    }

    /**
     * The method reads the database
     * @return an Input object
     */

    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        int years = 0;
        int budget = 0;
        List<Children> children = new ArrayList<>();
        List<SantaGiftsList> gifts = new ArrayList<>();
        List<AnnualChanges> changes = new ArrayList<>();

        try {
            // Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            JSONObject initialData = (JSONObject) jsonObject
                    .get(Constants.INITIAL_DATA);
            JSONArray jsonChildren = (JSONArray)
                    initialData.get(Constants.CHILDREN);
            JSONArray jsonGiftList = (JSONArray)
                    initialData.get(Constants.GIFT_LIST);

            if (jsonChildren != null) {
                for (Object jsonChild : jsonChildren) {
                    children.add(new Children(Integer.parseInt(((JSONObject) jsonChild).get(Constants.ID).toString()),
                            (String) ((JSONObject) jsonChild).get(Constants.LAST_NAME),
                            (String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME),
                            Integer.parseInt(((JSONObject) jsonChild).get(Constants.AGE).toString()),
                            (String) ((JSONObject) jsonChild).get(Constants.CITY),
                            Integer.parseInt(((JSONObject) jsonChild).get(Constants.NICE_SCORE).toString()),
                            Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild).get(Constants.GIFT_PREF))));
                }
            } else {
                System.out.println("NU EXISTA COPII");
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
