package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.FileUtil;
import dk.nuuday.sily.aoc.util.LineUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class Day06Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "abc",
            "",
            "a",
            "b",
            "c",
            "",
            "ab",
            "ac",
            "",
            "a",
            "a",
            "a",
            "a",
            "",
            "b");

    @Test
    public void testCountUniqueAnswersPerGroupExample() {
        int actual = Day06.countUniqueAnswersPerGroup(EXAMPLE_DATA);

        assertThat(actual, equalTo(11));
    }

    @Test
    public void testCountUniqueAnswersPerGroupData() throws IOException {
        List<String> groupAnswers = FileUtil.readLines("day06.txt");

        int actual = Day06.countUniqueAnswersPerGroup(groupAnswers);

        assertThat(actual, equalTo(6532));
    }

    @Test
    public void testCountCommonAnswersPerGroupExample() {
        long actual = Day06.countCommonAnswersPerGroup(EXAMPLE_DATA);

        assertThat(actual, equalTo(6L));
    }

    @Test
    public void testCountCommonAnswersPerGroupData() throws IOException {
        List<String> groupAnswers = FileUtil.readLines("day06.txt");

        long actual = Day06.countCommonAnswersPerGroup(groupAnswers);

        assertThat(actual, equalTo(3427L));
    }
}