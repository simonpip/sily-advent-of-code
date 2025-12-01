package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day15Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
            "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
            "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
            "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
            "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
            "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
            "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
            "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
            "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
            "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
            "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
            "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
            "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
            "Sensor at x=20, y=1: closest beacon is at x=15, y=3");

    @Test
    public void testCountNoBeaconPointsExample() {
        List<Day15.Reading> data = EXAMPLE_DATA.stream().map(Day15.Reading::new).collect(Collectors.toList());

        int actual = Day15.countNoBeaconPoints(data, 10);

        assertThat(actual, equalTo(26));
    }

    @Test
    public void testCountNoBeaconPointsData() throws IOException {
        List<Day15.Reading> data = FileUtil.readLines("y2022/day15.txt", Day15.Reading::new);

        int actual = Day15.countNoBeaconPoints(data, 2000000);

        assertThat(actual, equalTo(5299855));
    }

    @Test
    public void testFindHiddenBeaconExample() {
        List<Day15.Reading> data = EXAMPLE_DATA.stream().map(Day15.Reading::new).collect(Collectors.toList());

        long actual = Day15.findHiddenBeacon(data, 20);

        assertThat(actual, equalTo(56000011L));
    }

    @Test
    public void testFindHiddenBeaconData() throws IOException {
        List<Day15.Reading> data = FileUtil.readLines("y2022/day15.txt", Day15.Reading::new);

        long actual = Day15.findHiddenBeacon(data, 4000000);

        assertThat(actual, equalTo(13615843289729L));
    }
}
