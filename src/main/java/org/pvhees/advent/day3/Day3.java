package org.pvhees.advent.day3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.pvhees.advent.InputReader.fileToList;
import static org.pvhees.advent.InputReader.filenameToPath;

public class Day3 {

    private static long howManyValid(String filename, TriangleParser parser) throws IOException {
        List<String> lines = fileToList(filenameToPath(filename));
        return parser.parse(lines).stream()
                .filter(t -> t.isValid())
                .count();
    }

    public static long howManyValidByLine(String filename) throws IOException {
        TriangleParser byLineParser = lines -> {
            List<Triangle> result = new ArrayList<>();
            lines.stream().forEach(
                    line -> result.add(buildTriangleFromString(line))
            );
            return result;
        };

        return howManyValid(filename, byLineParser);
    }

    public static long howManyValidByColumn(String filename) throws IOException {
        TriangleParser byColumnParser = lines -> {
            List<Triangle> result = new ArrayList<>();

            for (int i = 0; i < lines.size(); i += 3) {
                Triplet<Integer> line1Triplet = buildTripletFromString(lines.get(i));
                Triplet<Integer> line2Triplet = buildTripletFromString(lines.get(i + 1));
                Triplet<Integer> line3Triplet = buildTripletFromString(lines.get(i + 2));

                result.add(new Triangle(line1Triplet.getFirst(), line2Triplet.getFirst(), line3Triplet.getFirst()));
                result.add(new Triangle(line1Triplet.getSecond(), line2Triplet.getSecond(), line3Triplet.getSecond()));
                result.add(new Triangle(line1Triplet.getThird(), line2Triplet.getThird(), line3Triplet.getThird()));
            }

            return result;
        };

        return howManyValid(filename, byColumnParser);
    }

    private static Triangle buildTriangleFromString(String s) {
        Triplet<Integer> triplet = buildTripletFromString(s);
        return new Triangle(triplet.getFirst(), triplet.getSecond(), triplet.getThird());
    }

    private static Triplet<Integer> buildTripletFromString(String s) {
        String[] split = s.trim().split("\\s+");
        return new Triplet<Integer>(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }

}
