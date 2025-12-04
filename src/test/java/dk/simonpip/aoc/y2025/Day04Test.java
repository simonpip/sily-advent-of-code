package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.FileUtil;
import junit.framework.TestCase;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day04Test extends TestCase {
    public void testSolveFirst_Example() throws IOException {
        boolean[][] grid = FileUtil.readFile("y2025/day04_example.txt", Day04::convert);

        int actual = Day04.solveFirst(grid).getA();

        assertThat(actual, equalTo(13));
    }

    public void testSolveFirst() throws IOException {
        boolean[][] grid = FileUtil.readFile("y2025/day04.txt", Day04::convert);

        int actual = Day04.solveFirst(grid).getA();

        assertThat(actual, equalTo(1604));
    }

    public void testSolveSecond_Example() throws IOException {
        boolean[][] grid = FileUtil.readFile("y2025/day04_example.txt", Day04::convert);

        int actual = Day04.solveSecond(grid);

        assertThat(actual, equalTo(43));
    }

    public void testSolveSecond() throws IOException {
        boolean[][] grid = FileUtil.readFile("y2025/day04.txt", Day04::convert);

        int actual = Day04.solveSecond(grid);

        assertThat(actual, equalTo(9397));
    }
}