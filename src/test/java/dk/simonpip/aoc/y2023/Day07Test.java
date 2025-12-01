package dk.simonpip.aoc.y2023;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day07Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483"
    );

    @Test
    public void testCalculateWinningsExample() {
        List<Day07.Hand> value = EXAMPLE_DATA.stream().map(Day07.Hand::new).collect(Collectors.toList());

        int actual = Day07.calculateWinnings(value, false);

        assertThat(actual, equalTo(6440));
    }

    @Test
    public void testCalculateWinningsData() throws IOException {
        List<Day07.Hand> value = FileUtil.readLines(
                "y2023/day07.txt",
                Day07.Hand::new);

        int actual = Day07.calculateWinnings(value, false);

        assertThat(actual, equalTo(251029473));
    }

    @Test
    public void testCalculateWinningsWithJokerExample() {
        List<Day07.Hand> value = EXAMPLE_DATA.stream().map(Day07.Hand::new).collect(Collectors.toList());

        int actual = Day07.calculateWinnings(value, true);

        assertThat(actual, equalTo(5905));
    }

    @Test
    public void testCalculateWinningsWithJokerData() throws IOException {
        List<Day07.Hand> value = FileUtil.readLines(
                "y2023/day07.txt",
                Day07.Hand::new);

        int actual = Day07.calculateWinnings(value, true);

        assertThat(actual, equalTo(251003917));
    }
}
