package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class Day08Test {
    private List<String> EXAMPLE_DATA = List.of(
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "jmp -4",
            "acc +6");

    @Test
    public void testRunProgramExample() {
        int actual = Day08.runProgram(EXAMPLE_DATA);

        assertThat(actual, equalTo(5));
    }

    @Test
    public void testRunProgramData() throws IOException {
        List<String> operations = FileUtil.readLines("day08.txt");

        int actual = Day08.runProgram(operations);

        assertThat(actual, equalTo(1814));
    }

    @Test
    public void testFixAndRunProgramExample() {
        int actual = Day08.fixAndRunProgram(EXAMPLE_DATA);

        assertThat(actual, equalTo(8));
    }

    @Test
    public void testFixAndRunProgramData() throws IOException {
        List<String> operations = FileUtil.readLines("day08.txt");

        int actual = Day08.fixAndRunProgram(operations);

        assertThat(actual, equalTo(1056));
    }
}