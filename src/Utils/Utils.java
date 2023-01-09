package Utils;

import org.json.simple.JSONArray;

import java.util.ArrayList;

public final class Utils {

    private Utils() {}

    public static ArrayList<String> convertJSONArray(final JSONArray array) {
        if (array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }
}
