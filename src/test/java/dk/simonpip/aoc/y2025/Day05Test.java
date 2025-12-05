package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.FileUtil;
import junit.framework.TestCase;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day05Test extends TestCase {
    public void testSolveFirst_Example() throws IOException {
        Day05.Ingredients ingredients = FileUtil.readFile("y2025/day05_example.txt", Day05.Ingredients::new);

        long actual = Day05.solveFirst(ingredients);

        assertThat(actual, equalTo(3L));
    }

    public void testSolveFirst() throws IOException {
        Day05.Ingredients ingredients = FileUtil.readFile("y2025/day05.txt", Day05.Ingredients::new);

        long actual = Day05.solveFirst(ingredients);

        assertThat(actual, equalTo(679L));
    }

    public void testSolveSecond_Example() throws IOException {
        Day05.Ingredients ingredients = FileUtil.readFile("y2025/day05_example.txt", Day05.Ingredients::new);

        long actual = Day05.solveSecond(ingredients);

        assertThat(actual, equalTo(14L));
    }

    public void testSolveSecond() throws IOException {
        Day05.Ingredients ingredients = FileUtil.readFile("y2025/day05.txt", Day05.Ingredients::new);

        long actual = Day05.solveSecond(ingredients);

        assertThat(actual, equalTo(358155203664116L));
    }
}