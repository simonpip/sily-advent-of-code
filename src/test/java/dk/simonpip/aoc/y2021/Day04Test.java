package dk.simonpip.aoc.y2021;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Day04Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
            "",
            "22 13 17 11  0",
            " 8  2 23  4 24",
            "21  9 14 16  7",
            " 6 10  3 18  5",
            " 1 12 20 15 19",
            "",
            " 3 15  0  2 22",
            " 9 18 13 17  5",
            "19  8  7 25 23",
            "20 11 10 24  4",
            "14 21 16 12  6",
            "",
            "14 21 17 24  4",
            "10 16 15  9 19",
            "18  8 23 26 20",
            "22 11 13  6  5",
            " 2  0 12  3  7");

    @Test
    public void testFindWinningBoardExample() {
        int actual = Day04.findWinningBoard(EXAMPLE_DATA);

        assertThat(actual, equalTo(4512));
    }

    @Test
    public void testFindWinningBoardData() throws IOException {
        List<String> entries = FileUtil.readLines("y2021/day04.txt", Function.identity());

        int actual = Day04.findWinningBoard(entries);

        assertThat(actual, equalTo(23177));
    }

    @Test
    public void testFindLastWinningBoardExample() {
        int actual = Day04.findLastWinningBoard(EXAMPLE_DATA);

        assertThat(actual, equalTo(1924));
    }

    @Test
    public void testFindLastWinningBoardData() throws IOException {
        List<String> entries = FileUtil.readLines("y2021/day04.txt", Function.identity());

        int actual = Day04.findLastWinningBoard(entries);

        assertThat(actual, equalTo(6804));
    }
}
