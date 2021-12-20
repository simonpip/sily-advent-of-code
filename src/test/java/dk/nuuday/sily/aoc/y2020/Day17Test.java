package dk.nuuday.sily.aoc.y2020;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Day17Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            ".#.",
            "..#",
            "###");

    @Test
    public void testRunGameOfLifeExample() {
        Day17.CellSet cellSet = new Day17.CellSet(EXAMPLE_DATA, false);

        int actual = Day17.runGameOfLife(cellSet, 6);

        assertThat(actual, equalTo(112));
    }

    @Test
    public void testRunGameOfLifeData() throws IOException {
        Day17.CellSet cellSet = FileUtil.readFile("y2020/day17.txt", e -> new Day17.CellSet(e, false));

        int actual = Day17.runGameOfLife(cellSet, 6);

        assertThat(actual, equalTo(284));
    }

    @Test
    public void testRunGameOfLife4DExample() {
        Day17.CellSet cellSet = new Day17.CellSet(EXAMPLE_DATA, true);

        int actual = Day17.runGameOfLife(cellSet, 6);

        assertThat(actual, equalTo(848));
    }

    @Test
    public void testRunGameOfLife4DData() throws IOException {
        Day17.CellSet cellSet = FileUtil.readFile("y2020/day17.txt", e -> new Day17.CellSet(e, true));

        int actual = Day17.runGameOfLife(cellSet, 6);

        assertThat(actual, equalTo(2240));
    }
}