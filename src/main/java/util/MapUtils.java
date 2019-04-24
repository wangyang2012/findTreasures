package util;

import model.*;

public abstract class MapUtils {

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

    public static ObjectOnMap[][] createMap(Integer x, Integer y) throws TreasuresException {
        if (x == null || y == null || x <= 0 || y <= 0) {
            throw new TreasuresException("Erreur lors de la création de la carte");
        }
        if (map == null) {
            map = new ObjectOnMap[x][y];
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

        if (x == null || y == null || x <= 0 || y <= 0 || x >= map.length || y >= map[x].length || map[x][y] != null) {
            throw new TreasuresException("Erreur lors de la création de la montagne");
        }

        Mountain mountain = new Mountain(x, y);
        map[x][y] = mountain;
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

        if (x == null || y == null || x <= 0 || y <= 0 || amount <= 0 || x >= map.length || y >= map[x].length || map[x][y] != null) {
            throw new TreasuresException("Erreur lors de la création de la montagne");
        }

        Treasure treasure = new Treasure(x, y, amount);
        map[x][y] = treasure;
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

        if (x == null || y == null || x <= 0 || y <= 0 || x >= map.length || y >= map[x].length || map[x][y] != null || StringUtils.isBlank(name) || StringUtils.isBlank(direction) || direction.length() != 1 || StringUtils.isBlank(actions)) {
            throw new TreasuresException("Erreur lors de la création de l'aventurier");
        }

        Adventurer adventurer = new Adventurer(name, x, y, direction, actions);
        map[x][y] = adventurer;
    }

    public static void printMap() {
        // TODO: to continue...
    }
}

