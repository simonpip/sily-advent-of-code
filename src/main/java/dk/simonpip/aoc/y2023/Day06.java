package dk.simonpip.aoc.y2023;

import java.util.List;

public class Day06 {
    public static int calculateMargin(List<Race> races) {
        return races.stream()
                .mapToInt(Day06::calculateWaysToWin)
                .reduce(1, (a, b) -> a * b);
    }

    private static int calculateWaysToWin(Race race) {
        int waysToWin = 0;
        for (long i = (race.time) / 2; i > 0; i--) {
            if (i * (race.time - i) <= race.record) {
                break;
            }
            waysToWin++;
        }

        // Each way is the reverse way, e.g. 2 * 3 and 3 * 2
        waysToWin *= 2;
        if (race.time % 2 == 0) {
            // ... except if time is even, then there's one unique way
            waysToWin--;
        }

        return waysToWin;
    }

    public static final class Race {
        private long time;
        private long record;

        public Race(long time, long record) {
            this.time = time;
            this.record = record;
        }
    }
}
