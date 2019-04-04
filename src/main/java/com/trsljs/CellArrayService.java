package com.trsljs;

import java.util.List;

public class CellArrayService {

    public CellArray getNextGeneration(CellArray currentGenerationCellArray){
        int rowEnd = currentGenerationCellArray.getRowCount();
        int columnEnd = currentGenerationCellArray.getColumnCount();
        CellArray cellArray = new CellArray(rowEnd, columnEnd);
        for (int row = 0; row < rowEnd; row++){
            for (int column = 0; column < columnEnd; column++){
                Cell cell = currentGenerationCellArray.getNextGenerationCell(row, column);
                cellArray.setCellAt(row, column, cell);
            }
        }
        return cellArray;
    }

    public CellArray getCellArrayWithGridSizeAndLiveCells(int rows, int columns,
                                                          List<LiveCellLocation> liveCellLocations){

        CellArray cellArray = new CellArray(rows, columns);
        for (LiveCellLocation location: liveCellLocations){
            cellArray.setCellAt(location.getRow(), location.getColumn(), location.getCell());
        }
        return cellArray;
    }
}
