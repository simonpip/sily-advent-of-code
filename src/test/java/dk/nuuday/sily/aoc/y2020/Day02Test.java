package dk.nuuday.sily.aoc.y2020;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class Day02Test {
    private static final List<Day02.Password> EXAMPLE_DATA = List.of(
            new Day02.Password("1-3 a: abcde"),
            new Day02.Password("1-3 b: cdefg"),
            new Day02.Password("2-9 c: ccccccccc"));

    @Test
    public void testCountValidPasswordsExample() {
        int actual = Day02.countValidPasswords(EXAMPLE_DATA);

        assertThat(actual, equalTo(2));
    }

    @Test
    public void testCountValidPasswordsData() throws IOException  {
        List<Day02.Password> entries = FileUtil.readLines("y2020/day02.txt", Day02.Password::new);

        int actual = Day02.countValidPasswords(entries);

        assertThat(actual, equalTo(536));
    }

    @Test
    public void testCountValidPasswordsPart2Example() {
        int actual = Day02.countValidPasswordsPart2(EXAMPLE_DATA);

        assertThat(actual, equalTo(1));
    }

    @Test
    public void testCountValidPasswordsPart2Data() throws IOException  {
        List<Day02.Password> entries = FileUtil.readLines("y2020/day02.txt", Day02.Password::new);

        int actual = Day02.countValidPasswordsPart2(entries);

        assertThat(actual, equalTo(558));
    }
}