package org.pvhees.advent.day4;

import java.util.*;
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
        this.sectorId = Integer.parseInt(roomData.substring(startOfSectorId + 1, startOfChecksum));
        this.checksum = roomData.substring(startOfChecksum + 1, roomData.length() - 1);
    }

    public boolean isReal() {

        Stream<String> streamData = Stream.of(this.data);
        Map<String, Long> letterToCount = streamData
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .collect(groupingBy(identity(), counting()));

        //TODO: works but is ugly
        List<LetterCount> letterCountList = new ArrayList<>();
        for (Map.Entry<String, Long> entry : letterToCount.entrySet()) {
            letterCountList.add(new LetterCount(entry.getKey().charAt(0), entry.getValue()));
        }

        Collections.sort(letterCountList, new Comparator<LetterCount>(){
            @Override
            public int compare(LetterCount d1, LetterCount d2){
                int countCmp = d1.count - d2.count < 0 ? 1 : (d1.count - d2.count > 0 ? -1 : 0);
                if (countCmp != 0) {
                    return countCmp;
                }
                return d1.letter.compareTo(d2.letter);
            }
        });

        StringBuilder calculatedChecksum = new StringBuilder();
        for (int i=0; i< 5; i++) {
            calculatedChecksum.append(letterCountList.get(i).getLetter());
        }

        return calculatedChecksum.toString().equalsIgnoreCase(this.checksum);
    }

    public int getSectorId() {
        return this.sectorId;
    }

    class LetterCount {
        private Character letter;
        private long count;

        public LetterCount(Character letter, long count) {
            this.letter = letter;
            this.count = count;
        }

        public Character getLetter() {
            return letter;
        }

        public long getCount() {
            return count;
        }
    }
}
