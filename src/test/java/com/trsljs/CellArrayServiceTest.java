package com.trsljs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CellArrayServiceTest {

    private CellArray expectedNewState;
    private CellArray initialState;
    private List<LiveCellLocation> liveCellLocations;

    @Before
    public void iniialize() {

        expectedNewState = new CellArray(6, 8);
        expectedNewState.setCellAt(0, 1, Cell.LIVE_CELL);
        expectedNewState.setCellAt(1, 1, Cell.LIVE_CELL);
        expectedNewState.setCellAt(1, 5, Cell.LIVE_CELL);
        expectedNewState.setCellAt(1, 6, Cell.LIVE_CELL);
        expectedNewState.setCellAt(1, 7, Cell.LIVE_CELL);
        expectedNewState.setCellAt(2, 1, Cell.LIVE_CELL);
        expectedNewState.setCellAt(4, 3, Cell.LIVE_CELL);
        expectedNewState.setCellAt(4, 4, Cell.LIVE_CELL);
        expectedNewState.setCellAt(5, 3, Cell.LIVE_CELL);
        expectedNewState.setCellAt(5, 4, Cell.LIVE_CELL);

        initialState = new CellArray(6, 8);
        initialState.setCellAt(0, 6, Cell.LIVE_CELL);
        initialState.setCellAt(1, 0, Cell.LIVE_CELL);
        initialState.setCellAt(1, 1, Cell.LIVE_CELL);
        initialState.setCellAt(1, 2, Cell.LIVE_CELL);
        initialState.setCellAt(1, 6, Cell.LIVE_CELL);
        initialState.setCellAt(2, 6, Cell.LIVE_CELL);
        initialState.setCellAt(4, 3, Cell.LIVE_CELL);
        initialState.setCellAt(4, 4, Cell.LIVE_CELL);
        initialState.setCellAt(5, 3, Cell.LIVE_CELL);
        initialState.setCellAt(5, 4, Cell.LIVE_CELL);

        liveCellLocations = new ArrayList<>();
        liveCellLocations.add(new LiveCellLocation(0,6));
        liveCellLocations.add(new LiveCellLocation(1,0));
        liveCellLocations.add(new LiveCellLocation(1,1));
        liveCellLocations.add(new LiveCellLocation(1,2));
        liveCellLocations.add(new LiveCellLocation(1,6));
        liveCellLocations.add(new LiveCellLocation(2,6));
        liveCellLocations.add(new LiveCellLocation(4,3));
        liveCellLocations.add(new LiveCellLocation(4,4));
        liveCellLocations.add(new LiveCellLocation(5,3));
        liveCellLocations.add(new LiveCellLocation(5,4));

    }

    @Test
    public void getNextGenerationTest() {
        CellArrayService cellArrayService = new CellArrayService();
        CellArray newState = cellArrayService.getNextGeneration(initialState);
        assertTrue(newState.equals(expectedNewState) && expectedNewState.equals(newState));

    }

    @Test
    public void getCellArrayWithGridSizeAndLiveCellsTest() {
        CellArrayService cellArrayService = new CellArrayService();
        CellArray testCellArray = cellArrayService.getCellArrayWithGridSizeAndLiveCells(6, 8, liveCellLocations);
        assertTrue(testCellArray.equals(initialState) && initialState.equals(testCellArray));
    }


}
