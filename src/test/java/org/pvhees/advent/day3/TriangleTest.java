package org.pvhees.advent.day3;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class TriangleTest {

    @Test
    public void example() {
        Integer[] triangle = new Integer[] {5, 10 ,25};
        assertThat(Triangle.isValid(triangle), not(equalTo(true)));
    }

    @Test
    public void opgave1() {
        System.out.println("Geldige driehoeken: "+ Triangle.howManyValid("/Users/pierrevanhees/IdeaProjects/advent2016/src/main/resources/day3-input.txt"));
    }

    @Test
    public void opgave2() {
        System.out.println("Geldige driehoeken per kolom: "+ Triangle.howManyValidByColumn("/Users/pierrevanhees/IdeaProjects/advent2016/src/main/resources/day3-input.txt"));
    }
}
