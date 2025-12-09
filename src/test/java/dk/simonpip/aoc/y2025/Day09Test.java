package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.Coordinate;
import dk.simonpip.aoc.util.FileUtil;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day09Test extends TestCase {
    public void testSolveFirst_Example() throws IOException {
        List<Coordinate> coordinates = FileUtil.readLines("y2025/day09_example.txt", Coordinate::new);

        long actual = Day09.solveFirst(coordinates);

        assertThat(actual, equalTo(50L));
    }

    public void testSolveFirst() throws IOException {
        List<Coordinate> coordinates = FileUtil.readLines("y2025/day09.txt", Coordinate::new);

        long actual = Day09.solveFirst(coordinates);

        assertThat(actual, equalTo(4746238001L));
    }

    public void testSolveSecond_Example() throws IOException {
        List<Coordinate> coordinates = FileUtil.readLines("y2025/day09_example.txt", Coordinate::new);

        long actual = Day09.solveSecond(coordinates);

        assertThat(actual, equalTo(24L));
    }

    public void testSolveSecond() throws IOException {
        List<Coordinate> coordinates = FileUtil.readLines("y2025/day09.txt", Coordinate::new);

        long actual = Day09.solveSecond(coordinates);

//        long actual = dk.simonpip.aoc.y2025.test.Day09.doPuzzle2(FileUtil.readLines("y2025/day09.txt"));

        assertThat(actual, equalTo(1552139370L));
    }
}