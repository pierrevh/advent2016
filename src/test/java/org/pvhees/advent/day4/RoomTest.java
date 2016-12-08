package org.pvhees.advent.day4;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoomTest {
    private static final String INPUT_FILE_DAY4 = "/Users/pierrevanhees/IdeaProjects/advent2016/src/main/resources/day4-input.txt";

    private static final List<String> EXAMPLE1_DATA = new ArrayList<String>() {{
       add("aaaaa-bbb-z-y-x-123[abxyz]");
       add("a-b-c-d-e-f-g-h-987[abcde]");
       add("not-a-real-room-404[oarel]");
       add("totally-real-room-200[decoy]");
    }};

    @Test
    public void example1() throws IOException {
        Rooms rooms = new Rooms(EXAMPLE1_DATA);
        assertThat(rooms.sumId(), is(1514));
    }

    @Test
    public void opgave1() throws IOException {
        Rooms rooms = new Rooms(INPUT_FILE_DAY4);
        System.out.println(rooms.sumId());
    }
}
