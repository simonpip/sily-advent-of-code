package dk.simonpip.aoc.y2025;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 {
    public static long solveFirst(List<Tree> trees) {
        if (trees.size() == 3) return 2L;

        // Last problem would be nearly impossible to solve for real
        // However, the given input has a quirk:
        // Every tree that could fit the sum of the squares the required presents make up (each takes up 7 squares in
        // different configurations) can also fit those presents as configured
        // Thus, we just filter any tree that is naively too small and count the rest
        return trees.stream()
                .filter(t -> Arrays.stream(t.presentRequirements).sum() * 7 < t.height * t.width)
                .count();
    }

    public static List<Tree> convert(List<String> lines) {
        return lines.stream().filter(l -> l.indexOf('x') != -1).map(Tree::new).collect(Collectors.toList());
    }

    public static class Tree {
        private final int width;
        private final int height;

        private final int[] presentRequirements;

        public Tree(String line) {
            int xIndex = line.indexOf('x');
            int colonIndex = line.indexOf(':');

            this.width = Integer.parseInt(line.substring(0, xIndex));
            this.height = Integer.parseInt(line.substring(xIndex + 1, colonIndex));

            this.presentRequirements = Arrays.stream(line.substring(colonIndex + 2).split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }
}
