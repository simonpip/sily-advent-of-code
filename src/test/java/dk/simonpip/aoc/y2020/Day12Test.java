package dk.simonpip.aoc.y2020;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Day12Test {
    private static final List<Day12.Instruction> EXAMPLE_DATA = List.of(
            new Day12.Instruction("F10"),
            new Day12.Instruction("N3"),
            new Day12.Instruction("F7"),
            new Day12.Instruction("R90"),
            new Day12.Instruction("F11"));

    @Test
    public void testCalculateLocationExample() {
        int actual = Day12.calculateLocation(EXAMPLE_DATA);

        assertThat(actual, equalTo(25));
    }

    @Test
    public void testCalculateLocationData() throws IOException {
        List<Day12.Instruction> instructions = FileUtil.readLines("y2020/day12.txt", Day12.Instruction::new);

        int actual = Day12.calculateLocation(instructions);

        assertThat(actual, equalTo(1221));
    }

    @Test
    public void testCalculateWaypointExample() {
        int actual = Day12.calculateWaypoint(EXAMPLE_DATA);

        assertThat(actual, equalTo(286));
    }

    @Test
    public void testCalculateWaypointData() throws IOException {
        List<Day12.Instruction> instructions = FileUtil.readLines("y2020/day12.txt", Day12.Instruction::new);

        int actual = Day12.calculateWaypoint(instructions);

        assertThat(actual, equalTo(59435));
    }
}