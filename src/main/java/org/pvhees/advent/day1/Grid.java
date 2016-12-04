package org.pvhees.advent.day1;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.*;

public class Grid {

    private static final Character LEFT = 'L';
    private static final Character RIGHT = 'R';

    private List<Instruction> instructions;
    private Set<Point> visited;
    private Optional<Point> firstVisitedTwice;

    public Grid(Heading headingAtStart, String navigation) {
        // build the instructions
        Map<Heading, Map<Character, Heading>> leftRightToHeadingMapping = buildDirectionToHeadingMapper();
        this.instructions = buildNavigationInstructions(headingAtStart, navigation, leftRightToHeadingMapping);

        this.visited = new HashSet<>();
        this.firstVisitedTwice = Optional.empty();
    }

    private List<Instruction> buildNavigationInstructions(
            Heading currentHeading,
            String navigation,
            Map<Heading, Map<Character, Heading>> leftRightToHeadingMapping)
    {
        List<Instruction> result = new ArrayList<>();

        if (navigation.trim().length() > 0) {
            String[] elements = navigation.split(",");
            for (String element : elements) {
                element = element.trim();
                char leftRight = element.charAt(0);
                int distance = Integer.parseInt(element.substring(1));
                Heading newHeading = leftRightToHeadingMapping.get(currentHeading).get(leftRight);
                result.add(new Instruction(newHeading, distance));
                currentHeading = newHeading;
            }
        }

        return result;
    }

    private Map<Heading, Map<Character, Heading>> buildDirectionToHeadingMapper() {
        Map<Character, Heading> west = new HashMap<>();
        west.put(LEFT, Heading.SOUTH);
        west.put(RIGHT, Heading.NORTH);
        Map<Character, Heading> east = new HashMap<>();
        east.put(LEFT, Heading.NORTH);
        east.put(RIGHT, Heading.SOUTH);
        Map<Character, Heading> south = new HashMap<>();
        south.put(LEFT, Heading.EAST);
        south.put(RIGHT, Heading.WEST);
        Map<Character, Heading> north = new HashMap<>();
        north.put(LEFT, Heading.WEST);
        north.put(RIGHT, Heading.EAST);

        Map<Heading, Map<Character, Heading>> result  = new HashMap<>();
        result.put(Heading.WEST, west);
        result.put(Heading.EAST, east);
        result.put(Heading.SOUTH, south);
        result.put(Heading.NORTH, north);

        return result;
    }

    public Point navigate() {
        // Start at 0,0, this is the first visited point
        Point currentPoint = new Point(0, 0);
        this.visited.clear();
        this.visited.add(currentPoint);

        // follow all instructions
        for (Instruction instruction : this.instructions) {
            int deltaX = 0;
            int deltaY = 0;
            switch (instruction.getHeading()) {
                case EAST:
                    deltaX = instruction.getDistance();
                    break;
                case WEST:
                    deltaX = -instruction.getDistance();
                    break;
                case NORTH:
                    deltaY = instruction.getDistance();
                    break;
                case SOUTH:
                    deltaY = -instruction.getDistance();
                    break;
            }

            Point newCurrentPoint = currentPoint.move(deltaX, deltaY);
            saveVisitedPoints(currentPoint, newCurrentPoint);
            currentPoint = newCurrentPoint;
        }

        return currentPoint;
    }

    private void saveVisitedPoints(Point startPoint, Point endPoint) {
        if (startPoint.getX() == endPoint.getX()) {
            // we're moving in the y-direction, x remains constant
            int x = startPoint.getX();
            // adds all points in between startpoint and endpoint
            for (int y : openRange(startPoint.getY(), endPoint.getY())) {
                Point p = new Point(x, y);
                addAndCheckForVisitedTwice(p);
            }
        } else {
            //we'removing in the x-direction, y remains constant
            int y = startPoint.getY();
            // adds all points in between startpoint and endpoint (excluding)
            for (int x : openRange(startPoint.getX(), endPoint.getX())) {
                Point p = new Point(x, y);
                addAndCheckForVisitedTwice(p);
            }
        }

        // finally, add endpoint too
        addAndCheckForVisitedTwice(endPoint);
    }

    private List<Integer> openRange(int first, int second) {
        // creates open range <lowest, highest>
        int lowest = Math.min(first, second) + 1;  // lowest + 1 makes sure range is open at low end
        int highest = Math.max(first, second) - 1; // highest - 1 makes sure range is open at high end
        return range(lowest, highest).boxed().collect(Collectors.toList());
    }

    private void addAndCheckForVisitedTwice(Point p) {
        if (!this.firstVisitedTwice.isPresent() && this.visited.contains(p)) {
            this.firstVisitedTwice = Optional.of(p);
        }
        this.visited.add(p);
    }

    public Optional<Point> getFirstRevisitedPoint() {
        return firstVisitedTwice;
    }
}
