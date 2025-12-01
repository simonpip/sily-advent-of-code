package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day04Test {
    private final List<String> EXAMPLE_DATA = List.of(
            "2-4,6-8",
            "2-3,4-5",
            "5-7,7-9",
            "2-8,3-7",
            "6-6,4-6",
            "2-6,4-8");

    @Test
    public void testCountTotalOverlapsExample() {
        List<Day04.Pair> data = EXAMPLE_DATA.stream().map(Day04.Pair::new).collect(Collectors.toList());

        long actual = Day04.countTotalOverlaps(data);

        assertThat(actual, equalTo(2L));
    }

    @Test
    public void testCountTotalOverlapsData() throws IOException {
        List<Day04.Pair> data = FileUtil.readLines("y2022/day04.txt", Day04.Pair::new);

        long actual = Day04.countTotalOverlaps(data);

        assertThat(actual, equalTo(441L));
    }

    @Test
    public void testCountPartialOverlapsExample() {
        List<Day04.Pair> data = EXAMPLE_DATA.stream().map(Day04.Pair::new).collect(Collectors.toList());

        long actual = Day04.countPartialOverlaps(data);

        assertThat(actual, equalTo(4L));
    }

    @Test
    public void testCountPartialOverlapsData() throws IOException {
        List<Day04.Pair> data = FileUtil.readLines("y2022/day04.txt", Day04.Pair::new);

        long actual = Day04.countPartialOverlaps(data);

        assertThat(actual, equalTo(861L));
    }
}
