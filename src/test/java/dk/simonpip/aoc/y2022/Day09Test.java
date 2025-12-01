package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day09Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2");


    private static final List<String> EXAMPLE_DATA_2 = List.of(
            "R 5",
            "U 8",
            "L 8",
            "D 3",
            "R 17",
            "D 10",
            "L 25",
            "U 20");

    @Test
    public void testCountTailFieldsExample() {
        List<Day09.Operation> data = EXAMPLE_DATA.stream().map(Day09.Operation::new).collect(Collectors.toList());

        int actual = Day09.countTailFields(data, 2);

        assertThat(actual, equalTo(13));
    }

    @Test
    public void testCountTailFieldsData() throws IOException {
        List<Day09.Operation> data = FileUtil.readLines("y2022/day09.txt", Day09.Operation::new);

        int actual = Day09.countTailFields(data, 2);

        assertThat(actual, equalTo(6044));
    }

    @Test
    public void testCountTailFieldsLongExample() {
        List<Day09.Operation> data = EXAMPLE_DATA.stream().map(Day09.Operation::new).collect(Collectors.toList());

        int actual = Day09.countTailFields(data, 10);

        assertThat(actual, equalTo(1));
    }

    @Test
    public void testCountTailFieldsLongExample2() {
        List<Day09.Operation> data = EXAMPLE_DATA_2.stream().map(Day09.Operation::new).collect(Collectors.toList());

        int actual = Day09.countTailFields(data, 10);

        assertThat(actual, equalTo(36));
    }

    @Test
    public void testCountTailFieldsLongData() throws IOException {
        List<Day09.Operation> data = FileUtil.readLines("y2022/day09.txt", Day09.Operation::new);

        int actual = Day09.countTailFields(data, 10);

        assertThat(actual, equalTo(2384));
    }
}
