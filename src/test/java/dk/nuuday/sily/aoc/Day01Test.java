package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class Day01Test {
    private static final List<Integer> EXAMPLE_DATA = List.of(
            1721,
            979,
            366,
            299,
            675,
            1456);

    @Test
    public void testFixExpenseReportExample() {
        int actual = Day01.fixExpenseReport(EXAMPLE_DATA);

        assertThat(actual, equalTo(514579));
    }

    @Test
    public void testFixExpenseReportData() throws IOException {
        List<Integer> entries = FileUtil.readLines("day01.txt", Integer::parseInt);

        int actual = Day01.fixExpenseReport(entries);

        assertThat(actual, equalTo(468051));
    }

    @Test
    public void testFixExpenseReportPart2Example() {
        int actual = Day01.fixExpenseReportPart2(EXAMPLE_DATA);

        assertThat(actual, equalTo(241861950));
    }

    @Test
    public void testFixExpenseReportPart2Data() throws IOException {
        List<Integer> entries = FileUtil.readLines("day01.txt", Integer::parseInt);

        int actual = Day01.fixExpenseReportPart2(entries);

        assertThat(actual, equalTo(272611658));
    }
}