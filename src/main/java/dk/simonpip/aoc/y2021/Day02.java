package dk.simonpip.aoc.y2021;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

class Day02 {
    private Day02() {}

    static long findDistanceTravelled(List<String> movements) {
        long[] position = move(movements);

        return position[0] * position[1];
    }

    static long findDistanceTravelledByAim(List<String> movements) {
        long[] position = move(movements);

        return position[0] * position[2];
    }

    static long[] move(List<String> movements) {
        List<Movement> parsedMovements = movements.stream().map(Movement::new).collect(Collectors.toList());

        long[] position = new long[] { 0, 0, 0 };
        for (Movement movement : parsedMovements) {
            movement.move(position);
        }

        return position;
    }

    private static class Movement {
        private final Direction direction;
        private final long distance;

        Movement(String movement) {
            String[] elements = movement.split(" ");
            this.direction = Direction.fromName(elements[0]);
            this.distance = Integer.parseInt(elements[1]);
        }

        void move(long[] position) {
            direction.move(position, distance);
        }
    }

    private enum Direction {
        FORWARD((position, distance) -> {
            move(position, distance, 0, 1);
            moveByAim(position, distance);
        }),
        DOWN((position, distance) -> move(position, distance, 1, 1)),
        UP((position, distance) -> move(position, distance, 1, -1));

        private final BiConsumer<long[], Long> move;

        Direction(BiConsumer<long[], Long> move) {
            this.move = move;
        }

        static Direction fromName(String name) {
            return Enum.valueOf(Direction.class, name.toUpperCase());
        }

        void move(long[] position, long distance) {
            move.accept(position, distance);
        }

        private static void move(long[] position, long distance, int index, long multiplier) {
            position[index] += distance * multiplier;
        }

        private static void moveByAim(long[] position, long distance) {
            position[2] += distance * position[1];
        }
    }
}
