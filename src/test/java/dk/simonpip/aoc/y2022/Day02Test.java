package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day02Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "A Y",
            "B X",
            "C Z");

    @Test
    public void testCalculateScoreExample() {
        List<Day02.Round> rounds = EXAMPLE_DATA.stream()
                .map(e -> new Day02.Round(e, false))
                .collect(Collectors.toList());

        int actual = Day02.calculateScore(rounds);

        assertThat(actual, equalTo(15));
    }

    @Test
    public void testCalculateScoreData() throws IOException {
        List<Day02.Round> rounds = FileUtil.readLines(
                "y2022/day02.txt",
                e -> new Day02.Round(e, false));

        int actual = Day02.calculateScore(rounds);

        assertThat(actual, equalTo(15632));
    }
    @Test
    public void testCalculateScoreAdvancedExample() {
        List<Day02.Round> rounds = EXAMPLE_DATA.stream()
                .map(e -> new Day02.Round(e, true))
                .collect(Collectors.toList());

        int actual = Day02.calculateScore(rounds);

        assertThat(actual, equalTo(12));
    }

    @Test
    public void testCalculateScoreAdvancedData() throws IOException {
        List<Day02.Round> rounds = FileUtil.readLines(
                "y2022/day02.txt",
                e -> new Day02.Round(e, true));

        int actual = Day02.calculateScore(rounds);

        assertThat(actual, equalTo(14416));
    }
}
