package model;

public abstract class ObjectOnMap {
    protected Integer x;
    protected Integer y;

    protected ObjectOnMap(Integer x, Integer y) {
        
        this.x = x;
        this.y = y;
    }
    protected ObjectOnMap() {
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
