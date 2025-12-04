package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.Pair;

import java.util.List;

public class Day04 {
    public static Pair<Integer, boolean[][]> solveFirst(boolean[][] grid) {
        int movableBales = 0;
        boolean[][] gridCopy = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            boolean[] booleans = grid[i];
            for (int j = 0; j < booleans.length; j++) {
                boolean hasBale = booleans[j];
                if (!hasBale) {
                    continue;
                }

                int neighbourBales = countNeighbour(grid, i - 1, j - 1)
                        + countNeighbour(grid, i - 1, j)
                        + countNeighbour(grid, i - 1, j + 1)
                        + countNeighbour(grid, i, j - 1)
                        + countNeighbour(grid, i, j + 1)
                        + countNeighbour(grid, i + 1, j - 1)
                        + countNeighbour(grid, i + 1, j)
                        + countNeighbour(grid, i + 1, j + 1);
                if (neighbourBales < 4) {
                    movableBales++;
                } else {
                    gridCopy[i][j] = true;
                }
            }
        }
        return new Pair<>(movableBales, gridCopy);
    }

    public static int solveSecond(boolean[][] grid) {
        int movableBales = 0;

        Pair<Integer, boolean[][]> loop = solveFirst(grid);
        while (loop.getA() > 0) {
            movableBales += loop.getA();

            loop = solveFirst(loop.getB());
        }

        return movableBales;
    }

    private static int countNeighbour(boolean[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) {
            // Coordinates are outside grid, not a bale
            return 0;
        }

        return grid[i][j] ? 1 : 0;
    }

    public static boolean[][] convert(List<String> file) {
        boolean[][] grid = new boolean[file.size()][file.get(0).length()];
        for (int i = 0; i < file.size(); i++) {
            String[] line = file.get(i).split("");

            for (int j = 0; j < line.length; j++) {
                grid[i][j] = "@".equals(line[j]);
            }
        }
        return grid;
    }
}
