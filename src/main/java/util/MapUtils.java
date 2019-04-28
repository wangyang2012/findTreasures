package util;

import model.*;
import model.Process;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class MapUtils {

    public static final Logger logger = Logger.getLogger(MapUtils.class);

    private static ObjectOnMap[][] map;
    private static int nbAdventurer = 0;

    public static ObjectOnMap[][] initMap(String[] fields) throws TreasuresException {
        if (fields == null || fields.length != 3) {
            throw new TreasuresException("Le format de la ligne est erroné.");
        }

        if (map != null) {
            throw new TreasuresException("La carte existe déjà.");
        }

        Integer x = Integer.valueOf(fields[1]);
        Integer y = Integer.valueOf(fields[2]);

        return createMap(x, y);
    }

    public static ObjectOnMap[][] createMap(Integer nbColumns, Integer nbLines) throws TreasuresException {
        if (nbColumns == null || nbLines == null || nbColumns <= 0 || nbLines <= 0) {
            throw new TreasuresException("Erreur lors de la création de la carte");
        }
        if (map == null) {
            map = new ObjectOnMap[nbLines][nbColumns];
        } else {
            throw new TreasuresException("La carte existe déjà, impossible de la recréer");
        }
        return map;
    }

    public static void addMountain(String[] fields) throws TreasuresException {
        if (fields == null || fields.length != 3) {
            throw new TreasuresException("Le format de la ligne est erroné.");
        }

        if (map == null) {
            throw new TreasuresException("Impossible de créer la montagne, car la carte n'existe pas.");
        }

        Integer x = Integer.valueOf(fields[1]);
        Integer y = Integer.valueOf(fields[2]);

        if (x == null || y == null || x < 0 || y < 0 || y >= map.length || x >= map[y].length || map[y][x] != null) {
            throw new TreasuresException("Erreur lors de la création de la montagne");
        }

        Mountain mountain = new Mountain(x, y);
        map[y][x] = mountain;
    }

    public static void addTreasure(String[] fields) throws TreasuresException {
        if (fields == null || fields.length != 4) {
            throw new TreasuresException("Le format de la ligne est erroné.");
        }

        if (map == null) {
            throw new TreasuresException("Impossible de créer la montagne, car la carte n'existe pas.");
        }

        Integer x = Integer.valueOf(fields[1]);
        Integer y = Integer.valueOf(fields[2]);
        Integer amount = Integer.valueOf(fields[3]);

        if (x == null || y == null || x < 0 || y < 0 || amount <= 0 || y >= map.length || x >= map[y].length || map[y][x] != null) {
            throw new TreasuresException("Erreur lors de la création du trésor");
        }

        Treasure treasure = new Treasure(x, y, amount);
        map[y][x] = treasure;
    }

    public static void createAdventurer(String[] fields) throws TreasuresException {
        if (fields == null || fields.length != 6) {
            throw new TreasuresException("Le format de la ligne est erroné.");
        }

        if (map == null) {
            throw new TreasuresException("Impossible de créer l'aventurier, car la carte n'existe pas.");
        }

        String name = fields[1];
        Integer x = Integer.valueOf(fields[2]);
        Integer y = Integer.valueOf(fields[3]);
        String direction = fields[4];
        String actions = fields[5];

        if (x == null || y == null || x < 0 || y < 0 || y >= map.length || x >= map[y].length || map[y][x] != null || StringUtils.isBlank(name) || StringUtils.isBlank(direction) || direction.length() != 1 || StringUtils.isBlank(actions)) {
            throw new TreasuresException("Erreur lors de la création de l'aventurier");
        }

        Adventurer adventurer = new Adventurer(name, x, y, direction, actions);
        map[y][x] = adventurer;
    }

    public static void printMap() throws TreasuresException {
        String mapStr = mapToString();
        writeToFile(mapStr);
    }

    public static String mapToString() throws TreasuresException {
        StringBuilder sb = new StringBuilder();
        if (map != null) {
            for (int i=0; i<map.length; i++) {
                for (int j=0; j<map[i].length; j++) {
                    boolean isLargeCase = false;
                    if (map[i][j] == null) {
                        sb.append("*");
                    } else {
                        ObjectOnMap object = map[i][j];
                        if (object instanceof Mountain) {
                            sb.append("M");
                        } else if (object instanceof Treasure) {
                            sb.append("T(" + ((Treasure) object).getAmount() + ")");
                            isLargeCase = true;
                        } else if (object instanceof Adventurer) {
                            sb.append("A");
                        }
                    }
                    // write espaces to align columns
                    if (j < map[i].length-1) {
                        // if is large case, write only one \t, else write two \t
                        sb.append("\t");
                        if (!isLargeCase) {
                            sb.append("\t");
                        }
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private static void writeToFile(String value) throws TreasuresException {
        if (StringUtils.isBlank(value)) {
            throw new TreasuresException("Aucune donnée à écrire dans le fichier");
        }
        logger.debug(value);
        Path path = Paths.get("map.txt");
        try {
            Files.write(path, value.getBytes());
        } catch (IOException e) {
            throw new TreasuresException("Une erreur est survenue lors de l'écriture de fichier.", e);
        }
    }
}

