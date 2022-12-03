package dk.nuuday.sily.aoc.y2022;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day03 {
    private static final int NUM_CHARACTERS = 26;
    private static final int START_LOWER_CASE = 97;
    private static final int END_LOWER_CASE_EX = START_LOWER_CASE + NUM_CHARACTERS;
    private static final int START_UPPER_CASE = 65;
    private static final int END_UPPER_CASE_EX = START_UPPER_CASE + NUM_CHARACTERS;

    static int findMisplacedItems(List<String> containers) {
        return containers.stream()
                .mapToInt(Day03::findMisplacedItem)
                .map(Day03::findPriority)
                .sum();
    }

    static int findAuthenticityItems(List<String> containers) {
        List<Integer> authenticityItems = new ArrayList<>(containers.size() / 3);
        for (int i = 0; i < containers.size(); i += 3) {
            List<String> group = containers.subList(i, i + 3);

            authenticityItems.add(findSharedItem(group));
        }

        return authenticityItems.stream().mapToInt(Day03::findPriority).sum();
    }

    private static char findMisplacedItem(String container) {
        String leftContainer = container.substring(0, container.length() / 2);
        String rightContainer = container.substring(container.length() / 2);

        return (char) leftContainer.chars()
                .filter(e -> rightContainer.chars().anyMatch(f -> e == f))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "No duplicate items found: " + leftContainer + " - " + rightContainer));
    }

    private static int findSharedItem(List<String> containers) {
        List<Set<Integer>> containerSets = containers.stream()
                .map(e -> {
                    Set<Integer> set = new HashSet<>();
                    e.chars().forEach(set::add);
                    return set;
                })
                .collect(Collectors.toList());

        Set<Integer> firstSet = containerSets.get(0);
        for (Integer item : firstSet) {
            boolean shared = containerSets.stream().allMatch(e -> e.contains(item));
            if (shared) {
                return item;
            }
        }
        throw new RuntimeException("No shared items found: [" + String.join(", ", containers) + "]");
    }

    private static int findPriority(int item) {
        if (item >= START_LOWER_CASE && item < END_LOWER_CASE_EX) {
            return item - START_LOWER_CASE + 1;
        } else if (item >= START_UPPER_CASE && item < END_UPPER_CASE_EX) {
            return item - START_UPPER_CASE + NUM_CHARACTERS + 1;
        }
        throw new RuntimeException("Item outside of ranges: " + item);
    }
}
