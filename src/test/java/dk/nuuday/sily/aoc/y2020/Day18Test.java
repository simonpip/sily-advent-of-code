package dk.nuuday.sily.aoc.y2020;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Day18Test {
    @Test
    public void testCalculateFunctionExample1() {
        long actual = Day18.calculateFunction("1 + 2 * 3 + 4 * 5 + 6");

        assertThat(actual, equalTo(71L));
    }

    @Test
    public void testCalculateFunctionExample2() {
        long actual = Day18.calculateFunction("1 + (2 * 3) + (4 * (5 + 6))");

        assertThat(actual, equalTo(51L));
    }

    @Test
    public void testCalculateFunctionExample3() {
        long actual = Day18.calculateFunction("2 * 3 + (4 * 5)");

        assertThat(actual, equalTo(26L));
    }

    @Test
    public void testCalculateFunctionExample4() {
        long actual = Day18.calculateFunction("5 + (8 * 3 + 9 + 3 * 4 * 3)");

        assertThat(actual, equalTo(437L));
    }

    @Test
    public void testCalculateFunctionExample5() {
        long actual = Day18.calculateFunction("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");

        assertThat(actual, equalTo(12240L));
    }

    @Test
    public void testCalculateFunctionExample6() {
        long actual = Day18.calculateFunction("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");

        assertThat(actual, equalTo(13632L));
    }

    @Test
    public void testSumResultsData() throws IOException {
        List<String> functions = FileUtil.readLines("y2020/day18.txt");

        long actual = Day18.sumResults(functions);

        assertThat(actual, equalTo(8929569623593L));
    }

    @Test
    public void testCalculateFunctionOrderedExample1() {
        long actual = Day18.calculateFunctionOrdered("1 + 2 * 3 + 4 * 5 + 6");

        assertThat(actual, equalTo(231L));
    }

    @Test
    public void testCalculateFunctionOrderedExample2() {
        long actual = Day18.calculateFunctionOrdered("1 + (2 * 3) + (4 * (5 + 6))");

        assertThat(actual, equalTo(51L));
    }

    @Test
    public void testCalculateFunctionOrderedExample3() {
        long actual = Day18.calculateFunctionOrdered("2 * 3 + (4 * 5)");

        assertThat(actual, equalTo(46L));
    }

    @Test
    public void testCalculateFunctionOrderedExample4() {
        long actual = Day18.calculateFunctionOrdered("5 + (8 * 3 + 9 + 3 * 4 * 3)");

        assertThat(actual, equalTo(1445L));
    }

    @Test
    public void testCalculateFunctionOrderedExample5() {
        long actual = Day18.calculateFunctionOrdered("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");

        assertThat(actual, equalTo(669060L));
    }

    @Test
    public void testCalculateFunctionOrderedExample6() {
        long actual = Day18.calculateFunctionOrdered("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");

        assertThat(actual, equalTo(23340L));
    }

    @Test
    public void testSumResultsDataOrdered() throws IOException {
        List<String> functions = FileUtil.readLines("y2020/day18.txt");

        long actual = Day18.sumResultsOrdered(functions);

        assertThat(actual, equalTo(231235959382961L));
    }
}