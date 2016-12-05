package org.pvhees.advent.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Triangle {

    private Triangle(){}

    public static Boolean isValid(Integer[] points) {
        return points[0] + points[1] > points[2] &&
                points[0] + points[2] > points[1] &&
                points[1] + points[2] > points[0];
    }

    public static Path filenameToPath(String filenaam) {
        return Paths.get(filenaam);
    }


    private static List<String> fileToList(Path path) throws IOException {
        List<String> result = null;

        try (Stream<String> stream = Files.lines(path)) {
            result = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static long howManyValidByLine(String filename) throws IOException {
        List<String> lines = fileToList(filenameToPath(filename));

        List<Triplet<Integer>> triplets = parseLinesToTriplets(lines);

        return triplets.stream()
                .filter(t -> isValid(new Integer[]{t.getFirst(), t.getSecond(), t.getThird()}))
                .count();
    }

    public static long howManyValidByColumn(String filename) throws IOException {
        List<String> lines = fileToList(filenameToPath(filename));

        List<Triplet<Integer>> triplets = parseColumnsToTriplets(lines);

        return triplets.stream()
                .filter(t -> isValid(new Integer[]{t.getFirst(), t.getSecond(), t.getThird()}))
                .count();
    }

    public static List<Triplet<Integer>> parseLinesToTriplets(List<String> lines) {
        List<Triplet<Integer>> result = new ArrayList<>();

        lines.stream().forEach(
                line -> result.add(buildTripletFromString(line))
        );

        return result;
    }

    public static List<Triplet<Integer>> parseColumnsToTriplets(List<String> lines) {
        List<Triplet<Integer>> result = new ArrayList<>();

        for (int i = 0; i < lines.size(); i += 3) {
            Triplet<Integer> line1Triplet = buildTripletFromString(lines.get(i));
            Triplet<Integer> line2Triplet = buildTripletFromString(lines.get(i + 1));
            Triplet<Integer> line3Triplet = buildTripletFromString(lines.get(i + 2));

            result.add(new Triplet<>(line1Triplet.getFirst(), line2Triplet.getFirst(), line3Triplet.getFirst()));
            result.add(new Triplet<>(line1Triplet.getSecond(), line2Triplet.getSecond(), line3Triplet.getSecond()));
            result.add(new Triplet<>(line1Triplet.getThird(), line2Triplet.getThird(), line3Triplet.getThird()));
        }

        return result;
    }


    public static Triplet<Integer> buildTripletFromString(String s) {
        String[] split = s.trim().split("\\s+");

        return new Triplet<Integer>(
                Integer.parseInt(split[0]),
                Integer.parseInt(split[1]),
                Integer.parseInt(split[2])
        );
    }
}
