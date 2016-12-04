package org.pvhees.advent.day1;

public class Instruction {
    private Heading heading;
    private int distance;

    public Instruction(Heading heading, int distance) {
        this.heading = heading;
        this.distance = distance;
    }

    public Heading getHeading() {
        return heading;
    }

    public int getDistance() {
        return  distance;
    }
}
