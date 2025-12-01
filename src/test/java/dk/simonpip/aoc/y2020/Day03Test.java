package dk.simonpip.aoc.y2020;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Day03Test {
    private static final Day03.Landscape EXAMPLE_DATA = new Day03.Landscape(List.of(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"));

    @Test
    public void testCountTreesExample() {
        long actual = Day03.countTrees(EXAMPLE_DATA);

        assertThat(actual, equalTo(7L));
    }

    @Test
    public void testCountTreesData() throws IOException {
        Day03.Landscape data = FileUtil.readFile("y2020/day03.txt", Day03.Landscape::new);

        long actual = Day03.countTrees(data);

        assertThat(actual, equalTo(294L));
    }

    @Test
    public void testCountTreesMultipleExample() {
        long actual = Day03.countTreesMultiple(EXAMPLE_DATA);

        assertThat(actual, equalTo(336L));
    }

    @Test
    public void testCountTreesMultipleData() throws IOException {
        Day03.Landscape data = FileUtil.readFile("y2020/day03.txt", Day03.Landscape::new);

        long actual = Day03.countTreesMultiple(data);

        assertThat(actual, equalTo(5774564250L));
    }
}