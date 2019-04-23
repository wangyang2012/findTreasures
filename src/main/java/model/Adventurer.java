package model;

public class Adventurer extends ObjectOnMap {
    private DirectionEnum direction;
    private Integer nbTreasure;

    protected Adventurer(Integer x, Integer y, Character direction) {
        super(x, y);
        this.direction = DirectionEnum.valueOf(direction.toString());
        this.nbTreasure = 0;
    }

    /**
     * @param action
     * @return
     */
    public Boolean move(String action) {
        // TODO function to complete
    }
}
