package dk.simonpip.aoc.y2023;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day01Test {
    private static final List<String> EXAMPLE_DATA_1 = List.of(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet"
    );

    private static final List<String> EXAMPLE_DATA_2 = List.of(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen"
    );

    @Test
    public void testFindCalibrationValuesExample() {
        int actual = Day01.findCalibrationValues(EXAMPLE_DATA_1);

        assertThat(actual, equalTo(142));
    }

    @Test
    public void testFindCalibrationValuesData() throws IOException {
        List<String> data = FileUtil.readLines("y2023/day01.txt");

        int actual = Day01.findCalibrationValues(data);

        assertThat(actual, equalTo(56049));
    }

    @Test
    public void testFindCalibrationValuesExtendedExample() {
        int actual = Day01.findCalibrationValuesExtended(EXAMPLE_DATA_2);

        assertThat(actual, equalTo(281));
    }

    @Test
    public void testFindCalibrationValuesExtendedData() throws IOException {
        List<String> data = FileUtil.readLines("y2023/day01.txt");

        int actual = Day01.findCalibrationValuesExtended(data);

        assertThat(actual, equalTo(54530));
    }
}
