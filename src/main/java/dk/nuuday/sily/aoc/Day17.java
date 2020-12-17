package dk.nuuday.sily.aoc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

class Day17 {
    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;
    private static final int W = 3;

    private Day17() {
    }

    static int runGameOfLife(CellSet cellSet, int cycles) {
        boolean lastD = cellSet.lastD;
        CellSet current = cellSet;
        for (int i = 0; i < cycles; i++) {
            int[] minCoords = current.findMinCoordinates();
            int[] maxCoords = current.findMaxCoordinates();

            CellSet newSet = new CellSet(lastD);
            for (int x = minCoords[X]; x <= maxCoords[X]; x++) {
                for (int y = minCoords[Y]; y <= maxCoords[Y]; y++) {
                    for (int z = minCoords[Z]; z <= maxCoords[Z]; z++) {
                        if (lastD) {
                            for (int w = minCoords[W]; w <= maxCoords[W]; w++) {
                                int neighbours = countNeighbours(x, y, z, w, current);
                                boolean active = current.contains(x, y, z, w);
                                if ((active && (neighbours == 2 || neighbours == 3)) || (!active && neighbours == 3)) {
                                    newSet.add(x, y, z, w);
                                }
                            }
                        } else {
                            int neighbours = countNeighbours(x, y, z, current);
                            boolean active = current.contains(x, y, z);
                            if ((active && (neighbours == 2 || neighbours == 3)) || (!active && neighbours == 3)) {
                                newSet.add(x, y, z);
                            }
                        }
                    }
                }
            }
            current = newSet;
        }

        return current.size();
    }

    private static int countNeighbours(int x, int y, int z, CellSet cellSet) {
        return (int) Arrays.stream(NEIGHBOUR_DELTA_ARRAY)
                .filter(d -> cellSet.contains(x + d[X], y + d[Y], z + d[Z]))
                .count();
    }

    private static int countNeighbours(int x, int y, int z, int w, CellSet cellSet) {
        return (int) Arrays.stream(NEIGHBOUR_DELTA_ARRAY_4D)
                .filter(d -> cellSet.contains(x + d[X], y + d[Y], z + d[Z], w + d[W]))
                .count();
    }

    static final class CellSet {
        private static final char ACTIVE = '#';

        private final SortedSet<int[]> cells;
        private final boolean lastD;

        private CellSet(boolean lastD) {
            Comparator<int[]> c1 = Comparator.comparing(e -> e[X]);
            Comparator<int[]> c2 = c1.thenComparing(e -> e[Y]);
            Comparator<int[]> c3 = c2.thenComparing(e -> e[Z]);
            Comparator<int[]> c4 = c3.thenComparing(e -> e[W]);
            this.cells = new TreeSet<>(c4);
            this.lastD = lastD;
        }

        CellSet(List<String> lines, boolean lastD) {
            this(lastD);

            for (int y = 0; y < lines.size(); y++) {
                String line = lines.get(y);
                for (int x = 0; x < line.length(); x++) {
                    if (line.charAt(x) == ACTIVE) {
                        this.cells.add(new int[]{x, y, 0, 0});
                    }
                }
            }
        }

        private int[] findMinCoordinates() {
            int minX = cells.stream().min(Comparator.comparing(e -> e[X])).get()[X] - 1;
            int minY = cells.stream().min(Comparator.comparing(e -> e[Y])).get()[Y] - 1;
            int minZ = cells.stream().min(Comparator.comparing(e -> e[Z])).get()[Z] - 1;
            int minW = cells.stream().min(Comparator.comparing(e -> e[W])).get()[W];

            if (lastD) {
                minW--;
            }

            return new int[]{minX, minY, minZ, minW};
        }

        private int[] findMaxCoordinates() {
            int maxX = cells.stream().max(Comparator.comparing(e -> e[X])).get()[X] + 1;
            int maxY = cells.stream().max(Comparator.comparing(e -> e[Y])).get()[Y] + 1;
            int maxZ = cells.stream().max(Comparator.comparing(e -> e[Z])).get()[Z] + 1;
            int maxW = cells.stream().max(Comparator.comparing(e -> e[W])).get()[W];

            if (lastD) {
                maxW++;
            }

            // Dead cells on the border are still relevant
            return new int[]{maxX, maxY, maxZ, maxW};
        }

        private void add(int x, int y, int z) {
            add(x, y, z, 0);
        }

        private void add(int x, int y, int z, int w) {
            cells.add(new int[]{x, y, z, w});
        }

        private boolean contains(int x, int y, int z) {
            return contains(x, y, z, 0);
        }

        private boolean contains(int x, int y, int z, int w) {
            return cells.contains(new int[]{x, y, z, w});
        }

        private int size() {
            return cells.size();
        }
    }

    // Not correct place for constants, but I wanted them out of the way of the top.
    private static final int[][] NEIGHBOUR_DELTA_ARRAY = new int[][]{
            new int[]{-1, -1, -1},
            new int[]{-1, -1, 0},
            new int[]{-1, -1, 1},
            new int[]{-1, 0, -1},
            new int[]{-1, 0, 0},
            new int[]{-1, 0, 1},
            new int[]{-1, 1, -1},
            new int[]{-1, 1, 0},
            new int[]{-1, 1, 1},
            new int[]{0, -1, -1},
            new int[]{0, -1, 0},
            new int[]{0, -1, 1},
            new int[]{0, 0, -1},
            new int[]{0, 0, 1},
            new int[]{0, 1, -1},
            new int[]{0, 1, 0},
            new int[]{0, 1, 1},
            new int[]{1, -1, -1},
            new int[]{1, -1, 0},
            new int[]{1, -1, 1},
            new int[]{1, 0, -1},
            new int[]{1, 0, 0},
            new int[]{1, 0, 1},
            new int[]{1, 1, -1},
            new int[]{1, 1, 0},
            new int[]{1, 1, 1}
    };

    private static final int[][] NEIGHBOUR_DELTA_ARRAY_4D = new int[][]{
            new int[]{-1, -1, -1, -1},
            new int[]{-1, -1, -1, 0},
            new int[]{-1, -1, -1, 1},
            new int[]{-1, -1, 0, -1},
            new int[]{-1, -1, 0, 0},
            new int[]{-1, -1, 0, 1},
            new int[]{-1, -1, 1, -1},
            new int[]{-1, -1, 1, 0},
            new int[]{-1, -1, 1, 1},
            new int[]{-1, 0, -1, -1},
            new int[]{-1, 0, -1, 0},
            new int[]{-1, 0, -1, 1},
            new int[]{-1, 0, 0, -1},
            new int[]{-1, 0, 0, 0},
            new int[]{-1, 0, 0, 1},
            new int[]{-1, 0, 1, -1},
            new int[]{-1, 0, 1, 0},
            new int[]{-1, 0, 1, 1},
            new int[]{-1, 1, -1, -1},
            new int[]{-1, 1, -1, 0},
            new int[]{-1, 1, -1, 1},
            new int[]{-1, 1, 0, -1},
            new int[]{-1, 1, 0, 0},
            new int[]{-1, 1, 0, 1},
            new int[]{-1, 1, 1, -1},
            new int[]{-1, 1, 1, 0},
            new int[]{-1, 1, 1, 1},
            new int[]{0, -1, -1, -1},
            new int[]{0, -1, -1, 0},
            new int[]{0, -1, -1, 1},
            new int[]{0, -1, 0, -1},
            new int[]{0, -1, 0, 0},
            new int[]{0, -1, 0, 1},
            new int[]{0, -1, 1, -1},
            new int[]{0, -1, 1, 0},
            new int[]{0, -1, 1, 1},
            new int[]{0, 0, -1, -1},
            new int[]{0, 0, -1, 0},
            new int[]{0, 0, -1, 1},
            new int[]{0, 0, 0, -1},
            new int[]{0, 0, 0, 1},
            new int[]{0, 0, 1, -1},
            new int[]{0, 0, 1, 0},
            new int[]{0, 0, 1, 1},
            new int[]{0, 1, -1, -1},
            new int[]{0, 1, -1, 0},
            new int[]{0, 1, -1, 1},
            new int[]{0, 1, 0, -1},
            new int[]{0, 1, 0, 0},
            new int[]{0, 1, 0, 1},
            new int[]{0, 1, 1, -1},
            new int[]{0, 1, 1, 0},
            new int[]{0, 1, 1, 1},
            new int[]{1, -1, -1, -1},
            new int[]{1, -1, -1, 0},
            new int[]{1, -1, -1, 1},
            new int[]{1, -1, 0, -1},
            new int[]{1, -1, 0, 0},
            new int[]{1, -1, 0, 1},
            new int[]{1, -1, 1, -1},
            new int[]{1, -1, 1, 0},
            new int[]{1, -1, 1, 1},
            new int[]{1, 0, -1, -1},
            new int[]{1, 0, -1, 0},
            new int[]{1, 0, -1, 1},
            new int[]{1, 0, 0, -1},
            new int[]{1, 0, 0, 0},
            new int[]{1, 0, 0, 1},
            new int[]{1, 0, 1, -1},
            new int[]{1, 0, 1, 0},
            new int[]{1, 0, 1, 1},
            new int[]{1, 1, -1, -1},
            new int[]{1, 1, -1, 0},
            new int[]{1, 1, -1, 1},
            new int[]{1, 1, 0, -1},
            new int[]{1, 1, 0, 0},
            new int[]{1, 1, 0, 1},
            new int[]{1, 1, 1, -1},
            new int[]{1, 1, 1, 0},
            new int[]{1, 1, 1, 1}
    };
}
