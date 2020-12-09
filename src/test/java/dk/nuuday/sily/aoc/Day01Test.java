package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class Day01Test {
    private static final List<Long> EXAMPLE_DATA = List.of(
            1721L,
            979L,
            366L,
            299L,
            675L,
            1456L);

    @Test
    public void testFixExpenseReportExample() {
        long actual = Day01.fixExpenseReport(EXAMPLE_DATA);

        assertThat(actual, equalTo(514579L));
    }

    @Test
    public void testFixExpenseReportData() throws IOException {
        List<Long> entries = FileUtil.readLines("day01.txt", Long::valueOf);

        long actual = Day01.fixExpenseReport(entries);

        assertThat(actual, equalTo(468051L));
    }

    @Test
    public void testFixExpenseReportPart2Example() {
        long actual = Day01.fixExpenseReportPart2(EXAMPLE_DATA);

        assertThat(actual, equalTo(241861950L));
    }

    @Test
    public void testFixExpenseReportPart2Data() throws IOException {
        List<Long> entries = FileUtil.readLines("day01.txt", Long::valueOf);

        long actual = Day01.fixExpenseReportPart2(entries);

        assertThat(actual, equalTo(272611658L));
    }
}