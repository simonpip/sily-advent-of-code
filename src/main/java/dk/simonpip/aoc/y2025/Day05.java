package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.Pair;

import java.util.*;

public class Day05 {
    public static long solveFirst(Ingredients ingredients) {
        return ingredients.getIngredients().stream()
                .filter(ingredient -> ingredients.getRanges().stream()
                        .anyMatch(range -> ingredient >= range.getA() && ingredient <= range.getB()))
                .count();
    }

    public static long solveSecond(Ingredients ingredients) {
        ingredients.getRanges().sort(Comparator.comparing(Pair::getA));

        List<Pair<Long, Long>> combinedRanges = new ArrayList<>();
        long left = ingredients.getRanges().get(0).getA();
        long right = ingredients.getRanges().get(0).getB();
        for (Pair<Long, Long> range : ingredients.getRanges()) {
            if (range.getA() > right + 1) {
                // Gap
                combinedRanges.add(new Pair<>(left, right));

                left = range.getA();
                right = range.getB();
            }
            right = Math.max(right, range.getB());
        }
        combinedRanges.add(new Pair<>(left, right));

        return combinedRanges.stream()
                .mapToLong(pair -> pair.getB() - pair.getA() + 1)
                .sum();
    }

    public static class Ingredients {
        private final List<Pair<Long, Long>> ranges;
        private final List<Long> ingredients;

        public List<Pair<Long, Long>> getRanges() {
            return ranges;
        }

        public List<Long> getIngredients() {
            return ingredients;
        }

        public Ingredients(List<String> lines) {
            this.ranges = new ArrayList<>();
            this.ingredients = new ArrayList<>();
            boolean addIngredients = false;
            for (String line : lines) {
                if (line.isEmpty()) {
                    addIngredients = true;
                    continue;
                }
                if (addIngredients) {
                    ingredients.add(Long.valueOf(line));
                } else {
                    String[] parts = line.split("-");
                    ranges.add(new Pair<>(
                            Long.valueOf(parts[0]),
                            Long.valueOf(parts[1])));
                }
            }
        }
    }
}
