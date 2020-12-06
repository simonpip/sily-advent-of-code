package dk.nuuday.sily.aoc;

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
        while (y < landscape.height) {
            if (landscape.isTree(x, y)) {
                count++;
            }

            y += down;
            x += left;
        }

        return count;
    }

    static final class Landscape {
        private static final char TREE = '#';

        private final int height;
        private final int width;
        private final boolean[][] landscape;

        Landscape(List<String> strings) {
            height = strings.size();
            width = strings.get(0).length();
            landscape = new boolean[width][height];

            for (int i = 0; i < strings.size(); i++) {
                String string = strings.get(i);

                for (int j = 0; j < string.length(); j++) {
                    char letter = string.charAt(j);

                    landscape[j][i] = letter == TREE;
                }
            }
        }

        boolean isTree(int x, int y) {
            return landscape[x % width][y];
        }
    }
}
