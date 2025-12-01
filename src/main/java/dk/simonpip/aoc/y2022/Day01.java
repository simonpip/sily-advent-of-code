package dk.simonpip.aoc.y2022;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day01 {
    private Day01() {}

    static int findHighestCalories(List<String> calorieLog, int topX) {
        List<Integer> sums = findCalorieSums(calorieLog);

        return sums.subList(0, topX).stream().mapToInt(e -> e).sum();
    }

    private static List<Integer> findCalorieSums(List<String> calorieLog) {
        List<Integer> sums = new ArrayList<>();

        int current = 0;
        for (String s : calorieLog) {
            if (s.isEmpty()) {
                sums.add(current);
                current = 0;
                continue;
            }
            current += Integer.parseInt(s);
        }
        sums.add(current);
        sums.sort(Comparator.reverseOrder());
        return sums;
    }
}
