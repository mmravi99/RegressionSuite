package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode readJsonAsTree(String filePath) throws IOException {
        return objectMapper.readTree(new File(filePath));
    }

    public static JsonNode getTestCaseByTcid(JsonNode rootNode, String tcid) {
        Iterator<JsonNode> elements = rootNode.path("core-product").elements();
        while (elements.hasNext()) {
            JsonNode testCase = elements.next();
            if (testCase.path("tcid").asText().equals(tcid)) {
                return testCase;
            }
        }
        return null;
    }

    public static void printAllDescriptions(JsonNode rootNode) {
        Iterator<JsonNode> elements = rootNode.path("core-product").elements();
        while (elements.hasNext()) {
            JsonNode testCase = elements.next();
            System.out.println(testCase.path("description").asText());
        }
    }

    public static String getTestDataForTcid(JsonNode rootNode, String tcid) {
        JsonNode testCase = getTestCaseByTcid(rootNode, tcid);
        if (testCase != null) {
            return testCase.path("testData").toString();
        }
        return null;
    }

    public static Map<String, Object> getTestDataForTcidAsMap(JsonNode rootNode, String tcid) throws IOException {
        JsonNode testCase = getTestCaseByTcid(rootNode, tcid);
        if (testCase != null) {
            return objectMapper.convertValue(testCase.path("testData"), Map.class);
        }
        return null;
    }


}
