package dk.nuuday.sily.aoc.y2020;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Day11Test {
    private static final Day11.Seating EXAMPLE_DATA = new Day11.Seating(List.of(
            "L.LL.LL.LL",
            "LLLLLLL.LL",
            "L.L.L..L..",
            "LLLL.LL.LL",
            "L.LL.LL.LL",
            "L.LLLLL.LL",
            "..L.L.....",
            "LLLLLLLLLL",
            "L.LLLLLL.L",
            "L.LLLLL.LL" ));

    @Test
    public void testCountOccupiedSeatsExample() {
        int actual = Day11.countOccupiedSeats(EXAMPLE_DATA);

        assertThat(actual, equalTo(37));
    }

    @Test
    public void testCountOccupiedSeatsData() throws IOException {
        Day11.Seating seating = FileUtil.readFile("y2020/day11.txt", Day11.Seating::new);

        int actual = Day11.countOccupiedSeats(seating);

        assertThat(actual, equalTo(2334));
    }

    @Test
    public void testCountOccupiedSeatsLongRangeExample() {
        int actual = Day11.countOccupiedSeatsLongRange(EXAMPLE_DATA);

        assertThat(actual, equalTo(26));
    }

    @Test
    public void testCountOccupiedSeatsLongRangeData() throws IOException {
        Day11.Seating seating = FileUtil.readFile("y2020/day11.txt", Day11.Seating::new);

        int actual = Day11.countOccupiedSeatsLongRange(seating);

        assertThat(actual, equalTo(2100));
    }
}