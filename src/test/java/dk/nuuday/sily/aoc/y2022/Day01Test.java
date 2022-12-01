package dk.nuuday.sily.aoc.y2022;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day01Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "1000",
            "2000",
            "3000",
            "",
            "4000",
            "",
            "5000",
            "6000",
            "",
            "7000",
            "8000",
            "9000",
            "",
            "10000");

    @Test
    public void testFindHighestCaloriesExample() {
        int actual = Day01.findHighestCalories(EXAMPLE_DATA, 1);

        assertThat(actual, equalTo(24000));
    }

    @Test
    public void testFindHighestCaloriesData() throws IOException {
        List<String> data = FileUtil.readLines("y2022/day01.txt");

        int actual = Day01.findHighestCalories(data, 1);

        assertThat(actual, equalTo(70369));
    }

    @Test
    public void testFindHighestCaloriesTop3Example() {
        int actual = Day01.findHighestCalories(EXAMPLE_DATA, 3);

        assertThat(actual, equalTo(45000));
    }

    @Test
    public void testFindHighestCaloriesTop3Data() throws IOException {
        List<String> data = FileUtil.readLines("y2022/day01.txt");

        int actual = Day01.findHighestCalories(data, 3);

        assertThat(actual, equalTo(203002));
    }
}
