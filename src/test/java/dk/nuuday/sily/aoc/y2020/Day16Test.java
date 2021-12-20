package dk.nuuday.sily.aoc.y2020;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Day16Test {
    private static final Day16.DataSet EXAMPLE_DATA = new Day16.DataSet(List.of(
            "class: 1-3 or 5-7",
            "row: 6-11 or 33-44",
            "seat: 13-40 or 45-50",
            "",
            "your ticket:",
            "7,1,14",
            "",
            "nearby tickets:",
            "7,3,47",
            "40,4,50",
            "55,2,20",
            "38,6,12"));

    private static final Day16.DataSet EXAMPLE_DATA_2 = new Day16.DataSet(List.of(
            "class: 0-1 or 4-19",
            "row: 0-5 or 8-19",
            "seat: 0-13 or 16-19",
            "",
            "your ticket:",
            "11,12,13",
            "",
            "nearby tickets:",
            "3,9,18",
            "15,1,5",
            "5,14,9"));

    @Test
    public void testFindErrorRateExample() {
        int actual = Day16.findErrorRate(EXAMPLE_DATA);

        assertThat(actual, equalTo(71));
    }

    @Test
    public void testFindErrorRateData() throws IOException {
        Day16.DataSet dataSet = FileUtil.readFile("y2020/day16.txt", Day16.DataSet::new);

        int actual = Day16.findErrorRate(dataSet);

        assertThat(actual, equalTo(23044));
    }

    @Test
    public void testFindProductOfMatchingFieldsExample() {
        long actual = Day16.findProductOfMatchingFields(EXAMPLE_DATA, "s");

        assertThat(actual, equalTo(14L));
    }

    @Test
    public void testFindProductOfMatchingFieldsExample2() {
        long actual = Day16.findProductOfMatchingFields(EXAMPLE_DATA_2, "s");

        assertThat(actual, equalTo(156L));
    }

    @Test
    public void testFindProductOfMatchingFieldsData() throws IOException {
        Day16.DataSet dataSet = FileUtil.readFile("y2020/day16.txt", Day16.DataSet::new);

        long actual = Day16.findProductOfMatchingFields(dataSet, "departure");

        assertThat(actual, equalTo(3765150732757L));
    }
}