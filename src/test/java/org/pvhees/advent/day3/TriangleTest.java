package org.pvhees.advent.day3;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class TriangleTest {

    private static final String INPUT_FILE_DAY3 = "/Users/pierrevanhees/IdeaProjects/advent2016/src/main/resources/day3-input.txt";

    @Test
    public void example() {
        Integer[] triangle = new Integer[] {5, 10 ,25};
        assertThat(Triangle.isValid(triangle), not(equalTo(true)));
    }

    @Test
    public void opgave1() throws IOException {
        System.out.println("Geldige driehoeken: "+ Triangle.howManyValidByLine(INPUT_FILE_DAY3));
    }

    @Test
    public void opgave2() throws IOException {
        System.out.println("Geldige driehoeken per kolom: "+ Triangle.howManyValidByColumn(INPUT_FILE_DAY3));
    }

}
