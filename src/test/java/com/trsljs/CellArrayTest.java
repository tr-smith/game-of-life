package com.trsljs;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CellArrayTest {
    private Cell[][] expectedDeadCells;
    private CellArray testCellArray;


    @Before
    public void initialize() {
        expectedDeadCells = new Cell[3][3];
        expectedDeadCells[0][0] = Cell.DEAD_CELL;
        expectedDeadCells[0][1] = Cell.DEAD_CELL;
        expectedDeadCells[0][2] = Cell.DEAD_CELL;
        expectedDeadCells[1][0] = Cell.DEAD_CELL;
        expectedDeadCells[1][1] = Cell.DEAD_CELL;
        expectedDeadCells[1][2] = Cell.DEAD_CELL;
        expectedDeadCells[2][0] = Cell.DEAD_CELL;
        expectedDeadCells[2][1] = Cell.DEAD_CELL;
        expectedDeadCells[2][2] = Cell.DEAD_CELL;

        testCellArray = new CellArray(3, 3);
    }
    @Test
    public void constructorInitializesDeadCellArray() {
        assertThat(expectedDeadCells, is(testCellArray.getCells()));
    }

    @Test
    public void deadCellSetCellAt() {
        testCellArray.setCellAt(1, 1, Cell.LIVE_CELL);
        assertThat(Cell.LIVE_CELL, is(testCellArray.getCellAt(1, 1)));
        testCellArray.setCellAt(1, 1, Cell.DEAD_CELL);
        assertThat(Cell.DEAD_CELL, is(testCellArray.getCellAt(1, 1)));
    }

    @Test
    public void liveCellSetCellAt() {
        testCellArray.setCellAt(1, 1, Cell.LIVE_CELL);
        assertThat(Cell.LIVE_CELL, is(testCellArray.getCellAt(1, 1)));
    }

    @Test
    public void rowCountIsRowCount(){
        assertThat(3, is(testCellArray.getRowCount()));
    }

    @Test
    public void columnCountIsColumnCount(){
        assertThat(3, is(testCellArray.getColumnCount()));
    }

    @Test
    public void liveCellWithOneNeighborWillDieInNextGeneration() {
        testCellArray.setCellAt(0, 2, Cell.LIVE_CELL);
        testCellArray.setCellAt(1, 1, Cell.LIVE_CELL);
        Cell nextGenerationCell = testCellArray.getNextGenerationCell(0, 2);
        assertThat(Cell.DEAD_CELL, is(nextGenerationCell));
    }


    @Test
    public void liveCellWithThreeNeighborsWillLiveInNextGeneration() {
        testCellArray.setCellAt(0, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(1, 1, Cell.LIVE_CELL);
        testCellArray.setCellAt(2, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(2, 2, Cell.LIVE_CELL);
        Cell nextGenerationCell = testCellArray.getNextGenerationCell(1, 1);
        assertThat(Cell.LIVE_CELL, is(nextGenerationCell));
    }

    @Test
    public void liveCellWithFourNeighborsWillDieInNextGeneration() {
        testCellArray.setCellAt(0, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(0, 2, Cell.LIVE_CELL);
        testCellArray.setCellAt(1, 1, Cell.LIVE_CELL);
        testCellArray.setCellAt(2, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(2, 2, Cell.LIVE_CELL);
        Cell nextGenerationCell = testCellArray.getNextGenerationCell(1, 1);
        assertThat(Cell.DEAD_CELL, is(nextGenerationCell));
    }

    @Test
    public void liveCellWithTwoNeighborsWillLiveInNextGeneration() {
        testCellArray.setCellAt(0, 1, Cell.LIVE_CELL);
        testCellArray.setCellAt(0, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(1, 1, Cell.LIVE_CELL);
        Cell nextGenerationCell = testCellArray.getNextGenerationCell(0, 0);
        assertThat(Cell.LIVE_CELL, is(nextGenerationCell));
    }

    @Test
    public void deadCellWithLessThanThreeLiveNeighborsStaysDead(){
        testCellArray.setCellAt(1, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(2, 1, Cell.LIVE_CELL);
        Cell nextGenerationCell = testCellArray.getNextGenerationCell(2, 0);
        assertThat(Cell.DEAD_CELL, is(nextGenerationCell));

    }

    @Test
    public void deadCellWithMoreThanThreeLiveNeighborsStaysDead(){
        testCellArray.setCellAt(0, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(1, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(2, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(2, 2, Cell.LIVE_CELL);
        Cell nextGenerationCell = testCellArray.getNextGenerationCell(1, 1);
        assertThat(Cell.DEAD_CELL, is(nextGenerationCell));

    }

    @Test
    public void deadCellWithExactlyThreeLiveNeighborsLives(){
        testCellArray.setCellAt(0, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(1, 1, Cell.DEAD_CELL);
        testCellArray.setCellAt(2, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(2, 2, Cell.LIVE_CELL);
        Cell nextGenerationCell = testCellArray.getNextGenerationCell(1, 1);
        assertThat(Cell.LIVE_CELL, is(nextGenerationCell));

    }

    @Test
    public void toDisplayStringTest() {
        String expectedToDisplayString = ".0.\n0.0\n..0\n";
        testCellArray.setCellAt(0, 1, Cell.LIVE_CELL);
        testCellArray.setCellAt(1, 0, Cell.LIVE_CELL);
        testCellArray.setCellAt(1, 2, Cell.LIVE_CELL);
        testCellArray.setCellAt(2, 2, Cell.LIVE_CELL);
        assertThat(expectedToDisplayString, is(testCellArray.toDisplayString()));
    }

}
