package dk.nuuday.sily.aoc.y2023;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day06Test {
    private static final List<Day06.Race> EXAMPLE_DATA_1 = List.of(
            new Day06.Race(7, 9),
            new Day06.Race(15, 40),
            new Day06.Race(30, 200)
    );

    private static final List<Day06.Race> DATA_1 = List.of(
            new Day06.Race(46, 214),
            new Day06.Race(80, 1177),
            new Day06.Race(78, 1402),
            new Day06.Race(66, 1024)
    );

    private static final List<Day06.Race> EXAMPLE_DATA_2 = List.of(
            new Day06.Race(71530, 940200)
    );

    private static final List<Day06.Race> DATA_2 = List.of(
            new Day06.Race(46807866, 214117714021024L)
    );

    @Test
    public void testCalculateMarginExample1() {
        int actual = Day06.calculateMargin(EXAMPLE_DATA_1);

        assertThat(actual, equalTo(288));
    }

    @Test
    public void testCalculateMarginData1() {
        int actual = Day06.calculateMargin(DATA_1);

        assertThat(actual, equalTo(512295));
    }

    @Test
    public void testCalculateMarginExample2() {
        int actual = Day06.calculateMargin(EXAMPLE_DATA_2);

        assertThat(actual, equalTo(71503));
    }

    @Test
    public void testCalculateMarginData2() {
        int actual = Day06.calculateMargin(DATA_2);

        assertThat(actual, equalTo(36530883));
    }
}
