package dk.nuuday.sily.aoc.y2022;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day08Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "30373",
            "25512",
            "65332",
            "33549",
            "35390");

    @Test
    public void testFindVisibleTreesExample() {
        long actual = Day08.findVisibleTrees(EXAMPLE_DATA);

        assertThat(actual, equalTo(21L));
    }

    @Test
    public void testFindVisibleTreesData() throws IOException {
        List<String> data = FileUtil.readLines("y2022/day08.txt");

        long actual = Day08.findVisibleTrees(data);

        assertThat(actual, equalTo(1703L));
    }

    @Test
    public void testFindTreeHouseCandidatesExample() {
        int actual = Day08.findTreeHouseCandidates(EXAMPLE_DATA);

        assertThat(actual, equalTo(8));
    }

    @Test
    public void testFindTreeHouseCandidatesData() throws IOException {
        List<String> data = FileUtil.readLines("y2022/day08.txt");

        int actual = Day08.findTreeHouseCandidates(data);

        assertThat(actual, equalTo(20));
    }
}
