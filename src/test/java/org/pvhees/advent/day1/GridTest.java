package org.pvhees.advent.day1;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class GridTest {

    private static final String OPGAVE = "L4, R2, R4, L5, L3, L1, R4, R5, R1, R3, L3, L2, L2, R5, R1, L1, L2, R2, R2, L5, R5, R5, L2, R1, R2, L2, L4, L1, R5, R2, R1, R1, L2, L3, R2, L5, L186, L5, L3, R3, L5, R4, R2, L5, R1, R4, L1, L3, R3, R1, L1, R4, R2, L1, L4, R5, L1, R50, L4, R3, R78, R4, R2, L4, R3, L4, R4, L1, R5, L4, R1, L2, R3, L2, R5, R5, L4, L1, L2, R185, L5, R2, R1, L3, R4, L5, R2, R4, L3, R4, L2, L5, R1, R2, L2, L1, L2, R2, L2, R1, L5, L3, L4, L3, L4, L2, L5, L5, R2, L3, L4, R4, R4, R5, L4, L2, R4, L5, R3, R1, L1, R3, L2, R2, R1, R5, L4, R5, L3, R2, R3, R1, R4, L4, R1, R3, L5, L1, L3, R2, R1, R4, L4, R3, L3, R3, R2, L3, L3, R4, L2, R4, L3, L4, R5, R1, L1, R5, R3, R1, R3, R4, L1, R4, R3, R1, L5, L5, L4, R4, R3, L2, R1, R5, L3, R4, R5, L4, L5, R2";

    @Test
    public void opgave() {
        // vraag 1:
        Grid grid = new Grid(Heading.NORTH, OPGAVE);
        Point result = grid.navigate();

        System.out.println(String.format("(%d, %d) distance=%d",
                result.getX(),
                result.getY(),
                result.distanceFromOrigin()));

        // vraag 2
        if (grid.getFirstRevisitedPoint().isPresent()) {
            Point p = grid.getFirstRevisitedPoint().get();
            System.out.println(String.format("first revisted point at (%d, %d) distance=%d",
                    p.getX(),
                    p.getY(),
                    p.distanceFromOrigin()));
        }
    }

    @Test
    public void eenKeerLinksRoute() {
        Grid grid = new Grid(Heading.NORTH, "L4");
        Point result = grid.navigate();
        assertThat(result, is(new Point(-4, 0)));
        assertFalse(grid.getFirstRevisitedPoint().isPresent());
    }

    @Test
    public void eenKeerRechtsRoute() {
        Grid grid = new Grid(Heading.NORTH, "R4");
        Point result = grid.navigate();
        assertThat(result, is(new Point(4, 0)));
    }

    @Test
    public void tweeStapjes() {
        Grid grid = new Grid(Heading.NORTH, "L4, R2");
        Point result = grid.navigate();
        assertThat(result, is(new Point(-4, 2)));
    }

    @Test
    public void kleineRoute() {
        Grid grid = new Grid(Heading.NORTH, "L4, R2, R4");
        Point result = grid.navigate();
        assertThat(result, is(new Point(0, 2)));
    }

    // Testcase vraag 2: if your instructions are R8, R4, R4, R8, the first location you visit twice is 4 blocks away, due East.
    // Dus: het gaat dus om alle punten waar je overheen bent gelopen, niet alleen eindpunten
    @Test
    public void rondjeRechtsom() {
        Grid grid = new Grid(Heading.NORTH, "R2, R2, R2, R2");
        Point result = grid.navigate();
        assertThat(result, is(new Point(0, 0)));
        assertThat(grid.getFirstRevisitedPoint().get(), is(new Point(0, 0)));
    }

    @Test
    public void rondjeLinksom() {
        Grid grid = new Grid(Heading.NORTH, "L2, L2, L2, L2");
        Point result = grid.navigate();
        assertThat(result, is(new Point(0, 0)));
        assertThat(grid.getFirstRevisitedPoint().get(), is(new Point(0, 0)));
    }

}
