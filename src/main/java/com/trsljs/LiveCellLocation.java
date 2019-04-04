package com.trsljs;

import java.util.Objects;

public class LiveCellLocation {
    private final int row;
    private final int column;
    private final Cell cell = Cell.LIVE_CELL;

    public LiveCellLocation(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Cell getCell() {
        return cell;
    }
}
