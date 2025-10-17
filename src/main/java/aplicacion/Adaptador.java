package aplicacion;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Adaptador {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Integer> adaptar(String jsonContent) throws IOException {
        return objectMapper.readValue(
                jsonContent,
                new TypeReference<Map<String, Integer>>() {}
        );
    }

    // Método sobrecargado que acepta Path directamente
    public Map<String, Integer> adaptar(Path filePath) throws IOException {
        return objectMapper.readValue(
                filePath.toFile(),
                new TypeReference<Map<String, Integer>>() {}
        );
    }
}
