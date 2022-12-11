package dk.nuuday.sily.aoc.y2022;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day11Test {
    @Test
    public void testCalculateProductOfInspectCountExample() throws IOException {
        List<Day11.Monkey> data = FileUtil.readFile("y2022/day11example.txt", Day11::convertToMonkeys);

        long actual = Day11.calculateProductOfInspectCount(data, 20, false);

        assertThat(actual, equalTo(10605L));
    }

    @Test
    public void testCalculateProductOfInspectCountData() throws IOException {
        List<Day11.Monkey> data = FileUtil.readFile("y2022/day11.txt", Day11::convertToMonkeys);

        long actual = Day11.calculateProductOfInspectCount(data, 20, false);

        assertThat(actual, equalTo(108240L));
    }
    @Test
    public void testCalculateProductOfInspectCountLongExample() throws IOException {
        List<Day11.Monkey> data = FileUtil.readFile("y2022/day11example.txt", Day11::convertToMonkeys);

        long actual = Day11.calculateProductOfInspectCount(data, 10000, true);

        assertThat(actual, equalTo(2713310158L));
    }

    @Test
    public void testCalculateProductOfInspectCountLongData() throws IOException {
        List<Day11.Monkey> data = FileUtil.readFile("y2022/day11.txt", Day11::convertToMonkeys);

        long actual = Day11.calculateProductOfInspectCount(data, 10000, true);

        assertThat(actual, equalTo(25712998901L));
    }
}
