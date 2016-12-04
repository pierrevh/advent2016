package org.pvhees.advent.day1;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point move(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int distanceFromOrigin() {
        return this.x + this.y;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Point) {
            Point that = (Point) other;
            return this.x == that.x && this.y == that.y;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.x * 17 + this.y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", this.x, this.y);
    }

}
