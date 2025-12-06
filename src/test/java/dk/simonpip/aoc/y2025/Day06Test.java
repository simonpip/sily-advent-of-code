package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.FileUtil;
import dk.simonpip.aoc.y2025.Day06.Problem;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day06Test extends TestCase {
    public void testSolveFirst_Example() throws IOException {
        List<Problem> problems = FileUtil.readFile("y2025/day06_example.txt", Day06::convertForFirst);

        long actual = Day06.solve(problems);

        assertThat(actual, equalTo(4277556L));
    }

    public void testSolveFirst() throws IOException {
        List<Problem> problems = FileUtil.readFile("y2025/day06.txt", Day06::convertForFirst);

        long actual = Day06.solve(problems);

        assertThat(actual, equalTo(6343365546996L));
    }

    public void testSolveSecond_Example() throws IOException {
        List<Problem> problems = FileUtil.readFile("y2025/day06_example.txt", Day06::convertForSecond, false);

        long actual = Day06.solve(problems);

        assertThat(actual, equalTo(3263827L));
    }

    public void testSolveSecond() throws IOException {
        List<Problem> problems = FileUtil.readFile("y2025/day06.txt", Day06::convertForSecond, false);

        long actual = Day06.solve(problems);

        assertThat(actual, equalTo(11136895955912L));
    }
}