package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class Day19Test {
    private static final Day19.DataSet EXAMPLE_DATA = new Day19.DataSet(List.of(
            "0: 4 1 5",
            "1: 2 3 | 3 2",
            "2: 4 4 | 5 5",
            "3: 4 5 | 5 4",
            "4: \"a\"",
            "5: \"b\"",
            "",
            "ababbb",
            "bababa",
            "abbbab",
            "aaabbb",
            "aaaabbb"));

    @Test
    public void testCountValidElementsExample() {
        long actual = Day19.countValidElements(EXAMPLE_DATA);

        assertThat(actual, equalTo(2L));
    }

    @Test
    public void testCountValidElementsData() throws IOException {
        Day19.DataSet dataSet = FileUtil.readFile("day19.txt", Day19.DataSet::new);

        long actual = Day19.countValidElements(dataSet);

        assertThat(actual, equalTo(200L));
    }

    @Test
    public void testCountValidElementsLargeExample() throws IOException {
        Day19.DataSet dataSet = FileUtil.readFile("day19_large_example.txt", Day19.DataSet::new);

        long actual = Day19.countValidElements(dataSet);

        assertThat(actual, equalTo(3L));
    }

    @Test
    public void testCountValidElementsLargeExampleModified() throws IOException {
        Day19.DataSet dataSet = FileUtil.readFile("day19_large_example_modified.txt", Day19.DataSet::new);

        long actual = Day19.countValidElements(dataSet);

        assertThat(actual, equalTo(12L));
    }

    @Test
    public void testTestElementLargeExampleModified() throws IOException {
        Day19.DataSet dataSet = FileUtil.readFile("day19_large_example_modified.txt", Day19.DataSet::new);

        boolean actual = Day19.testElement(dataSet, "babbbbaabbbbbabbbbbbaabaaabaaa");

        assertThat(actual, equalTo(true));
    }

    @Test
    public void testCountValidElementsDataModified() throws IOException {
        Day19.DataSet dataSet = FileUtil.readFile("day19_modified.txt", Day19.DataSet::new);

        long actual = Day19.countValidElements(dataSet);

        assertThat(actual, equalTo(200L));
    }
}