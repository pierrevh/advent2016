package org.pvhees.advent.day3;

public class Triangle {

    private Triplet<Integer> sides;

    public Triangle(int side1, int side2, int side3) {
        sides = new Triplet<>(side1, side2, side3);
    }

    public Boolean isValid() {
        return this.sides.getFirst() + this.sides.getSecond() > this.sides.getThird() &&
                this.sides.getFirst() + this.sides.getThird() > this.sides.getSecond() &&
                this.sides.getSecond() + this.sides.getThird() > this.sides.getFirst();
    }

}
