package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.FileUtil;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day03Test extends TestCase {
    public void testFirst_Example() throws IOException {
        List<int[]> arrays = FileUtil.readLines("y2025/day03_example.txt", Day03::convert);

        long actual = Day03.solveFirst(arrays);

        assertThat(actual, equalTo(357L));
    }

    public void testFirst() throws IOException {
        List<int[]> arrays = FileUtil.readLines("y2025/day03.txt", Day03::convert);

        long actual = Day03.solveFirst(arrays);

        assertThat(actual, equalTo(16927L));
    }

    public void testSecond_Example() throws IOException {
        List<int[]> arrays = FileUtil.readLines("y2025/day03_example.txt", Day03::convert);

        long actual = Day03.solveSecond(arrays);

        assertThat(actual, equalTo(3121910778619L));
    }

    public void testSecond() throws IOException {
        List<int[]> arrays = FileUtil.readLines("y2025/day03.txt", Day03::convert);

        long actual = Day03.solveSecond(arrays);

        assertThat(actual, equalTo(167384358365132L));
    }
}