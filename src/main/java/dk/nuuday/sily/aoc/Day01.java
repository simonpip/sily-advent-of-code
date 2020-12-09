package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.Shared;

import java.util.List;
import java.util.stream.Collectors;

class Day01 {
    private static final int WANTED_SUM = 2020;

    private Day01() {
    }

    static long fixExpenseReport(List<Long> entries) {
        long[] numbers = Shared.findNumbersSummingTo(entries, WANTED_SUM);

        if (numbers.length == 2) {
            return numbers[0] * numbers[1];
        }
        return 0;
    }

    static long fixExpenseReportPart2(List<Long> entries) {
        List<Long> sorted = entries.stream()
                .filter(e -> e < WANTED_SUM)
                .sorted()
                .collect(Collectors.toList());

        for (int i = sorted.size() - 1; i >= 1; i--) {
            long high = sorted.get(i);
            long[] numbers = Shared.findNumbersSummingTo(sorted.subList(0, i), WANTED_SUM - high);

            if (numbers.length == 2) {
                return high * numbers[0] * numbers[1];
            }
        }
        return 0;
    }
}
