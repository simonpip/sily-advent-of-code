package dk.simonpip.aoc.y2020;

import dk.simonpip.aoc.util.Shared;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

class Day09 {
    private Day09() {
    }

    static long findWeakness(List<Long> code, int preambleLength) {
        for (int i = preambleLength; i < code.size(); i++) {
            Long value = code.get(i);

            if (Shared.findNumbersSummingTo(code.subList(i - preambleLength, i), value).length == 0) {
                return value;
            }
        }
        return 0;
    }

    static long breakEncryption(List<Long> code, int preambleLength) {
        long weakness = findWeakness(code, preambleLength);

        SortedSet<Long> numbers = new TreeSet<>();
        long sum = 0;
        int index = 0;
        while (index < code.size() - 1) {
            for (int i = index; i < code.size(); i++) {
                long number = code.get(i);
                numbers.add(number);
                sum += number;

                if (sum == weakness && numbers.size() > 1) {
                    return numbers.first() + numbers.last();
                } else if (sum > weakness) {
                    numbers.clear();
                    sum = 0;
                }
            }
            index++;
        }
        return 0;
    }
}
