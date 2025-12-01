package dk.simonpip.aoc.y2023;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day05Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "seeds: 79 14 55 13",
            "",
            "seed-to-soil map:",
            "50 98 2",
            "52 50 48",
            "",
            "soil-to-fertilizer map:",
            "0 15 37",
            "37 52 2",
            "39 0 15",
            "",
            "fertilizer-to-water map:",
            "49 53 8",
            "0 11 42",
            "42 0 7",
            "57 7 4",
            "",
            "water-to-light map:",
            "88 18 7",
            "18 25 70",
            "",
            "light-to-temperature map:",
            "45 77 23",
            "81 45 19",
            "68 64 13",
            "",
            "temperature-to-humidity map:",
            "0 69 1",
            "1 0 69",
            "",
            "humidity-to-location map:",
            "60 56 37",
            "56 93 4"
    );

    @Test
    public void testFindLowestLocationExample() {
        Day05.SeedMap value = new Day05.SeedMap(EXAMPLE_DATA);

        long actual = Day05.findLowestLocation(value);

        assertThat(actual, equalTo(35L));
    }

    @Test
    public void testFindLowestLocationData() throws IOException {
        Day05.SeedMap value = FileUtil.readFile("y2023/day05.txt", Day05.SeedMap::new);

        long actual = Day05.findLowestLocation(value);

        assertThat(actual, equalTo(251346198L));
    }

    @Test
    public void testFindLowestLocationByRangeExample() {
        Day05.SeedMap value = new Day05.SeedMap(EXAMPLE_DATA);

        long actual = Day05.findLowestLocationByRange(value);

        assertThat(actual, equalTo(46L));
    }

    @Test
    public void testFindLowestLocationByRangeData() throws IOException {
        Day05.SeedMap value = FileUtil.readFile("y2023/day05.txt", Day05.SeedMap::new);

        long actual = Day05.findLowestLocationByRange(value);

        assertThat(actual, equalTo(72263011L));
    }
}
