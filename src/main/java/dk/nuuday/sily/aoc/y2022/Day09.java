package dk.nuuday.sily.aoc.y2022;

import dk.nuuday.sily.aoc.util.Coordinate;

import java.util.*;

public class Day09 {
    static int countTailFields(List<Operation> operations, int knots) {
        Coordinate[] knotCoords = new Coordinate[knots];
        for (int i = 0; i < knotCoords.length; i++) {
            knotCoords[i] = new Coordinate(0, 0);
        }

        Set<Coordinate> tailCoordinates = new HashSet<>();
        tailCoordinates.add(knotCoords[knotCoords.length - 1]);

        for (Operation operation : operations) {
            for (int i = 0; i < operation.times; i++) {
                knotCoords[0] = transform(knotCoords[0], operation.direction);

                for (int j = 1; j < knotCoords.length; j++) {
                    knotCoords[j] = follow(knotCoords[j], knotCoords[j - 1]);
                }

                tailCoordinates.add(knotCoords[knotCoords.length - 1]);
            }
        }

        return tailCoordinates.size();
    }



    private static Coordinate transform(Coordinate coordinate, Direction direction) {
        return new Coordinate(coordinate.getX() + direction.dx, coordinate.getY() + direction.dy);
    }

    private static Coordinate follow(Coordinate tail, Coordinate head) {
        int dx = Math.abs(head.getX() - tail.getX());
        int dy = Math.abs(head.getY() - tail.getY());
        if (dx <= 1 && dy <= 1) {
            return tail;
        }

        int tailX = tail.getX(), tailY = tail.getY();
        // Move diagonally by reducing the difference in Harvard distance to two unit vectors
        if (head.getX() != tail.getX()) {
            tailX += (head.getX() - tail.getX()) / dx;
        }
        if (head.getY() != tail.getY()) {
            tailY += (head.getY() - tail.getY()) / dy;
        }
        return new Coordinate(tailX, tailY);
    }

    private enum Direction {
        UP('U', 0, -1),
        DOWN('D', 0, 1),
        LEFT('L', -1, 0),
        RIGHT('R', 1, 0);

        private final char signifier;
        private final int dx;
        private final int dy;

        Direction(char signifier, int dx, int dy) {
            this.signifier = signifier;
            this.dx = dx;
            this.dy = dy;
        }

        static Direction fromChar(char character) {
            Map<Character, Direction> map = Map.of(
                    UP.signifier, UP,
                    DOWN.signifier, DOWN,
                    LEFT.signifier, LEFT,
                    RIGHT.signifier, RIGHT);

            return map.get(character);
        }
    }

    static class Operation {
        private final Direction direction;
        private final int times;

        public Operation(String operation) {
            direction = Direction.fromChar(operation.charAt(0));
            times = Integer.parseInt(operation.substring(2));
        }
    }
}
