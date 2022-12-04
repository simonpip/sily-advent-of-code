package dk.nuuday.sily.aoc.y2022;

import java.util.List;

public class Day04 {
    private static final String PAIR_DELIMITER = ",";
    private static final String INTERVAL_DELIMITER = "-";

    static long countTotalOverlaps(List<Pair> pairs) {
        return pairs.stream().filter(Day04::totalOverlap).count();
    }

    private static boolean totalOverlap(Pair pair) {
        // if left range encompasses right range, both numbers will be negative
        // if vice versa, both numbers will be positive
        // if neither range fully encompasses, one will be negative and one will be positive, result will be < 0
        // if both ranges share a start or an end (guaranteed one fully encompasses the other), result will be 0
        return 0 <= (pair.left.start - pair.right.start) * (pair.right.end - pair.left.end);
    }

    static long countPartialOverlaps(List<Pair> pairs) {
        return pairs.stream().filter(Day04::partialOverlap).count();
    }

    private static boolean partialOverlap(Pair pair) {
        return pair.left.start <= pair.right.end && pair.right.start <= pair.left.end;
    }

    static class Pair {
        private final Interval left;
        private final Interval right;

        public Pair(String pair) {
            String[] split = pair.split(PAIR_DELIMITER);
            left = new Interval(split[0]);
            right = new Interval(split[1]);
        }
    }

    private static class Interval {
        private final int start;
        private final int end;

        public Interval(String interval) {
            String[] split = interval.split(INTERVAL_DELIMITER);
            start = Integer.parseInt(split[0]);
            end = Integer.parseInt(split[1]);
        }
    }
}
