package org.pvhees.advent.day3;

import java.util.List;

@FunctionalInterface
public interface TriangleParser {

    List<Triangle> parse(List<String> lines);

}
