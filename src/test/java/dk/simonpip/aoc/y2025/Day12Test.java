package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.FileUtil;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day12Test extends TestCase {
    public void testSolveFirst_Example() throws IOException {
        List<Day12.Tree> trees = FileUtil.readFile("y2025/day12_example.txt", Day12::convert);

        long actual = Day12.solveFirst(trees);

        assertThat(actual, equalTo(2L));
    }

    public void testSolveFirst() throws IOException {
        List<Day12.Tree> trees = FileUtil.readFile("y2025/day12.txt", Day12::convert);

        long actual = Day12.solveFirst(trees);

        assertThat(actual, equalTo(536L));
    }
}