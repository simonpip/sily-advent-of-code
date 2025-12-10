package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.FileUtil;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day10Test extends TestCase {
    public void testSolveFirst_Example() throws IOException {
        List<Day10.Indicator> indicators
                = FileUtil.readLines("y2025/day10_example.txt", Day10.Indicator::new);

        int actual = Day10.solveFirst(indicators);

        assertThat(actual, equalTo(7));
    }

    public void testSolveFirst() throws IOException {
        List<Day10.Indicator> indicators
                = FileUtil.readLines("y2025/day10.txt", Day10.Indicator::new);

        int actual = Day10.solveFirst(indicators);

        assertThat(actual, equalTo(477));
    }

    public void testSolveSecond_Example() throws IOException {
        List<Day10.Indicator> indicators
                = FileUtil.readLines("y2025/day10_example.txt", Day10.Indicator::new);

        int actual = Day10.solveSecond(indicators);

        assertThat(actual, equalTo(33));
    }

    public void testSolveSecond() throws IOException {
        List<Day10.Indicator> indicators
                = FileUtil.readLines("y2025/day10.txt", Day10.Indicator::new);

        int actual = Day10.solveSecond(indicators);

        assertThat(actual, equalTo(17970));
    }
}