package dk.nuuday.sily.aoc.y2022;

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
                knotCoords[0] = knotCoords[0].transform(operation.direction);

                for (int j = 1; j < knotCoords.length; j++) {
                    knotCoords[j] = knotCoords[j].follow(knotCoords[j - 1]);
                }

                tailCoordinates.add(knotCoords[knotCoords.length - 1]);
            }
        }

        return tailCoordinates.size();
    }

    private static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Coordinate transform(Direction direction) {
            return new Coordinate(x + direction.dx, y + direction.dy);
        }

        Coordinate follow(Coordinate head) {
            int dx = Math.abs(head.x - x);
            int dy = Math.abs(head.y - y);
            if (dx <= 1 && dy <= 1) {
                return this;
            }

            int tailX = x, tailY = y;
            // Move diagonally by reducing the difference in Harvard distance to two unit vectors
            if (head.x != x) {
                tailX += (head.x - x) / dx;
            }
            if (head.y != y) {
                tailY += (head.y - y) / dy;
            }
            return new Coordinate(tailX, tailY);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
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
