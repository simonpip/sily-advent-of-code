package dk.simonpip.aoc.y2020;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

class Day12 {
    private Day12() {
    }

    static int calculateLocation(List<Instruction> instructions) {
        int[] location = new int[2];
        Heading heading = new Heading();
        for (Instruction instruction : instructions) {
            instruction.moveShip(heading, location);
        }
        return Math.abs(location[0]) + Math.abs(location[1]);
    }
    static int calculateWaypoint(List<Instruction> instructions) {
        int[] waypoint = new int[2];
        waypoint[0] = 10;
        waypoint[1] = -1;
        int[] location = new int[2];
        for (Instruction instruction : instructions) {
            instruction.moveWaypoint(waypoint, location);
        }
        return Math.abs(location[0]) + Math.abs(location[1]);
    }

    static final class Instruction {
        private final InstructionType type;
        private final int value;

        Instruction(String direction) {
            this.type = InstructionType.fromLetter(direction.charAt(0));
            this.value = Integer.parseInt(direction.substring(1));
        }

        private void moveShip(Heading heading, int[] location) {
            type.direction.execute(heading, value, location);
        }

        private void moveWaypoint(int[] waypoint, int[] location) {
            type.waypoint.execute(value, waypoint, location);
        }
    }

    private enum InstructionType {
        FORWARD('F',
                (heading, val, loc) -> heading.type.valConsumer.accept(val, loc),
                (val, wp, loc) -> {
                    for (int i = 0; i < val; i++) {
                        loc[0] += wp[0];
                        loc[1] += wp[1];
                    }
                }),
        LEFT('L',
                (heading, val, loc) -> heading.update(360 - val),
                (val, wp, loc) -> {
                    int mod = val / 90;
                    for (int i = 0; i < mod; i++) {
                        int newX = wp[1];
                        int newY = -wp[0];
                        wp[0] = newX;
                        wp[1] = newY;
                    }
                }),
        RIGHT('R',
                (heading, val, loc) -> heading.update(val),
                (val, wp, loc) -> {
                    int mod = val / 90;
                    for (int i = 0; i < mod; i++) {
                        int newX = -wp[1];
                        int newY = wp[0];
                        wp[0] = newX;
                        wp[1] = newY;
                    }
                }),
        NORTH('N',
                (heading, val, loc) -> loc[1] -= val,
                (val, wp, loc) -> wp[1] -= val),
        SOUTH('S',
                (heading, val, loc) -> loc[1] += val,
                (val, wp, loc) -> wp[1] += val),
        EAST('E',
                (heading, val, loc) -> loc[0] += val,
                (val, wp, loc) -> wp[0] += val),
        WEST('W',
                (heading, val, loc) -> loc[0] -= val,
                (val, wp, loc) -> wp[0] -= val);

        private final char letter;
        private final DirectionAction direction;
        private final WaypointAction waypoint;

        InstructionType(char letter, DirectionAction direction, WaypointAction waypoint) {
            this.letter = letter;
            this.direction = direction;
            this.waypoint = waypoint;
        }

        private static InstructionType fromLetter(char value) {
            return Arrays.stream(values()).filter(e -> e.letter == value).findFirst().orElse(null);
        }
    }

    private static final class Heading {
        private HeadingType type;

        private Heading() {
            type = HeadingType.EAST;
        }

        private void update(int headingChange) {
            int newVal = (type.headingValue * 90) + headingChange;

            type = HeadingType.fromInt(newVal);
        }
    }

    private enum HeadingType {
        EAST(0, (val, curr) -> curr[0] += val),
        SOUTH(1, (val, curr) -> curr[1] += val),
        WEST(2, (val, curr) -> curr[0] -= val),
        NORTH(3, (val, curr) -> curr[1] -= val);

        private final int headingValue;
        private final BiConsumer<Integer, int[]> valConsumer;

        HeadingType(int headingValue, BiConsumer<Integer, int[]> valConsumer) {
            this.headingValue = headingValue;
            this.valConsumer = valConsumer;
        }

        private static HeadingType fromInt(int value) {
            int modValue = (value / 90) % 4;
            return Arrays.stream(values()).filter(e -> e.headingValue == modValue).findFirst().orElse(null);
        }
    }

    @FunctionalInterface
    private interface DirectionAction {
        void execute(Heading heading, int value, int[] location);
    }

    @FunctionalInterface
    private interface WaypointAction {
        void execute(int value, int[] waypoint, int[] location);
    }
}
