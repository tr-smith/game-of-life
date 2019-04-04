package com.trsljs;

public class Cell {
    private final boolean isAlive;

    public static final Cell DEAD_CELL = new Cell(false);
    public static final Cell LIVE_CELL = new Cell(true);

    private Cell(boolean isAlive){
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
