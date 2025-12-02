package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.FileUtil;
import dk.simonpip.aoc.util.Pair;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day02Test extends TestCase {
    public void testFirst_Example() throws IOException {
        List<Pair<Long, Long>> pairs = FileUtil.readFile("y2025/day02_example.txt", Day02::convert);

        long actual = Day02.solveFirst(pairs);

        assertThat(actual, equalTo(1227775554L));
    }

    public void testFirst() throws IOException {
        List<Pair<Long, Long>> pairs = FileUtil.readFile("y2025/day02.txt", Day02::convert);

        long actual = Day02.solveFirst(pairs);

        assertThat(actual, equalTo(31000881061L));
    }

    public void testSecond_Example() throws IOException {
        List<Pair<Long, Long>> pairs = FileUtil.readFile("y2025/day02_example.txt", Day02::convert);

        long actual = Day02.solveSecond(pairs);

        assertThat(actual, equalTo(4174379265L));
    }

    public void testSecond() throws IOException {
        List<Pair<Long, Long>> pairs = FileUtil.readFile("y2025/day02.txt", Day02::convert);

        long actual = Day02.solveSecond(pairs);

        assertThat(actual, equalTo(46769308485L));
    }
}
