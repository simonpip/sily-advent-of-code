package dk.nuuday.sily.aoc.y2022;

import java.util.Arrays;
import java.util.List;

public class Day08 {
    private static final int ASCII_NUMBERS_START = 48;

    static long findVisibleTrees(List<String> heights) {
        Tree[][] treeArray = createTreeArray(heights);

        for (Tree[] trees : treeArray) {
            int maxHeight = -1;
            for (Tree tree : trees) {
                maxHeight = checkTree(tree, maxHeight);
            }

            maxHeight = -1;
            for (int j = trees.length - 1; j >= 0; j--) {
                maxHeight = checkTree(trees[j], maxHeight);
            }
        }

        for (int i = 0; i < treeArray[0].length; i++) {
            int maxHeight = -1;
            for (Tree[] trees : treeArray) {
                maxHeight = checkTree(trees[i], maxHeight);
            }

            maxHeight = -1;
            for (int j = treeArray.length - 1; j >= 0; j--) {
                maxHeight = checkTree(treeArray[j][i], maxHeight);
            }
        }

        return Arrays.stream(treeArray).mapToLong(e -> Arrays.stream(e).filter(f -> f.visible).count()).sum();
    }

    private static int checkTree(Tree tree, int maxHeight) {
        if (tree.height > maxHeight) {
            tree.visible = true;
            return tree.height;
        }
        return maxHeight;
    }

    static int findTreeHouseCandidates(List<String> heights) {
        Tree[][] treeArray = createTreeArray(heights);

        for (int x = 1; x < treeArray.length - 1; x++) {
            Tree[] treeLine = treeArray[x];
            for (int y = 1; y < treeLine.length - 1; y++) {
                Tree candidate = treeLine[y];

                int x1 = x + 1;
                for (; x1 < treeArray.length; x1++) {
                    if (treeArray[x1][y].height >= candidate.height) {
                        x1++;
                        break;
                    }
                }
                candidate.sees = x1 - x - 1;

                int x2 = x - 1;
                for (; x2 >= 0; x2--) {
                    if (treeArray[x2][y].height >= candidate.height) {
                        x2--;
                        break;
                    }
                }
                candidate.sees *= x - x2 - 1;

                int y1 = y + 1;
                for (; y1 < treeLine.length; y1++) {
                    if (treeArray[x][y1].height >= candidate.height) {
                        y1++;
                        break;
                    }
                }
                candidate.sees *= y1 - y - 1;

                int y2 = y - 1;
                for (; y2 >= 0; y2--) {
                    if (treeArray[x][y2].height >= candidate.height) {
                        y2--;
                        break;
                    }
                }
                candidate.sees *= y - y2 - 1;
            }
        }

        return Arrays.stream(treeArray)
                .mapToInt(e -> Arrays.stream(e)
                        .mapToInt(f -> f.sees)
                        .max()
                        .orElseThrow(() -> new RuntimeException("No trees found")))
                .max()
                .orElseThrow(() -> new RuntimeException("No trees found"));
    }

    private static Tree[][] createTreeArray(List<String> heights) {
        Tree[][] trees = new Tree[heights.size()][];

        int i = 0;
        for (String heightLine : heights) {
            trees[i] = new Tree[heightLine.length()];
            char[] chars = heightLine.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                int height = chars[j] - ASCII_NUMBERS_START;

                trees[i][j] = new Tree(height);
            }

            i++;
        }

        return trees;
    }

    static class Tree {
        private final int height;
        private boolean visible;
        private int sees;

        public Tree(int height) {
            this.height = height;
        }
    }
}
