package dk.nuuday.sily.aoc.y2022;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day05Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "    [D]",
            "[N] [C]",
            "[Z] [M] [P]",
            " 1   2   3",
            "",
            "move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2");

    @Test
    public void testGetTopCratesExample() {
        Day05.WorkOrder data = new Day05.WorkOrder(EXAMPLE_DATA);

        String actual = Day05.getTopCrates(data, false);

        assertThat(actual, equalTo("CMZ"));
    }

    @Test
    public void testGetTopCratesData() throws IOException {
        Day05.WorkOrder data = FileUtil.readFile("y2022/day05.txt", Day05.WorkOrder::new);

        String actual = Day05.getTopCrates(data, false);

        assertThat(actual, equalTo("GRTSWNJHH"));
    }

    @Test
    public void testGetTopCratesAdvancedExample() {
        Day05.WorkOrder data = new Day05.WorkOrder(EXAMPLE_DATA);

        String actual = Day05.getTopCrates(data, true);

        assertThat(actual, equalTo("MCD"));
    }

    @Test
    public void testGetTopCratesAdvancedData() throws IOException {
        Day05.WorkOrder data = FileUtil.readFile("y2022/day05.txt", Day05.WorkOrder::new);

        String actual = Day05.getTopCrates(data, true);

        assertThat(actual, equalTo("QLFQDBBHM"));
    }
}
