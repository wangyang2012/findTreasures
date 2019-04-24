package model;

public class Adventurer extends ObjectOnMap {
    private enum DirectionEnum {
        N, E, S, W;
    }

    private enum AcitonEnum {
        A, G, D;
    }

    private String name;
    private DirectionEnum direction;
    private Integer nbTreasure;
    private Integer priority; // order in the inputFile, The smaller the value, the higher the priority
    private String actions;


    public Adventurer(String name, Integer x, Integer y, String direction, String actions) {
        super(x, y);
        this.name = name;
        this.direction = DirectionEnum.valueOf(direction);
        this.actions = actions;
        this.nbTreasure = 0;
    }

    /**
     * Move object with an action character
     * @param action
     * @return true: moved, false: don't moved
     */
    public Boolean move(String action) {
        // TODO function to complete
        // TODO: switch enum or if ("A".equals)
        return true;
    }

    // get priority
}
