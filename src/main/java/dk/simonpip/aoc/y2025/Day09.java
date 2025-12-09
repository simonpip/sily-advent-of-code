package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.Coordinate;
import dk.simonpip.aoc.util.Pair;
import dk.simonpip.aoc.util.Vector;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day09 {
    public static long solveFirst(List<Coordinate> coordinates) {
        return findAllPossibleAreas(coordinates).get(0).getA();
    }

    public static long solveSecond(List<Coordinate> coordinates) {
        List<Vector> connections = connections(coordinates);
        List<Vector> verticalConnections = connections.stream()
                .filter(pair -> pair.getA().getY() != pair.getB().getY())
                .collect(Collectors.toList());
        List<Vector> horizontalConnections = connections.stream()
                .filter(pair -> pair.getA().getY() == pair.getB().getY())
                .collect(Collectors.toList());

        List<Pair<Long, Vector>> areas = findAllPossibleAreas(coordinates);
        for (Pair<Long, Vector> area : areas) {
            if (inside(area.getB().getA(), area.getB().getB(), verticalConnections, horizontalConnections)) {
                return area.getA();
            }
        }
        return 0L;
    }

    private static List<Pair<Long, Vector>> findAllPossibleAreas(List<Coordinate> coordinates) {
        List<Pair<Long, Vector>> areas = new ArrayList<>();
        for (int i = 0; i < coordinates.size() - 1; i++) {
            Coordinate lhs = coordinates.get(i);
            for (int j = i + 1; j < coordinates.size(); j++) {
                Coordinate rhs = coordinates.get(j);
                areas.add(new Pair<>(getArea(lhs, rhs), new Vector(lhs, rhs)));
            }
        }
        areas.sort(Comparator.comparing((Function<Pair<Long, ?>, Long>) Pair::getA).reversed());

        return areas;
    }

    private static long getArea(Coordinate lhs, Coordinate rhs) {
        return (long) (Math.abs(lhs.getX() - rhs.getX()) + 1) * (long) (Math.abs(lhs.getY() - rhs.getY()) + 1);
    }

    private static List<Vector> connections(List<Coordinate> coordinates) {
        List<Vector> vectors = new ArrayList<>(coordinates.size());
        for (int i = 0; i < coordinates.size() - 1; i++) {
            vectors.add(makeConnection(coordinates.get(i), coordinates.get(i + 1)));
        }
        vectors.add(makeConnection(coordinates.get(0), coordinates.get(coordinates.size() - 1)));

        vectors.sort(Comparator
                .comparing(Vector::getX1)
                .thenComparing(Vector::getY1));

        return vectors;
    }

    private static Vector makeConnection(Coordinate lhs, Coordinate rhs) {
        if (lhs.getX() < rhs.getX()
                || lhs.getX() == rhs.getX() && lhs.getY() < rhs.getY()) {
            return new Vector(lhs, rhs);
        }
        return new Vector(rhs, lhs);
    }

    private static boolean inside(Coordinate lhs, Coordinate rhs,
                                  List<Vector> verticalConnections,
                                  List<Vector> horizontalConnections) {
        // LHS and RHS might be any set of opposing points in any order
        int minX = Math.min(lhs.getX(), rhs.getX());
        int maxX = Math.max(lhs.getX(), rhs.getX());
        int minY = Math.min(lhs.getY(), rhs.getY());
        int maxY = Math.max(lhs.getY(), rhs.getY());

        return verticalConnections.stream()
                .filter(pair -> pair.getX1() > minX && pair.getX1() < maxX)
                .noneMatch(pair -> pair.getY1() <= minY && pair.getY2() > minY
                        || pair.getY1() < maxY && pair.getY2() >= maxY)
                && horizontalConnections.stream()
                .filter(pair -> pair.getY1() > minY && pair.getY1() < maxY)
                .noneMatch(pair -> pair.getX1() <= minX && pair.getX2() > minX
                        || pair.getX1() < maxX && pair.getX2() >= maxX);
    }
}
