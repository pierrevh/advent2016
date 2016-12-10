package org.pvhees.advent.day4;

import java.util.*;

import static java.util.Collections.reverseOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Room {

    private int sectorId;
    private String checksum;
    private String data;

    public Room(String roomData) {
        int startOfChecksum = roomData.indexOf("[");
        int startOfSectorId = roomData.lastIndexOf("-");
        this.data = roomData.substring(0, startOfSectorId);
        this.sectorId = Integer.parseInt(roomData.substring(startOfSectorId + 1, startOfChecksum));
        this.checksum = roomData.substring(startOfChecksum + 1, roomData.length() - 1);
    }

    public boolean isReal() {
        Map<String, Long> letterCount = Arrays.stream(
                this.data.replaceAll("-", "").toLowerCase().split(""))
                .collect(groupingBy(identity(), counting()));

        List<String> top5Characters = letterCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(toList());

        String calculatedChecksum = top5Characters.stream().reduce("", String::concat);

        return calculatedChecksum.equalsIgnoreCase(this.checksum);
    }

    public int getSectorId() {
        return this.sectorId;
    }

}
