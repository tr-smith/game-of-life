package com.trsljs;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LiveCellLocationTest {
    LiveCellLocation location;
    @Before
    public void initialize() {
        location = new LiveCellLocation(2, 4);
    }

    @Test
    public void liveCellLocationRowIsRow() {
        assertThat(2, CoreMatchers.is(location.getRow()));
    }

    @Test
    public void liveCellLocationColumnIsColumn() {
        assertThat(4, CoreMatchers.is(location.getColumn()));
    }

    @Test
    public void cellIsAlive() {
        assertEquals(location.getCell(), Cell.LIVE_CELL);
    }

}
