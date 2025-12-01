package dk.simonpip.aoc.y2021;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Day03Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010");

    @Test
    public void testDiagnoseExample() {
        long actual = Day03.diagnose(EXAMPLE_DATA);

        assertThat(actual, equalTo(198L));
    }

    @Test
    public void testDiagnoseData() throws IOException {
        List<String> entries = FileUtil.readLines("y2021/day03.txt", Function.identity());

        long actual = Day03.diagnose(entries);

        assertThat(actual, equalTo(3549854L));
    }

    @Test
    public void testDiagnoseComplexExample() {
        long actual = Day03.diagnoseComplex(EXAMPLE_DATA);

        assertThat(actual, equalTo(230L));
    }

    @Test
    public void testDiagnoseComplexData() throws IOException {
        List<String> entries = FileUtil.readLines("y2021/day03.txt", Function.identity());

        long actual = Day03.diagnoseComplex(entries);

        assertThat(actual, equalTo(3765399L));
    }
}
