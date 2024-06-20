import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.TreeMap;

public class Serializer {

    public String serialize(TreeMap<Integer, Task> map) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        return jsonResult;
    }

    public TreeMap<Integer, Task> deserialize(String json) throws IOException {
        StringReader reader = new StringReader(json);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<TreeMap<Integer, Task>> typeRef = new TypeReference<>() {};
        return mapper.readValue(reader, typeRef);
    }
}