package dk.simonpip.aoc.y2022;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day11 {
    static long calculateProductOfInspectCount(List<Monkey> monkeys, int iterations, boolean reduceWorry) {
        List<Integer> monkeyIndices = monkeys.stream().map(e -> e.index).sorted().collect(Collectors.toList());
        Map<Integer, Monkey> monkeyMap = monkeys.stream().collect(Collectors.toMap(e -> e.index, Function.identity()));
        long modProduct = -1;
        if (reduceWorry)
            modProduct = monkeys.stream().mapToInt(e -> e.test).reduce((a, b) -> a * b).orElse(monkeys.get(0).test);

        for (int i = 0; i < iterations; i++) {
            for (Integer monkeyIndex : monkeyIndices) {
                monkeyMap.get(monkeyIndex).inspect(monkeyMap, modProduct);
            }
        }

        return monkeyMap.values().stream()
                .map(e -> e.inspectCount)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .mapToLong(e -> e)
                .reduce((a, b) -> a * b)
                .orElseThrow(() -> new RuntimeException("Did not find two max inspect counts"));
    }

    static List<Monkey> convertToMonkeys(List<String> strings) {
        List<Monkey> monkeys = new ArrayList<>();
        for (int i = 0; i < strings.size(); i += 7) {
            monkeys.add(new Monkey(
                    findIndex(i, strings),
                    findStartingItems(i, strings),
                    findOperation(i, strings),
                    findTest(i, strings),
                    findTrueIndex(i, strings),
                    findFalseIndex(i, strings)));
        }
        return monkeys;
    }

    private static int findIndex(int baseIndex, List<String> strings) {
        String indexLine = strings.get(baseIndex);
        return Integer.parseInt(indexLine.substring(7, indexLine.length() - 1));
    }

    private static List<Long> findStartingItems(int baseIndex, List<String> strings) {
        String[] items = strings.get(baseIndex + 1).substring(16).split(", ");
        return Arrays.stream(items).map(Long::valueOf).collect(Collectors.toList());
    }

    private static Function<Long, Long> findOperation(int baseIndex, List<String> strings) {
        String operation = strings.get(baseIndex + 2).substring(21);
        if ("* old".equals(operation)) {
            return e -> e * e;
        } else if ('*' == operation.charAt(0)) {
           int value = Integer.parseInt(operation.substring(2));
           return e -> e * value;
        } else {
           int value = Integer.parseInt(operation.substring(2));
           return e -> e + value;
        }
    }

    private static int findTest(int baseIndex, List<String> strings) {
        return Integer.parseInt(strings.get(baseIndex + 3).substring(19));
    }

    private static int findTrueIndex(int baseIndex, List<String> strings) {
        return Integer.parseInt(strings.get(baseIndex + 4).substring(25));
    }

    private static int findFalseIndex(int baseIndex, List<String> strings) {
        return Integer.parseInt(strings.get(baseIndex + 5).substring(26));
    }

    static class Monkey {
        private final int index;
        private final Queue<Long> items;
        private final Function<Long, Long> operation;
        private final int test;
        private final int trueIndex;
        private final int falseIndex;

        private long inspectCount;

        public Monkey(int index,
                      List<Long> items,
                      Function<Long, Long> operation,
                      int test,
                      int trueIndex,
                      int falseIndex) {
            this.index = index;
            this.items = new ArrayDeque<>(items);
            this.operation = operation;
            this.test = test;
            this.trueIndex = trueIndex;
            this.falseIndex = falseIndex;
        }

        void inspect(Map<Integer, Monkey> monkeyMap, long modProduct) {
            while (!items.isEmpty()) {
                inspectCount++;

                long item = items.poll();
                item = operation.apply(item);
                if (modProduct == -1) {
                    item /= 3;
                } else {
                    item %= modProduct;
                }

                if (item % test == 0) {
                    monkeyMap.get(trueIndex).items.add(item);
                } else {
                    monkeyMap.get(falseIndex).items.add(item);
                }
            }
        }
    }
}
