package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class Day05Test {
    private static final List<Day05.BoardingPass> EXAMPLE_DATA = List.of(
            new Day05.BoardingPass("BFFFBBFRRR"), //  row 70, column 7, seat ID 567
            new Day05.BoardingPass("FFFBBBFRRR"), //  row 14, column 7, seat ID 119
            new Day05.BoardingPass("BBFFBBFRLL")); // row 102, column 4, seat ID 820

    @Test
    public void testFindHighestTakenSeatExample() {
        int actual = Day05.findHighestTakenSeat(EXAMPLE_DATA);

        assertThat(actual, equalTo(820));
    }

    @Test
    public void testFindHighestTakenSeatData() throws IOException {
        List<Day05.BoardingPass> boardingPasses = FileUtil.readLines("day05.txt", Day05.BoardingPass::new);

        int actual = Day05.findHighestTakenSeat(boardingPasses);

        assertThat(actual, equalTo(848));
    }

    @Test
    public void testFindUnfilledSeatData() throws IOException {
        List<Day05.BoardingPass> boardingPasses = FileUtil.readLines("day05.txt", Day05.BoardingPass::new);

        int actual = Day05.findUnfilledSeat(boardingPasses);

        assertThat(actual, equalTo(682));
    }
}