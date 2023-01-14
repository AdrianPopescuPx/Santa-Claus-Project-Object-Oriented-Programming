package SantaDatabase;

import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private FileWriter file;
    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    public JSONObject writeFile(final int id, final String field,
                                final String message) throws IOException {
        JSONObject object = new JSONObject();
        object.put("annualChildren", id);
        object.put("children", message);

        return object;
    }

    public void closeJSON(String string) {
        try {
            file.write(string);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
