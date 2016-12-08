package org.pvhees.advent.day4;

import org.pvhees.advent.InputReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Rooms {
    private List<Room> rooms;

    public Rooms(String inputFile) throws IOException {
        List<String> input = InputReader.filenameToList(inputFile);
        rooms = buildRooms(input);
    }

    public Rooms(List<String> input) {
        rooms = buildRooms(input);
    }

    private List<Room> buildRooms(List<String> input) {
        return input.stream()
                .map(Room::new)
                .collect(Collectors.toList());
    }

    public int sumId() {
        return rooms.stream()
                .filter(Room::isReal)
                .mapToInt(r -> r.getSectorId())
                .sum();
    }

}
