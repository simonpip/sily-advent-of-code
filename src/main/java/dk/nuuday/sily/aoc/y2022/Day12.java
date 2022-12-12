package dk.nuuday.sily.aoc.y2022;

import dk.nuuday.sily.aoc.util.Coordinate;
import dk.nuuday.sily.aoc.util.Pair;

import java.util.*;

public class Day12 {
    static int findNumberOfSteps(char[][] heightArray) {
        return findNumberOfSteps(heightArray, findStart(heightArray));
    }

    static int findMinimalNumberOfSteps(char[][] heightArray) {
        List<Coordinate> startingPoints = new ArrayList<>();
        for (int x = 0; x < heightArray.length; x++) {
            char[] line = heightArray[x];
            for (int y = 0; y < line.length; y++) {
                if ('S' == line[y] || 'a' == line[y]) {
                    startingPoints.add(new Coordinate(x, y));
                }
            }
        }

        return startingPoints.stream()
                .mapToInt(e -> findNumberOfSteps(heightArray, e))
                .filter(e -> e != -1)
                .min()
                .orElseThrow(() -> new RuntimeException("No starting points found"));
    }

    private static int findNumberOfSteps(char[][] heightArray, Coordinate start) {
        Queue<Pair<Integer, Coordinate>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(0, start));
        Set<Coordinate> visitedCoords = new HashSet<>();
        visitedCoords.add(start);

        while (!queue.isEmpty()) {
            Pair<Integer, Coordinate> current = queue.poll();
            int steps = current.getA();
            Coordinate currentCoords = current.getB();
            int x = currentCoords.getX(), y = currentCoords.getY();
            int candidateHeight = findHeight(heightArray[x][y]);

            if (x > 0 && considerCandidate(
                    x - 1, y, heightArray, candidateHeight, steps, queue, visitedCoords)) {
                return steps + 1;
            }
            if (x < heightArray.length - 1 && considerCandidate(
                    x + 1, y, heightArray, candidateHeight, steps, queue, visitedCoords)) {
                return steps + 1;
            }
            if (y > 0 && considerCandidate(
                    x, y - 1, heightArray, candidateHeight, steps, queue, visitedCoords)) {
                return steps + 1;
            }
            if (y < heightArray[x].length - 1 && considerCandidate(
                    x, y + 1, heightArray, candidateHeight, steps, queue, visitedCoords)) {
                return steps + 1;
            }
        }

        return -1;
    }

    private static Coordinate findStart(char[][] heightArray) {
        for (int x = 0; x < heightArray.length; x++) {
            char[] chars = heightArray[x];
            for (int y = 0; y < chars.length; y++) {
                if ('S' == chars[y]) {
                    return new Coordinate(x, y);
                }
            }
        }
        throw new RuntimeException("No start found in height array");
    }

    private static int findHeight(char candidate) {
        switch (candidate) {
            case 'S':
                return 'a';
            case 'E':
                return 'z';
            default:
                return candidate;
        }
    }

    private static boolean considerCandidate(int x, int y,
                                             char[][] heightArray,
                                             int candidateHeight,
                                             int steps,
                                             Queue<Pair<Integer, Coordinate>> queue,
                                             Set<Coordinate> visitedCoords) {
        Coordinate candidate = new Coordinate(x, y);
        char nextCandidate = heightArray[x][y];
        if (!visitedCoords.contains(candidate) && candidateHeight >= findHeight(nextCandidate) - 1) {
            if ('E' == nextCandidate) {
                return true;
            }
            queue.add(new Pair<>(steps + 1, candidate));
            visitedCoords.add(candidate);
        }
        return false;
    }
}
