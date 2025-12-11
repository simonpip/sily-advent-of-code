package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.FileUtil;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day11Test extends TestCase {
    public void testSolveFirst_Example() throws IOException {
        Map<String, Day11.Device> devices = FileUtil.readFile("y2025/day11_example.txt", Day11::convert);

        long actual = Day11.solveFirst(devices);

        assertThat(actual, equalTo(5L));
    }

    public void testSolveFirst() throws IOException {
        Map<String, Day11.Device> devices = FileUtil.readFile("y2025/day11.txt", Day11::convert);

        long actual = Day11.solveFirst(devices);

        assertThat(actual, equalTo(566L));
    }

    public void testSolveSecond_Example() throws IOException {
        Map<String, Day11.Device> devices = FileUtil.readFile("y2025/day11_example2.txt", Day11::convert);

        long actual = Day11.solveSecond(devices);

        assertThat(actual, equalTo(2L));
    }

    public void testSolveSecond() throws IOException {
        Map<String, Day11.Device> devices = FileUtil.readFile("y2025/day11.txt", Day11::convert);

        long actual = Day11.solveSecond(devices);

        assertThat(actual, equalTo(331837854931968L));
    }
}