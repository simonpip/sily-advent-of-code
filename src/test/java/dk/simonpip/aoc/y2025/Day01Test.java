package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.FileUtil;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day01Test extends TestCase {
    public void testFirst_Example() throws IOException {
        List<Integer> rotations = FileUtil.readLines("y2025/day01_example.txt", Day01::convert);

        int actual = Day01.solveFirst(rotations);

        assertThat(actual, equalTo(3));
    }

    public void testFirst_TestModulus() throws IOException {
        List<Integer> rotations = FileUtil.readLines("y2025/day01_testModulus.txt", Day01::convert);

        int actual = Day01.solveFirst(rotations);

        assertThat(actual, equalTo(2));
    }

    public void testFirst() throws IOException {
        List<Integer> rotations = FileUtil.readLines("y2025/day01.txt", Day01::convert);

        int actual = Day01.solveFirst(rotations);

        assertThat(actual, equalTo(1089));
    }

    public void testSecond_Example() throws IOException {
        List<Integer> rotations = FileUtil.readLines("y2025/day01_example.txt", Day01::convert);

        int actual = Day01.solveSecond(rotations);

        assertThat(actual, equalTo(6));
    }

    public void testSecond_TestModulus() throws IOException {
        List<Integer> rotations = FileUtil.readLines("y2025/day01_testModulus.txt", Day01::convert);

        int actual = Day01.solveSecond(rotations);

        assertThat(actual, equalTo(6));
    }

    public void testSecond() throws IOException {
        List<Integer> rotations = FileUtil.readLines("y2025/day01.txt", Day01::convert);

        int actual = Day01.solveSecond(rotations);

        assertThat(actual, equalTo(6530));
    }
}