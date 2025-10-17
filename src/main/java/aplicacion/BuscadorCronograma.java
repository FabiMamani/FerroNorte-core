package aplicacion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BuscadorCronograma {
    private final Path directory;
    private final String targetFile;

    public BuscadorCronograma(String directoryPath, String targetFile) {
        this.directory = Paths.get(directoryPath);
        this.targetFile = targetFile;
    }

    public String buscar() throws IOException {
        Path filePath = directory.resolve(targetFile);

        if (!Files.exists(filePath)) {
            throw new IOException("Archivo no encontrado: " + filePath);
        }

        return Files.readString(filePath);
    }

    // Método alternativo que devuelve el Path para mayor flexibilidad
    public Path obtenerRutaCompleta() {
        return directory.resolve(targetFile);
    }
}