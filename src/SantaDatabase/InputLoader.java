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

            years = Integer.parseInt((jsonObject.get(Constants.YEARS)).toString());
            budget = Integer.parseInt((jsonObject.get(Constants.BUDGET)).toString());
            if (jsonChildren != null) {
                for (Object jsonChild : jsonChildren) {
                    children.add(new Children(Integer.parseInt(((JSONObject) jsonChild).get(Constants.ID).toString()),
                            (String) ((JSONObject) jsonChild).get(Constants.LAST_NAME),
                            (String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME),
                            Integer.parseInt(((JSONObject) jsonChild).get(Constants.AGE).toString()),
                            (String) ((JSONObject) jsonChild).get(Constants.CITY),
                            Double.parseDouble(((JSONObject) jsonChild).get(Constants.NICE_SCORE).toString()),
                            Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild).get(Constants.GIFT_PREF))));
                }
            }   else {
                    System.out.println("NU EXISTA COPII");
            }

            if (jsonGiftList != null) {
                for (Object jsonGift : jsonGiftList) {
                    gifts.add(new SantaGiftsList((String) ((JSONObject) jsonGift).get(Constants.PRODUCT_NAME),
                            Double.parseDouble(((JSONObject) jsonGift).get(Constants.PRICE).toString()),
                            (String) ((JSONObject) jsonGift).get(Constants.CATEGORY_GIFT)));
                }
            }   else {
                    System.out.println("NU EXISTA LISTA DE CADOURI");
            }
            changes = readChanges(jsonObject);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return new Input(years, budget, children, gifts, changes);
    }

    public List<AnnualChanges> readChanges(JSONObject jsonObject) {
        List<AnnualChanges> changesResult = new ArrayList<>();
        JSONArray jsonAnnualChanges = (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);
        if (jsonAnnualChanges != null) {
            for (Object jsonIterator : jsonAnnualChanges) {
                List<SantaGiftsList> newGifts = new ArrayList<>();
                Double newSantaBudget = Double.parseDouble(((JSONObject) jsonIterator).get(Constants.NEW_BUDGET)
                        .toString());
                JSONArray jsonNewGifts = (JSONArray) ((JSONObject) jsonIterator).get(Constants.NEW_GIFTS);
                if (jsonNewGifts != null) {
                    for (Object jsonGifts : jsonNewGifts) {
                        newGifts.add(new SantaGiftsList((String) ((JSONObject) jsonGifts).get(Constants.PRODUCT_NAME),
                                Double.parseDouble(((JSONObject) jsonGifts).get(Constants.PRICE).toString()),
                                (String) ((JSONObject) jsonGifts).get(Constants.CATEGORY_GIFT)));
                    }
                }   else {
                    System.out.println("NU EXISTA NOI COPII");
                }

                List<Children> newChildren = new ArrayList<>();
                JSONArray jsonNewChildren = (JSONArray) ((JSONObject) jsonIterator).get(Constants.NEW_CHILDREN);
                if (jsonNewChildren != null) {
                    for (Object jsonChild : jsonNewChildren) {
                        newChildren.add(new Children(Integer.parseInt(((JSONObject) jsonChild).get(Constants.ID).toString()),
                                (String) ((JSONObject) jsonChild).get(Constants.LAST_NAME),
                                (String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME),
                                Integer.parseInt(((JSONObject) jsonChild).get(Constants.AGE).toString()),
                                (String) ((JSONObject) jsonChild).get(Constants.CITY),
                                Double.parseDouble(((JSONObject) jsonChild).get(Constants.NICE_SCORE).toString()),
                                Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild).get(Constants.GIFT_PREF))));
                    }
                }   else {
                    System.out.println("NU EXISTA NOI COPII");
                }
                List<ChildrenUpdate> childrenUpdates = new ArrayList<>();
                JSONArray jsonChildrenUpdates = (JSONArray) ((JSONObject) jsonIterator).get(Constants.CHILDREN_UPDATES);
                if (jsonChildrenUpdates != null) {
                    for (Object jsonChild : jsonChildrenUpdates) {
                        if (((JSONObject) jsonChild).get(Constants.NICE_SCORE) != null) {
                            childrenUpdates.add(new ChildrenUpdate(Integer.parseInt(((JSONObject) jsonChild)
                                    .get(Constants.ID).toString()),
                                    Double.parseDouble(((JSONObject) jsonChild).get(Constants.NICE_SCORE).toString()),
                                    Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild).get(Constants.GIFT_PREF))));
                        }   else {
                            childrenUpdates.add(new ChildrenUpdate(Integer.parseInt(((JSONObject) jsonChild)
                                    .get(Constants.ID).toString()),
                                    null,
                                    Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild).get(Constants.GIFT_PREF))));
                        }
                    }
                }   else {
                    System.out.println("NU EXISTA COPII UPDATATI");
                }

            changesResult.add(new AnnualChanges(newSantaBudget, newGifts, newChildren,childrenUpdates));
            }
        }   else {
            System.out.println("NU EXISTA SCHIMBARI");
            changesResult = null;
        }
        return changesResult;
    }
}
