package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.Grid;
import dk.simonpip.aoc.util.Pair;

import java.util.List;

public class Day04 {
    public static Pair<Integer, Grid<Boolean>> solveFirst(Grid<Boolean> grid) {
        int movableBales = 0;
        Grid<Boolean> gridCopy = new Grid<>(grid.getHeight(), grid.getWidth(), false);
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                boolean hasBale = grid.get(x, y);
                if (!hasBale) {
                    continue;
                }

                int neighbourBales = countNeighbour(grid, x - 1, y - 1)
                        + countNeighbour(grid, x - 1, y)
                        + countNeighbour(grid, x - 1, y + 1)
                        + countNeighbour(grid, x, y - 1)
                        + countNeighbour(grid, x, y + 1)
                        + countNeighbour(grid, x + 1, y - 1)
                        + countNeighbour(grid, x + 1, y)
                        + countNeighbour(grid, x + 1, y + 1);
                if (neighbourBales < 4) {
                    movableBales++;
                } else {
                    gridCopy.set(x, y, true);
                }
            }
        }
        return new Pair<>(movableBales, gridCopy);
    }

    public static int solveSecond(Grid<Boolean> grid) {
        int movableBales = 0;

        Pair<Integer, Grid<Boolean>> loop = solveFirst(grid);
        while (loop.getA() > 0) {
            movableBales += loop.getA();

            loop = solveFirst(loop.getB());
        }

        return movableBales;
    }

    private static int countNeighbour(Grid<Boolean> grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.getWidth() || y >= grid.getHeight()) {
            // Coordinates are outside grid, not a bale
            return 0;
        }

        return grid.get(x, y) ? 1 : 0;
    }

    public static Grid<Boolean> convert(List<String> file) {
        return new Grid<>(file, c -> c == '@');
    }
}
