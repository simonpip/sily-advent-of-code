package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day03Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw");

    @Test
    public void testFindMisplacedItemsExample() {
        int actual = Day03.findMisplacedItems(EXAMPLE_DATA);

        assertThat(actual, equalTo(157));
    }

    @Test
    public void testFindMisplacedItemsData() throws IOException {
        List<String> data = FileUtil.readLines("y2022/day03.txt");

        int actual = Day03.findMisplacedItems(data);

        assertThat(actual, equalTo(8202));
    }
    @Test
    public void testFindAuthenticityItemsExample() {
        int actual = Day03.findAuthenticityItems(EXAMPLE_DATA);

        assertThat(actual, equalTo(70));
    }

    @Test
    public void testFindAuthenticityItemsData() throws IOException {
        List<String> data = FileUtil.readLines("y2022/day03.txt");

        int actual = Day03.findAuthenticityItems(data);

        assertThat(actual, equalTo(2864));
    }
}
