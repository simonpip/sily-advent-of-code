package dk.nuuday.sily.aoc.y2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Day19 {
    private Day19() {
    }

    static long countValidElements(DataSet dataSet) {
        List<String> output = dataSet.elements.stream()
                .filter(e -> testElement(dataSet, e))
                .collect(Collectors.toList());

        return output.size();
    }

    static boolean testElement(DataSet dataSet, String element) {
        Set<Integer> indices = dataSet.ruleMap.get(0).check(element, Set.of(0), dataSet.ruleMap);

        return indices.contains(element.length());
    }

    static final class DataSet {
        private final Map<Integer, Rule> ruleMap;
        private final List<String> elements;

        DataSet(List<String> lines) {
            int splitIndex = lines.indexOf("");

            this.ruleMap = new HashMap<>(splitIndex);
            for (String line : lines.subList(0, splitIndex)) {
                String[] split = line.split(": ");

                int index = Integer.parseInt(split[0]);
                String rule = split[1];

                if (rule.contains("|")) {
                    ruleMap.put(index, new OrRule(rule));
                } else if (rule.matches("[0-9 ]+")) {
                    ruleMap.put(index, new AndRule(rule));
                } else {
                    ruleMap.put(index, new BaseRule(rule));
                }
            }

            elements = lines.subList(splitIndex + 1, lines.size());
        }
    }

    private interface Rule {
        Set<Integer> check(String string, Set<Integer> indices, Map<Integer, Rule> ruleMap);
    }

    private static final class BaseRule implements Rule {
        private final char letter;

        private BaseRule(String rule) {
            this.letter = rule.charAt(1);
        }

        @Override
        public Set<Integer> check(String string, Set<Integer> indices, Map<Integer, Rule> ruleMap) {
            Set<Integer> newIndices = new HashSet<>(indices.size());
            for (Integer index : indices) {
                if (index >= string.length() || letter != string.charAt(index)) {
                    continue;
                }
                newIndices.add(index + 1);
            }
            return newIndices;
        }
    }

    private static final class AndRule implements Rule {
        private final int[] ruleIndices;

        private AndRule(String rule) {
            this.ruleIndices = Arrays.stream(rule.split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        @Override
        public Set<Integer> check(String string, Set<Integer> indices, Map<Integer, Rule> ruleMap) {
            Set<Integer> newIndices = indices;
            for (int i : ruleIndices) {
                newIndices = ruleMap.get(i).check(string, newIndices, ruleMap);
            }
            return newIndices;
        }
    }

    private static final class OrRule implements Rule {
        private final List<Rule> internalRules;

        private OrRule(String rule) {
            this.internalRules = Arrays
                    .stream(rule.split(" [|]{1} "))
                    .map(AndRule::new)
                    .collect(Collectors.toList());
        }

        @Override
        public Set<Integer> check(String string, Set<Integer> indices, Map<Integer, Rule> ruleMap) {
            Set<Integer> newIndices = new HashSet<>(indices.size());
            for (Rule rule : internalRules) {
                newIndices.addAll(rule.check(string, indices, ruleMap));
            }
            return newIndices;
        }
    }
}
