package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.FileUtil;
import dk.simonpip.aoc.util.Shared;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day12Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "Sabqponm",
            "abcryxxl",
            "accszExk",
            "acctuvwj",
            "abdefghi");

    @Test
    public void testFindNumberOfStepsExample() {
        char[][] data = Shared.convertToCharArray(EXAMPLE_DATA);

        int actual = Day12.findNumberOfSteps(data);

        assertThat(actual, equalTo(31));
    }

    @Test
    public void testFindNumberOfStepsData() throws IOException {
        char[][] data = FileUtil.readFile("y2022/day12.txt", Shared::convertToCharArray);

        int actual = Day12.findNumberOfSteps(data);

        assertThat(actual, equalTo(472));
    }

    @Test
    public void testFindMinimalNumberOfStepsExample() {
        char[][] data = Shared.convertToCharArray(EXAMPLE_DATA);

        int actual = Day12.findMinimalNumberOfSteps(data);

        assertThat(actual, equalTo(29));
    }

    @Test
    public void testFindMinimalNumberOfStepsData() throws IOException {
        char[][] data = FileUtil.readFile("y2022/day12.txt", Shared::convertToCharArray);

        int actual = Day12.findMinimalNumberOfSteps(data);

        assertThat(actual, equalTo(465));
    }
}
