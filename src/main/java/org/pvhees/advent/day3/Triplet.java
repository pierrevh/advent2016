package org.pvhees.advent.day3;

import java.lang.reflect.Array;

public class Triplet<T> {

    private T first;
    private T second;
    private T third;

    public Triplet(T first, T second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public T getThird() {
        return this.third;
    }

}
