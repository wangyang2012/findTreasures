package util;

import model.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class MapUtils {

    public static final Logger logger = Logger.getLogger(MapUtils.class);

    private static ObjectOnMap[][] map;
    private static List<Adventurer> adventurersList = new ArrayList<>();
    private static Integer maxNumberOfMoves = 0;    // the max of number of move of each methods.

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
        adventurersList.add(adventurer);

        // calcul number of moves of this adventurer and update maxNumberOfMoves if this value is bigger
        if (StringUtils.isNotBlank(actions)) {
            String[] splitedActions = actions.split("A");
            Integer numberOfMoves = splitedActions.length;
            if (numberOfMoves != null && numberOfMoves > maxNumberOfMoves) {
                maxNumberOfMoves = numberOfMoves;
            }
        }
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
                            sb.append("A(" + ((Adventurer)object).getName() + ")");
                            isLargeCase = true;
                        } else if (object instanceof AdventurerOnTreasure) {
                            AdventurerOnTreasure aot = (AdventurerOnTreasure)object;
                            sb.append("AT(" + aot.getAdventurer().getName() + " " + aot.getTreasure().getAmount() + ")");
                            isLargeCase = true;
                        }
                    }
                    // write espaces to align columns
                    if (j < map[i].length-1) {
                        // if is large case, write only one \t, else write two \t
                        sb.append("\t\t");
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

    public static void moveAdventurers() {
        for (int i=0; i<maxNumberOfMoves; i++) {
            for (Adventurer adventurer: adventurersList) {
                adventurer.move(i);
            }
        }
    }

    public static boolean isLegalMove(int nextY, int nextX) {
        if (nextY >= map.length || nextX >= map[nextY].length) {
            return false;
        }
        return true;
    }


    public static void moveObjectWithoutCheck(Integer y, Integer x, int nextY, int nextX) {
        ObjectOnMap oldObject = map[y][x];
        ObjectOnMap newObject = map[nextY][nextX];
        ObjectOnMap tempObject;

        // treate old object
        if (oldObject instanceof AdventurerOnTreasure) {
            tempObject = ((AdventurerOnTreasure) oldObject).getAdventurer();
            map[y][x] = ((AdventurerOnTreasure) oldObject).getTreasure();
        } else {
            tempObject = map[y][x];
        }

        // do move
        tempObject.setX(nextX);
        tempObject.setY(nextY);
        if (newObject == null) {
            map[nextY][nextX] = tempObject;
        } else {
            // new case is used by a treasure, take the treasure
            Treasure treasure = (Treasure)map[nextY][nextX];
            ((Adventurer)tempObject).takeTreasure(treasure);
            if (treasure.getAmount() == 0) {
                map[nextY][nextX] = tempObject;
            } else {
                map[nextY][nextX] = new AdventurerOnTreasure((Adventurer) tempObject, treasure);
            }
        }
    }
}

