package dk.nuuday.sily.aoc.y2020;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Day09Test {
    private final List<Long> EXAMPLE_DATA = List.of(
            35L,
            20L,
            15L,
            25L,
            47L,
            40L,
            62L,
            55L,
            65L,
            95L,
            102L,
            117L,
            150L,
            182L,
            127L,
            219L,
            299L,
            277L,
            309L,
            576L);

    @Test
    public void testFindWeaknessExample() {
        long actual = Day09.findWeakness(EXAMPLE_DATA, 5);

        assertThat(actual, equalTo(127L));
    }

    @Test
    public void testFindWeaknessData() throws IOException {
        List<Long> code = FileUtil.readLines("y2020/day09.txt", Long::valueOf);

        long actual = Day09.findWeakness(code, 25);

        assertThat(actual, equalTo(2089807806L));
    }

    @Test
    public void testBreakEncryptionExample() {
        long actual = Day09.breakEncryption(EXAMPLE_DATA, 5);

        assertThat(actual, equalTo(62L));
    }

    @Test
    public void testBreakEncryptionData() throws IOException {
        List<Long> code = FileUtil.readLines("y2020/day09.txt", Long::valueOf);

        long actual = Day09.breakEncryption(code, 25);

        assertThat(actual, equalTo(245848639L));
    }
}