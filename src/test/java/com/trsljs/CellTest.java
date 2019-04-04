package com.trsljs;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {
    @Test
    public void deadCellIsDead(){
        Cell cell = Cell.DEAD_CELL;
        assertFalse(cell.isAlive());
    }

    @Test
    public void liveCellIsAlive(){
        Cell cell = Cell.LIVE_CELL;
        assertTrue(cell.isAlive());
    }

}
