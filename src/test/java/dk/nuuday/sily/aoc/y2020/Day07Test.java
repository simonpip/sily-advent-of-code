package dk.nuuday.sily.aoc.y2020;

import dk.nuuday.sily.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Day07Test {
    private static final List<String> EXAMPLE_DATA_1 = List.of(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags.");

    private static final List<String> EXAMPLE_DATA_2 = List.of(
            "shiny gold bags contain 2 dark red bags.",
            "dark red bags contain 2 dark orange bags.",
            "dark orange bags contain 2 dark yellow bags.",
            "dark yellow bags contain 2 dark green bags.",
            "dark green bags contain 2 dark blue bags.",
            "dark blue bags contain 2 dark violet bags.",
            "dark violet bags contain no other bags.");

    @Test
    public void testFindCountOfOuterBagsExample() {
        int actual = Day07.findCountOfOuterBags(EXAMPLE_DATA_1, "shiny gold");

        assertThat(actual, equalTo(4));
    }

    @Test
    public void testFindCountOfOuterBagsData() throws IOException {
        List<String> descriptions = FileUtil.readLines("y2020/day07.txt");

        int actual = Day07.findCountOfOuterBags(descriptions, "shiny gold");

        assertThat(actual, equalTo(252));
    }

    @Test
    public void testSumInnerBagsExample() {
        int actual = Day07.sumInnerBags(EXAMPLE_DATA_2, "shiny gold");

        assertThat(actual, equalTo(126));
    }

    @Test
    public void testSumInnerBagsData() throws IOException {
        List<String> descriptions = FileUtil.readLines("y2020/day07.txt");

        int actual = Day07.sumInnerBags(descriptions, "shiny gold");

        assertThat(actual, equalTo(35487));
    }
}