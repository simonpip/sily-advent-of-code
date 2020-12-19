package dk.nuuday.sily.aoc;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class Day10Test {
    private static final List<Integer> EXAMPLE_DATA = List.of(
            16,
            10,
            15,
            5,
            1,
            11,
            7,
            19,
            6,
            12,
            4);

    @Test
    public void testGetAdapterJumpsMultipliedExample() {
        long actual = Day10.getAdapterJumpsMultiplied(EXAMPLE_DATA);

        assertThat(actual, equalTo(35L));
    }

    @Test
    public void testGetAdapterJumpsMultipliedData() throws IOException {
        List<Integer> adapters = FileUtil.readLines("day10.txt", Integer::valueOf);

        long actual = Day10.getAdapterJumpsMultiplied(adapters);

        assertThat(actual, equalTo(1656L));
    }

    @Test
    public void testCountDistinctAdapterConfigurationsExample() {
        long actual = Day10.countDistinctAdapterConfigurations(EXAMPLE_DATA);

        assertThat(actual, equalTo(8L));
    }

    @Test
    public void testCountDistinctAdapterConfigurationsExample2() throws IOException {
        List<Integer> adapters = FileUtil.readLines("day10_large_example.txt", Integer::valueOf);

        long actual = Day10.countDistinctAdapterConfigurations(adapters);

        assertThat(actual, equalTo(19208L));
    }

    @Test
    public void testCountDistinctAdapterConfigurationsData() throws IOException {
        List<Integer> adapters = FileUtil.readLines("day10.txt", Integer::valueOf);

        long actual = Day10.countDistinctAdapterConfigurations(adapters);

        assertThat(actual, equalTo(56693912375296L));
    }
}