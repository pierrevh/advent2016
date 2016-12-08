package org.pvhees.advent.day4;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Room {

    private int sectorId;
    private String checksum;
    private String data;

    public Room(String roomData) {
        int startOfChecksum = roomData.indexOf("[");
        int startOfSectorId = roomData.lastIndexOf("-");
        this.data = roomData.substring(0, startOfSectorId).replaceAll("-", "");
        System.out.println(this.data);
        this.sectorId = Integer.parseInt(roomData.substring(startOfSectorId+1, startOfChecksum));
        this.checksum = roomData.substring(startOfChecksum+1, roomData.length()-1);
    }

    public boolean isReal() {

        Stream<String> streamData = Stream.of(this.data);
        Map<String, Long> letterToCount = streamData
                        .map(s -> s.split(""))
                        .flatMap(Arrays::stream)
                        .collect(groupingBy(identity(), counting()));

        //TODO: use lettercount to determine if checksum is valid
        return false;
    }

    public int getSectorId() {
        return this.sectorId;
    }
}
