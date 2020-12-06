package dk.nuuday.sily.aoc;

import java.util.List;
import java.util.stream.Collectors;

class Day01 {
    private static final int WANTED_SUM = 2020;

    private Day01() {
    }

    static int fixExpenseReport(List<Integer> entries) {
        int[] numbers = findNumbersSummingTo(entries, WANTED_SUM);

        if (numbers.length == 2) {
            return numbers[0] * numbers[1];
        }
        return 0;
    }

    static int fixExpenseReportPart2(List<Integer> entries) {
        List<Integer> sorted = entries.stream()
                .filter(e -> e < WANTED_SUM)
                .sorted()
                .collect(Collectors.toList());

        for (int i = sorted.size() - 1; i >= 1; i--) {
            int high = sorted.get(i);
            int[] numbers = findNumbersSummingTo(sorted.subList(0, i), WANTED_SUM - high);

            if (numbers.length == 2) {
                return high * numbers[0] * numbers[1];
            }
        }
        return 0;
    }

    private static int[] findNumbersSummingTo(List<Integer> numbers, int wantedSum) {
        List<Integer> sorted = numbers.stream()
                .filter(e -> e < wantedSum)
                .sorted()
                .collect(Collectors.toList());

        for (int i = sorted.size() - 1; i >= 1; i--) {
            int high = sorted.get(i);

            boolean matchingLow = sorted.subList(0, i).stream().anyMatch(e -> e == wantedSum - high);
            if (matchingLow) {
                return new int[] {wantedSum - high, high};
            }
        }
        return new int[0];
    }
}
