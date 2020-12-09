package dk.nuuday.sily.aoc.util;

import java.util.List;
import java.util.stream.Collectors;

public class Shared {
    private Shared() {
    }

    public static long[] findNumbersSummingTo(List<Long> numbers, long wantedSum) {
        List<Long> sorted = numbers.stream()
                .filter(e -> e < wantedSum)
                .sorted()
                .collect(Collectors.toList());

        for (int i = sorted.size() - 1; i >= 1; i--) {
            long high = sorted.get(i);

            boolean matchingLow = sorted.subList(0, i).stream().anyMatch(e -> e == wantedSum - high);
            if (matchingLow) {
                return new long[] {wantedSum - high, high};
            }
        }
        return new long[0];
    }
}
