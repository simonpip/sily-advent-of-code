package dk.simonpip.aoc.y2025;

import java.util.List;

public class Day01 {
    public static int solveFirst(List<Integer> rotations) {

        int current = 50;
        int password = 0;
        for (Integer rotation : rotations) {
            // Left rotations are negative
            current += rotation;
            current %= 100;
            if (current == 0) {
                password++;
            }
            if (current < 0) {
                // current is negative, so add to subtract
                current = 100 + current;
            }
        }

        return password;
    }

    public static int solveSecond(List<Integer> rotations) {
        int current = 50;
        int password = 0;
        for (Integer rotation : rotations) {
            // Left rotations are negative
            int next = current + rotation;
            password += Math.abs(next / 100);
            if (next == 0) {
                password++;
            }
            if (next * current < 0) {
                // Only one is negative, thus it's moved past 0
                password++;
            }
            current = next % 100;

            if (current < 0) {
                // current is negative, so add to subtract
                current = 100 + current;
            }
        }

        return password;
    }

    public static int convert(String line) {
        String direction = line.substring(0, 1);
        int value = Integer.parseInt(line.substring(1));

        return "L".equals(direction)
                ? value * -1
                : value;
    }
}
