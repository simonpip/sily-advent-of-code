package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.Grid;

import java.util.List;

class Day03 {
    private Day03() {
    }

    static long countTrees(Landscape landscape) {
        return countTrees(landscape, 3, 1);
    }

    static long countTreesMultiple(Landscape landscape) {
        return countTrees(landscape, 1, 1)
                * countTrees(landscape, 3, 1)
                * countTrees(landscape, 5, 1)
                * countTrees(landscape, 7, 1)
                * countTrees(landscape, 1, 2);
    }

    private static long countTrees(Landscape landscape, int left, int down) {
        int x = left;
        int y = down;
        long count = 0;
        while (y < landscape.getHeight()) {
            if (landscape.isTree(x, y)) {
                count++;
            }

            y += down;
            x += left;
        }

        return count;
    }

    static final class Landscape extends Grid<Boolean> {
        private static final char TREE = '#';

        Landscape(List<String> strings) {
            super(strings, c -> c.equals(TREE));
        }

        boolean isTree(int x, int y) {
            return get(x % getWidth(), y);
        }
    }
}
