package SantaDatabase;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {

    private final JSONArray jsonArray = new JSONArray();
    private final JSONObject jsonObject = new JSONObject();
    private FileWriter file;
    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    public void clear() {
        jsonArray.clear();
        jsonObject.clear();
    }
    public void writeFile(List<Children> childrenCategoryList) throws IOException {
        JSONObject object = new JSONObject();
        object.put("children", childrenCategoryList);
        ObjectMapper mapper = new ObjectMapper();
        String jsonSource = mapper.writeValueAsString(object);
        JSONObject jsonObjectClone = mapper.readValue(jsonSource, JSONObject.class);
        jsonArray.add(jsonObjectClone);
    }

    public void closeJSON() throws IOException {
        jsonObject.put("annualChildren", jsonArray);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, false);
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        file.write(jsonString);
        file.flush();
        file.close();
    }
}
