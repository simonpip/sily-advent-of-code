package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {
    public static long solveFirst(List<Pair<Long, Long>> pairs) {
        return solve(pairs, 2);
    }

    public static long solveSecond(List<Pair<Long, Long>> pairs) {
        return solve(pairs, Integer.MAX_VALUE);
    }

    private static long solve(List<Pair<Long, Long>> pairs, int maxParts) {
        long sum = 0;
        for (Pair<Long, Long> pair : pairs) {
            for (long i = pair.getA(); i <= pair.getB(); i++) {
                String number = String.valueOf(i);
                boolean invalid = false;
                for (int j = 2; j <= Math.min(number.length(), maxParts); j++) {
                    if (number.length() % j != 0) {
                        continue;
                    }

                    invalid |= allSame(split(number, j));
                }

                if (invalid) {
                    sum += i;
                }
            }
        }

        return sum;
    }

    private static String[] split(String number, int parts) {
        int length = number.length() / parts;

        String[] array = new String[parts];
        for (int i = 0; i < parts; i++) {
            array[i] = number.substring(length * i, length * (i + 1));
        }

        return array;
    }

    private static boolean allSame(String[] parts) {
        String compare = parts[0];
        boolean same = true;
        for (int i = 1; i < parts.length; i++) {
            same &= compare.equals(parts[i]);
        }

        return same;
    }

    public static List<Pair<Long, Long>> convert(List<String> file) {
        String[] pairs = file.get(0).split(",");

        return Arrays.stream(pairs).map(pair -> {
            String[] numbers = pair.split("-");

            Long a = Long.valueOf(numbers[0]);
            Long b = Long.valueOf(numbers[1]);

            return new Pair<>(a, b);
        }).collect(Collectors.toList());
    }
}
