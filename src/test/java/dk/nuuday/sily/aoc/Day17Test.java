package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

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
        Day17.CellSet cellSet = FileUtil.readFile("day17.txt", e -> new Day17.CellSet(e, false));

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
        Day17.CellSet cellSet = FileUtil.readFile("day17.txt", e -> new Day17.CellSet(e, true));

        int actual = Day17.runGameOfLife(cellSet, 6);

        assertThat(actual, equalTo(2240));
    }
}