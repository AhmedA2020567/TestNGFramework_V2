package O3_utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    static String projectPath = System.getProperty("user.dir");
    static String FILE_PATH = projectPath + "/src/test/resources/testData/user.json";

    public String getData(int index) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // ✅ You must pass a File, not a String path
        JsonNode myNode = mapper.readTree(new File(FILE_PATH));

        // ✅ Get first user's firstname
        String name = myNode.path("users").get(index).path("firstname").asText();
        String name2 = myNode.at("/users/"+index+"/firstname").asText();
        return name;
    }

}
