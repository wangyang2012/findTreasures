package model;

import util.MapUtils;

import java.util.HashMap;
import java.util.Map;

public class Adventurer extends ObjectOnMap {

    private enum DirectionEnum {
        N(0),
        E(1),
        S(2),
        W(3);
        private final int direction;
        private static Map<Integer, DirectionEnum> map = new HashMap<Integer, DirectionEnum>();

        static {
            for (DirectionEnum directionEnum : DirectionEnum.values()) {
                map.put(directionEnum.direction, directionEnum);
            }
        }

        DirectionEnum(final int direction) {
            this.direction = direction;
        }

        public Integer getValue() {
            return direction;
        }

        public static DirectionEnum getEnum(int value) {
            return map.get(value);
        }
    }

    private String name;
    private DirectionEnum direction;
    private Integer nbTreasure;
    private String[] actions;

    public Adventurer(String name, Integer x, Integer y, String direction, String actions) {
        super(x, y);
        this.name = name;
        this.direction = DirectionEnum.valueOf(direction);
        this.actions = actions.split("A"); // As each step finishes by a A, we have to get all actions between each A
        this.nbTreasure = 0;
    }

    /**
     * Move object with an action character
     * @param indexOfStep
     * @return true: moved, false: don't moved.
     */
    public Boolean move(Integer indexOfStep) {
        // TODO function to complete
        // TODO: switch enum or if ("A".equals)
        if (indexOfStep == null || indexOfStep < 0 || indexOfStep >= this.actions.length) {
            return false;
        }

        String actionsTodo = this.actions[indexOfStep] + "A"; // when we split actions b "A" in the constructor, the last "A" was missing so we have to add it at the end
        for (char action: actionsTodo.toCharArray()) {
            doAction(action);
        }
        return true;
    }

    private void doAction(char action) {
        switch(action) {
            case 'A':
                // calcul next x and y
                int nextX = x;
                int nextY = y;
                switch(direction) {
                    case N:
                        nextY = y - 1;
                        break;
                    case E:
                        nextX = x + 1;
                        break;
                    case S:
                        nextY = y + 1;
                        break;
                    case W:
                        nextX = x - 1;
                        break;
                }
                // check if case is legal on the map
                if (MapUtils.isLegalMove(nextY, nextX)) {
                    MapUtils.moveObjectWithoutCheck(y, x, nextY, nextX);

                }
                break;
            case 'G':
                int newDirectionValueG = (this.direction.getValue()+3) % 4;
                this.direction = DirectionEnum.getEnum(newDirectionValueG);
                break;
            case 'D':
                int newDirectionValueD = (this.direction.getValue()+1) % 4;
                this.direction = DirectionEnum.getEnum(newDirectionValueD);
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void takeTreasure(Treasure treasure) {
        if (treasure != null && treasure.getAmount() > 1) {
            treasure.take();
            this.nbTreasure++;
        }
    }
}
