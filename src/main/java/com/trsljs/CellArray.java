package com.trsljs;

import java.util.Arrays;

public class CellArray {
    private Cell[][] cells;

    public CellArray(int rows, int columns) {
        cells = new Cell[rows][columns];
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++) {
                cells[row][column] = Cell.DEAD_CELL;
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCellAt(int row, int column, Cell cell){
        this.cells[row][column] = cell;
    }

    public Cell getCellAt(int row, int column){
        return this.cells[row][column];
    }

    public int getRowCount(){
        return this.cells.length;
    }

    public int getColumnCount(){
        return this.cells[0].length;
    }

    public Cell getNextGenerationCell(int row, int column) {
        int count = 0;

        for  (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            if (row + rowOffset < 0 || row + rowOffset >= cells.length){
                continue;
            }
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++){
                if ((column + columnOffset < 0 || column + columnOffset >= cells[0].length) ||
                        rowOffset == 0 && columnOffset == 0){
                    continue;
                }

                if (cells[row + rowOffset][column + columnOffset].isAlive()){
                    count++;
                }
            }
        }
        boolean isAlive = cells[row][column].isAlive() ? (count == 2 || count == 3) : count == 3;
        if (isAlive){
            return Cell.LIVE_CELL;
        } else {
            return Cell.DEAD_CELL;
        }
    }

    public String toDisplayString() {
        StringBuilder displayString = new StringBuilder();

        for (int row = 0; row < cells.length; row++){
            for (int column = 0; column < cells[0].length; column++) {
                displayString.append(cells[row][column].isAlive() ? "0" : ".");
            }
            displayString.append("\n");
        }

        return displayString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellArray cellArray = (CellArray) o;
        return Arrays.deepEquals(cells, cellArray.cells);
    }
}
