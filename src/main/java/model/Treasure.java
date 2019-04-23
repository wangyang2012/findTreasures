package model;

public class Treasure extends ObjectOnMap {

    private Integer amount;
    protected Treasure(Integer x, Integer y, Integer amount) {
        super(x, y);
        this.amount = amount;
    }

    public void take() {
        this.amount --;
    }
}
