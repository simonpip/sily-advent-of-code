package dk.simonpip.aoc.y2021;

import com.google.common.primitives.Booleans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Day03 {
    private Day03() {}

    static int diagnose(List<String> report) {
        List<boolean[]> parsedReport = report.stream().map(Day03::toArray).collect(Collectors.toList());

        int entryLength = parsedReport.get(0).length;

        boolean[] mostCommon = new boolean[entryLength];
        boolean[] leastCommon = new boolean[entryLength];
        for (int i = 0; i < mostCommon.length; i++) {
            mostCommon[i] = findMostCommonBit(parsedReport, i);
            leastCommon[i] = !mostCommon[i];
        }

        return toInt(mostCommon) * toInt(leastCommon);
    }

    static int diagnoseComplex(List<String> report) {
        List<boolean[]> parsedReport = report.stream().map(Day03::toArray).collect(Collectors.toList());

        boolean[] mostCommonArray = findMostCommonArray(parsedReport, false);
        boolean[] leastCommonArray = findMostCommonArray(parsedReport, true);

        return toInt(mostCommonArray) * toInt(leastCommonArray);
    }

    private static boolean[] toArray(String s) {
        return Booleans.toArray(s.chars().mapToObj(c -> (char) c).map(c -> c == '1').collect(Collectors.toList()));
    }

    private static boolean findMostCommonBit(List<boolean[]> report, int index) {
        int threshold = report.size() / 2;

        int count = 0;
        for (boolean[] reportEntry : report) {
            if (reportEntry[index]) {
                count++;
            }
        }

        // If 1 and 0 bits are split evenly, prefer truth
        // If not, if count of 1 bits is higher than threshold, return 1 bit
        // When report contains odd number, threshold is floor of division, so only when count is strictly higher is
        // there more 1 bits
        return report.size() % 2 == 0
                ? count >= threshold
                : count > threshold;
    }

    private static boolean[] findMostCommonArray(List<boolean[]> report, boolean negate) {
        List<boolean[]> copy = new ArrayList<>(report);
        int entryLength = copy.get(0).length;
        for (int i = 0; i < entryLength; i++) {
            boolean mostCommon = negate != findMostCommonBit(copy, i);

            final int index = i;
            copy = copy.stream().filter(e -> e[index] == mostCommon).collect(Collectors.toList());

            if (copy.size() == 1) {
                break;
            }
        }
        return copy.get(0);
    }

    private static int toInt(boolean[] array) {
        int value = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[array.length - (i + 1)]) {
                value += (int) Math.pow(2, i);
            }
        }
        return value;
    }
}
