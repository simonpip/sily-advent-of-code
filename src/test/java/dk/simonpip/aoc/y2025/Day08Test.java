package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.FileUtil;
import dk.simonpip.aoc.util.Coordinate3D;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day08Test extends TestCase {
    public void testSolveFirst_Example() throws IOException {
        List<Coordinate3D> junctionBoxes = FileUtil.readLines("y2025/day08_example.txt", Coordinate3D::new);

        long actual = Day08.solveFirst(junctionBoxes, 10);

        assertThat(actual, equalTo(40L));
    }

    public void testSolveFirst() throws IOException {
        List<Coordinate3D> junctionBoxes = FileUtil.readLines("y2025/day08.txt", Coordinate3D::new);

        long actual = Day08.solveFirst(junctionBoxes, 1000);

        assertThat(actual, equalTo(98696L));
    }

    public void testSolveSecond_Example() throws IOException {
        List<Coordinate3D> junctionBoxes = FileUtil.readLines("y2025/day08_example.txt", Coordinate3D::new);

        long actual = Day08.solveSecond(junctionBoxes);

        assertThat(actual, equalTo(25272L));
    }

    public void testSolveSecond() throws IOException {
        List<Coordinate3D> junctionBoxes = FileUtil.readLines("y2025/day08.txt", Coordinate3D::new);

        long actual = Day08.solveSecond(junctionBoxes);

        assertThat(actual, equalTo(2245203960L));
    }
}