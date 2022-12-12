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
        Day08.Tree[][] data = Day08.createTreeArray(EXAMPLE_DATA);

        long actual = Day08.findVisibleTrees(data);

        assertThat(actual, equalTo(21L));
    }

    @Test
    public void testFindVisibleTreesData() throws IOException {
        Day08.Tree[][] data = FileUtil.readFile("y2022/day08.txt", Day08::createTreeArray);

        long actual = Day08.findVisibleTrees(data);

        assertThat(actual, equalTo(1703L));
    }

    @Test
    public void testFindTreeHouseCandidatesExample() {
        Day08.Tree[][] data = Day08.createTreeArray(EXAMPLE_DATA);

        int actual = Day08.findTreeHouseCandidates(data);

        assertThat(actual, equalTo(8));
    }

    @Test
    public void testFindTreeHouseCandidatesData() throws IOException {
        Day08.Tree[][] data = FileUtil.readFile("y2022/day08.txt", Day08::createTreeArray);

        int actual = Day08.findTreeHouseCandidates(data);

        assertThat(actual, equalTo(496650));
    }
}
