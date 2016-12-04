package org.pvhees.advent.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Triangle {

    public static Boolean isValid(Integer[] points) {
        return points[0] + points[1] > points[2] &&
                points[0] + points[2] > points[1] &&
                points[1] + points[2] > points[0];
    }

    public static long howManyValid(String filename) {
        long result = 0;
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            result = stream
                    .filter(line -> checkValidLine(line))
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Boolean checkValidLine(String line) {
        String[] points = line.trim().split("\\s+");
        return isValid(new Integer[]{
                Integer.parseInt(points[0]),
                Integer.parseInt(points[1]),
                Integer.parseInt(points[2])});
    }

    public static long howManyValidByColumn(String filename) {
        long result = 0;

        List<String> lines = null;
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read line in groups of three, constructing three triangles
        for (int i = 0; i < lines.size(); i += 3) {

            String line1 = lines.get(i);
            String line2 = lines.get(i + 1);
            String line3 = lines.get(i + 2);

            String[] points1 = line1.trim().split("\\s+");
            String[] points2 = line2.trim().split("\\s+");
            String[] points3 = line3.trim().split("\\s+");

            result += isValid(new Integer[]{
                    Integer.parseInt(points1[0]),
                    Integer.parseInt(points2[0]),
                    Integer.parseInt(points3[0])
            }) ? 1 : 0;
            result += isValid(new Integer[]{
                    Integer.parseInt(points1[1]),
                    Integer.parseInt(points2[1]),
                    Integer.parseInt(points3[1])
            }) ? 1 : 0;
            result += isValid(new Integer[]{
                    Integer.parseInt(points1[2]),
                    Integer.parseInt(points2[2]),
                    Integer.parseInt(points3[2])
            }) ? 1 : 0;
        }


        return result;
    }


}
