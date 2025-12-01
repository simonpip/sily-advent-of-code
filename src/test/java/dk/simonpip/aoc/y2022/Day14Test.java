package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day14Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "498,4 -> 498,6 -> 496,6",
            "503,4 -> 502,4 -> 502,9 -> 494,9");

    @Test
    public void testMeasureSandExample() {
        List<Day14.Line> data = EXAMPLE_DATA.stream().map(Day14.Line::new).collect(Collectors.toList());

        int actual = Day14.measureSand(data, false);

        assertThat(actual, equalTo(24));
    }

    @Test
    public void testMeasureSandData() throws IOException {
        List<Day14.Line> data = FileUtil.readLines("y2022/day14.txt", Day14.Line::new);

        int actual = Day14.measureSand(data, false);

        assertThat(actual, equalTo(805));
    }

    @Test
    public void testMeasureSandBottomExistsExample() {
        List<Day14.Line> data = EXAMPLE_DATA.stream().map(Day14.Line::new).collect(Collectors.toList());

        int actual = Day14.measureSand(data, true);

        assertThat(actual, equalTo(93));
    }

    @Test
    public void testMeasureSandBottomExistsData() throws IOException {
        List<Day14.Line> data = FileUtil.readLines("y2022/day14.txt", Day14.Line::new);

        int actual = Day14.measureSand(data, true);

        assertThat(actual, equalTo(25161));
    }
}
