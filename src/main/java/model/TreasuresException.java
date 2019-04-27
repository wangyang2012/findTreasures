package model;

public class TreasuresException extends Exception {

    public TreasuresException(String message) {
        super(message);
    }

    public TreasuresException(String message, Exception e) {
        super(message, e);
    }
}
