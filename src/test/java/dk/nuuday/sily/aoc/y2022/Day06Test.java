package dk.nuuday.sily.aoc.y2022;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day06Test {
    private static final int PACKET_MARKER_LENGTH = 4;
    private static final int MESSAGE_MARKER_LENGTH = 14;

    @Test
    public void testIndexOfMarkerPacketExample1() {
        int actual = Day06.indexOfMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb", PACKET_MARKER_LENGTH);

        assertThat(actual, equalTo(7));
    }

    @Test
    public void testIndexOfMarkerPacketExample2() {
        int actual = Day06.indexOfMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", PACKET_MARKER_LENGTH);

        assertThat(actual, equalTo(5));
    }

    @Test
    public void testIndexOfMarkerPacketExample3() {
        int actual = Day06.indexOfMarker("nppdvjthqldpwncqszvftbrmjlhg", PACKET_MARKER_LENGTH);

        assertThat(actual, equalTo(6));
    }

    @Test
    public void testIndexOfMarkerPacketExample4() {
        int actual = Day06.indexOfMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", PACKET_MARKER_LENGTH);

        assertThat(actual, equalTo(10));
    }

    @Test
    public void testIndexOfMarkerPacketExample5() {
        int actual = Day06.indexOfMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", PACKET_MARKER_LENGTH);

        assertThat(actual, equalTo(11));
    }

    @Test
    public void testIndexOfMarkerPacketData() throws IOException {
        String data = FileUtil.readFile("y2022/day06.txt", e -> e.get(0));

        int actual = Day06.indexOfMarker(data, PACKET_MARKER_LENGTH);

        assertThat(actual, equalTo(1965));
    }

    @Test
    public void testIndexOfMarkerMessageExample1() {
        int actual = Day06.indexOfMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb", MESSAGE_MARKER_LENGTH);

        assertThat(actual, equalTo(19));
    }

    @Test
    public void testIndexOfMarkerMessageExample2() {
        int actual = Day06.indexOfMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", MESSAGE_MARKER_LENGTH);

        assertThat(actual, equalTo(23));
    }

    @Test
    public void testIndexOfMarkerMessageExample3() {
        int actual = Day06.indexOfMarker("nppdvjthqldpwncqszvftbrmjlhg", MESSAGE_MARKER_LENGTH);

        assertThat(actual, equalTo(23));
    }

    @Test
    public void testIndexOfMarkerMessageExample4() {
        int actual = Day06.indexOfMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", MESSAGE_MARKER_LENGTH);

        assertThat(actual, equalTo(29));
    }

    @Test
    public void testIndexOfMarkerMessageExample5() {
        int actual = Day06.indexOfMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", MESSAGE_MARKER_LENGTH);

        assertThat(actual, equalTo(26));
    }

    @Test
    public void testIndexOfMarkerMessageData() throws IOException {
        String data = FileUtil.readFile("y2022/day06.txt", e -> e.get(0));

        int actual = Day06.indexOfMarker(data, MESSAGE_MARKER_LENGTH);

        assertThat(actual, equalTo(2773));
    }
}
