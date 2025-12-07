package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.FileUtil;
import junit.framework.TestCase;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day07Test extends TestCase {
    public void testSolveFirst_Example() throws IOException {
        Day07.Experiment experiment = FileUtil.readFile("y2025/day07_example.txt", Day07.Experiment::new);

        int actual = Day07.solveFirst(experiment);

        assertThat(actual, equalTo(21));
    }

    public void testSolveFirst() throws IOException {
        Day07.Experiment experiment = FileUtil.readFile("y2025/day07.txt", Day07.Experiment::new);

        int actual = Day07.solveFirst(experiment);

        assertThat(actual, equalTo(1516));
    }

    public void testSolveSecond_Example() throws IOException {
        Day07.Experiment experiment = FileUtil.readFile("y2025/day07_example.txt", Day07.Experiment::new);

        long actual = Day07.solveSecond(experiment);

        assertThat(actual, equalTo(40L));
    }

    public void testSolveSecond() throws IOException {
        Day07.Experiment experiment = FileUtil.readFile("y2025/day07.txt", Day07.Experiment::new);

        long actual = Day07.solveSecond(experiment);

        assertThat(actual, equalTo(1393669447690L));
    }
}