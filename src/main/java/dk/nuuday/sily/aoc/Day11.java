package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.Grid;

import java.util.List;
import java.util.function.Function;

class Day11 {
    static int countOccupiedSeats(Seating seating) {
        return countOccupiedSeats(seating, Day11::doGameOfLifeStep);
    }

    static int countOccupiedSeatsLongRange(Seating seating) {
        return countOccupiedSeats(seating, Day11::doLongRangeGameOfLifeStep);
    }

    static int countOccupiedSeats(Seating seating, Function<Seating, Seating> stepFunction) {
        Seating previous = null;
        Seating current = seating;
        while (!current.equalTo(previous)) {
            previous = current;
            current = stepFunction.apply(current);
        }

        int count = 0;
        for (int x = 0; x < current.getWidth(); x++) {
            for (int y = 0; y < current.getHeight(); y++) {
                count += countSeat(current, x, y);
            }
        }
        return count;
    }

    private static Seating doGameOfLifeStep(Seating seating) {
        Seating nextStep = new Seating(seating);

        for (int x = 0; x < seating.getWidth(); x++) {
            for (int y = 0; y < seating.getHeight(); y++) {
                SeatStatus current = seating.get(x, y);
                if (current == SeatStatus.FLOOR) {
                    continue;
                }
                int countAdjacent = countAdjacent(seating, x, y);
                if (current == SeatStatus.EMPTY && countAdjacent == 0) {
                    nextStep.set(x, y, SeatStatus.OCCUPIED);
                } else if (current == SeatStatus.OCCUPIED && countAdjacent >= 4) {
                    nextStep.set(x, y, SeatStatus.EMPTY);
                }
            }
        }
        return nextStep;
    }

    private static int countAdjacent(Seating seating, int x, int y) {
        return countSeat(seating, x - 1, y)
                + countSeat(seating, x, y - 1)
                + countSeat(seating, x + 1, y)
                + countSeat(seating, x, y + 1)
                + countSeat(seating, x - 1, y - 1)
                + countSeat(seating, x + 1, y + 1)
                + countSeat(seating, x - 1, y + 1)
                + countSeat(seating, x + 1, y - 1);
    }

    private static int countSeat(Seating seating, int x, int y) {
        if (x < 0 || x >= seating.getWidth() || y < 0 || y >= seating.getHeight()) {
            return 0;
        }
        return seating.get(x, y).people;
    }

    private static Seating doLongRangeGameOfLifeStep(Seating seating) {
        Seating nextStep = new Seating(seating);

        for (int x = 0; x < seating.getWidth(); x++) {
            for (int y = 0; y < seating.getHeight(); y++) {
                SeatStatus current = seating.get(x, y);
                if (current == SeatStatus.FLOOR) {
                    continue;
                }
                int countAdjacent = countVisible(seating, x, y);
                if (current == SeatStatus.EMPTY && countAdjacent == 0) {
                    nextStep.set(x, y, SeatStatus.OCCUPIED);
                } else if (current == SeatStatus.OCCUPIED && countAdjacent > 4) {
                    nextStep.set(x, y, SeatStatus.EMPTY);
                }
            }
        }
        return nextStep;
    }

    private static int countVisible(Seating seating, int x, int y) {
        return countSeat(seating, x, y, -1, 0)
                + countSeat(seating, x, y, 0, -1)
                + countSeat(seating, x, y, 1, 0)
                + countSeat(seating, x, y, 0, 1)
                + countSeat(seating, x, y,  -1, -1)
                + countSeat(seating, x, y,  1, 1)
                + countSeat(seating, x, y,  -1, 1)
                + countSeat(seating, x, y,  1, -1);
    }

    private static int countSeat(Seating seating, int x, int y, int dX, int dY) {
        int newX = x + dX;
        int newY = y + dY;

        if (newX < 0 || newX >= seating.getWidth() || newY < 0 || newY >= seating.getHeight()) {
            return 0;
        }
        SeatStatus seatStatus = seating.get(newX, newY);
        if (seatStatus == SeatStatus.FLOOR) {
            return countSeat(seating, newX, newY, dX, dY);
        }
        return seatStatus.people;
    }

    static final class Seating extends Grid<SeatStatus> {
        public Seating(List<String> strings) {
            super(strings, SeatStatus::fromChar);
        }

        private Seating(Seating original) {
            super(original);
        }

        public boolean equalTo(Seating other) {
            if (other == null || getHeight() != other.getHeight() || getWidth() != other.getWidth()) {
                return false;
            }

            for (int y = 0; y < getHeight(); y++) {
                for (int x = 0; x < getWidth(); x++) {
                    if (get(x, y) != other.get(x, y)) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return stringValue(e -> String.valueOf(e.letter));
        }
    }

    private enum SeatStatus {
        FLOOR(0, '.'),
        EMPTY(0, 'L'),
        OCCUPIED(1, '#');

        private final int people;
        private final char letter;

        SeatStatus(int people, char letter) {
            this.people = people;
            this.letter = letter;
        }

        private static SeatStatus fromChar(char letter) {
            switch (letter) {
                case '.':
                    return FLOOR;
                case 'L':
                    return EMPTY;
                case '#':
                    return OCCUPIED;
            }
            return FLOOR;
        }
    }
}
