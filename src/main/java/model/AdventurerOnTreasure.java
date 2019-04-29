package model;

public class AdventurerOnTreasure extends ObjectOnMap {
    private Adventurer adventurer;
    private Treasure treasure;

    public AdventurerOnTreasure(Adventurer adventurer, Treasure treasure) {
        this.adventurer = adventurer;
        this.treasure = treasure;
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }

    public Treasure getTreasure() {
        return treasure;
    }
}
