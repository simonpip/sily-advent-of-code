package dk.nuuday.sily.aoc.y2021;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Day01Test {
    private static final List<Integer> EXAMPLE_DATA = List.of(
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263);

    @Test
    public void testFindIncreaseInDepthExample() {
        long actual = Day01.findIncreaseInDepth(EXAMPLE_DATA);

        assertThat(actual, equalTo(7L));
    }

    @Test
    public void testFindIncreaseInDepthData() throws IOException {
        List<Integer> entries = FileUtil.readLines("y2021/day01.txt", Integer::valueOf);

        long actual = Day01.findIncreaseInDepth(entries);

        assertThat(actual, equalTo(1502L));
    }

    @Test
    public void testFindIncreaseInAverageDepthExample() {
        long actual = Day01.findIncreaseInAverageDepth(EXAMPLE_DATA);

        assertThat(actual, equalTo(5L));
    }

    @Test
    public void testFindIncreaseInAverageDepthData() throws IOException {
        List<Integer> entries = FileUtil.readLines("y2021/day01.txt", Integer::valueOf);

        long actual = Day01.findIncreaseInAverageDepth(entries);

        assertThat(actual, equalTo(1538L));
    }
}
