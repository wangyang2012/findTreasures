package model;

public class Adventurer extends ObjectOnMap {
    private enum DirectionEnum {
        N, E, S, W;
    }

    private enum AcitonEnum {
        A, G, D;
    }

    private DirectionEnum direction;
    private Integer nbTreasure;

    protected Adventurer(Integer x, Integer y, Character direction) {
        super(x, y);
        this.direction = DirectionEnum.valueOf(direction.toString());
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
}
