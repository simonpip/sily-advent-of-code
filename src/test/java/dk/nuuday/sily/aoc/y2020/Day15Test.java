package dk.nuuday.sily.aoc.y2020;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Day15Test {
    @Test
    public void testFindNumberExample1() {
        long actual = Day15.findNumber("0,3,6", 2020);

        assertThat(actual, equalTo(436L));
    }

    @Test
    public void testFindNumberExample2() {
        long actual = Day15.findNumber("1,3,2", 2020);

        assertThat(actual, equalTo(1L));
    }

    @Test
    public void testFindNumberExample3() {
        long actual = Day15.findNumber("2,1,3", 2020);

        assertThat(actual, equalTo(10L));
    }

    @Test
    public void testFindNumberExample4() {
        long actual = Day15.findNumber("1,2,3", 2020);

        assertThat(actual, equalTo(27L));
    }

    @Test
    public void testFindNumberExample5() {
        long actual = Day15.findNumber("2,3,1", 2020);

        assertThat(actual, equalTo(78L));
    }

    @Test
    public void testFindNumberExample6() {
        long actual = Day15.findNumber("3,2,1", 2020);

        assertThat(actual, equalTo(438L));
    }

    @Test
    public void testFindNumberExample7() {
        long actual = Day15.findNumber("3,1,2", 2020);

        assertThat(actual, equalTo(1836L));
    }

    @Test
    public void testFindNumberData() {
        long actual = Day15.findNumber("6,19,0,5,7,13,1", 2020);

        assertThat(actual, equalTo(468L));
    }

    @Test
    public void testFindNumberDataLong() {
        long actual = Day15.findNumber("6,19,0,5,7,13,1", 30000000);

        assertThat(actual, equalTo(1801753L));
    }
}