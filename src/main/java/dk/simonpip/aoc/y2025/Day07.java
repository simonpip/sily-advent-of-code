package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.Grid;
import dk.simonpip.aoc.util.LineUtil;
import dk.simonpip.aoc.util.Pair;

import java.util.Arrays;
import java.util.List;

public class Day07 {
    public static int solveFirst(Experiment experiment) {
        return solve(experiment).getA();
    }

    public static long solveSecond(Experiment experiment) {
        return solve(experiment).getB();
    }

    public static Pair<Integer, Long> solve(Experiment experiment) {
        long[] beams = new long[experiment.getSplitters().getWidth()];
        beams[experiment.getStartingBeam()]++;

        int splittersHit = 0;
        for (int x = 0; x < experiment.getSplitters().getHeight(); x++) {
            long[] newBeams = new long[beams.length];
            for (int y = 0; y < experiment.getSplitters().getWidth(); y++) {
                long currBeams = beams[y];
                if (currBeams == 0) {
                    continue;
                }

                if (experiment.getSplitters().get(x, y)) {
                    newBeams[y - 1] += currBeams;
                    newBeams[y + 1] += currBeams;
                    splittersHit++;
                } else {
                    newBeams[y] += beams[y];
                }
            }
            beams = newBeams;
        }
        return new Pair<>(
                splittersHit,
                Arrays.stream(beams).sum());
    }

    public static class Experiment {
        private final int startingBeam;
        private final Grid<Boolean> splitters;

        public int getStartingBeam() { return startingBeam; }
        public Grid<Boolean> getSplitters() { return splitters; }

        public Experiment(List<String> lines) {
            String emitterLine = LineUtil.removeFirst(lines);
            this.startingBeam = emitterLine.indexOf('S');
            this.splitters = new Grid<>(lines, c -> c == '^');
        }
    }
}
