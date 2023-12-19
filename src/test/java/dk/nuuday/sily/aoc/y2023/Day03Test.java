package dk.nuuday.sily.aoc.y2023;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day03Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598.."
    );

    @Test
    public void testFindActivePartsExample() {
        Day03.Schematic schematic = new Day03.Schematic(EXAMPLE_DATA);

        int actual = Day03.findActiveParts(schematic);

        assertThat(actual, equalTo(4361));
    }

    @Test
    public void testFindActivePartsData() throws IOException {
        Day03.Schematic schematic = FileUtil.readFile("y2023/day03.txt", Day03.Schematic::new);

        int actual = Day03.findActiveParts(schematic);

        assertThat(actual, equalTo(549908));
    }

    @Test
    public void testFindGearRatiosExample() {
        Day03.Schematic schematic = new Day03.Schematic(EXAMPLE_DATA);

        int actual = Day03.findGearRatios(schematic);

        assertThat(actual, equalTo(467835));
    }

    @Test
    public void testFindGearRatiosData() throws IOException {
        Day03.Schematic schematic = FileUtil.readFile("y2023/day03.txt", Day03.Schematic::new);

        int actual = Day03.findGearRatios(schematic);

        assertThat(actual, equalTo(81166799));
    }
}
