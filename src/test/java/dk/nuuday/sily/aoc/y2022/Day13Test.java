package dk.nuuday.sily.aoc.y2022;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day13Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "[1,1,3,1,1]",
            "[1,1,5,1,1]",
            "",
            "[[1],[2,3,4]]",
            "[[1],4]",
            "",
            "[9]",
            "[[8,7,6]]",
            "",
            "[[4,4],4,4]",
            "[[4,4],4,4,4]",
            "",
            "[7,7,7,7]",
            "[7,7,7]",
            "",
            "[]",
            "[3]",
            "",
            "[[[]]]",
            "[[]]",
            "",
            "[1,[2,[3,[4,[5,6,7]]]],8,9]",
            "[1,[2,[3,[4,[5,6,0]]]],8,9]");

    @Test
    public void testCountOrderedPairsExample() {
        int actual = Day13.countOrderedPacketPairs(EXAMPLE_DATA);

        assertThat(actual, equalTo(13));
    }

    @Test
    public void testCountOrderedPairsData() throws IOException {
        List<String> data = FileUtil.readLines("y2022/day13.txt");

        int actual = Day13.countOrderedPacketPairs(data);

        assertThat(actual, equalTo(5675));
    }

    @Test
    public void testOrderPacketPairsExample() {
        int actual = Day13.orderPacketPairs(EXAMPLE_DATA);

        assertThat(actual, equalTo(140));
    }

    @Test
    public void testOrderPacketPairsData() throws IOException {
        List<String> data = FileUtil.readLines("y2022/day13.txt");

        int actual = Day13.orderPacketPairs(data);

        assertThat(actual, equalTo(20383));
    }
}
