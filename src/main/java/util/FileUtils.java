package util;

import model.TreasuresException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class FileUtils {
    public static List<String> readFile(String fileName) throws TreasuresException {
        try {
            return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new TreasuresException("Impossible de lire le fichier d'entrée");
        }
    }
}
