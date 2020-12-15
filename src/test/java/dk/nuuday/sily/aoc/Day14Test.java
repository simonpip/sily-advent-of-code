package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Day14Test {
    private static final Day14.Program EXAMPLE_DATA = new Day14.Program(List.of(
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11",
            "mem[7] = 101",
            "mem[8] = 0"));

    private static final Day14.Program EXAMPLE_DATA_2 = new Day14.Program(List.of(
            "mask = 000000000000000000000000000000X1001X",
            "mem[42] = 100",
            "mask = 00000000000000000000000000000000X0XX",
            "mem[26] = 1"));

    @Test
    public void testSumMaskedNumbersExample() {
        long actual = Day14.sumMaskedNumbers(EXAMPLE_DATA);

        assertThat(actual, equalTo(165L));
    }

    @Test
    public void testSumMaskedNumbersData() throws IOException {
        Day14.Program program = FileUtil.readFile("day14.txt", Day14.Program::new);

        long actual = Day14.sumMaskedNumbers(program);

        assertThat(actual, equalTo(8566770985168L));
    }

    @Test
    public void testSumMaskedAddressesExample() {
        long actual = Day14.sumMaskedAddresses(EXAMPLE_DATA_2);

        assertThat(actual, equalTo(208L));
    }

    @Test
    public void testSumMaskedAddressesData() throws IOException {
        Day14.Program program = FileUtil.readFile("day14.txt", Day14.Program::new);

        long actual = Day14.sumMaskedAddresses(program);

        assertThat(actual, equalTo(4832039794082L));
    }
}