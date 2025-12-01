package dk.simonpip.aoc.y2021;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Day02Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2");

    @Test
    public void testFindDistanceTravelledExample() {
        long actual = Day02.findDistanceTravelled(EXAMPLE_DATA);

        assertThat(actual, equalTo(150L));
    }

    @Test
    public void testFindDistanceTravelledData() throws IOException {
        List<String> entries = FileUtil.readLines("y2021/day02.txt", Function.identity());

        long actual = Day02.findDistanceTravelled(entries);

        assertThat(actual, equalTo(1660158L));
    }

    @Test
    public void testFindDistanceTravelledByAimExample() {
        long actual = Day02.findDistanceTravelledByAim(EXAMPLE_DATA);

        assertThat(actual, equalTo(900L));
    }

    @Test
    public void testFindDistanceTravelledByAimData() throws IOException {
        List<String> entries = FileUtil.readLines("y2021/day02.txt", Function.identity());

        long actual = Day02.findDistanceTravelledByAim(entries);

        assertThat(actual, equalTo(1604592846L));
    }
}
