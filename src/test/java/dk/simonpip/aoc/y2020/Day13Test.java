package dk.simonpip.aoc.y2020;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Day13Test {
    private static final Day13.Schedule EXAMPLE_DATA = new Day13.Schedule(List.of(
            "939",
            "7,13,x,x,59,x,31,19"));
    private static final Day13.Schedule ACTUAL_SCHEDULE = new Day13.Schedule(List.of(
            "939",
            "7,13,x,x,59,x,31,19,5"));

    @Test
    public void name() {
        assertThat(ACTUAL_SCHEDULE.routes.size(), equalTo(6));
        assertThat(ACTUAL_SCHEDULE.routes.get(0).route, equalTo(7));
        assertThat(ACTUAL_SCHEDULE.routes.get(0).remainder, equalTo(0));
        assertThat(ACTUAL_SCHEDULE.routes.get(1).route, equalTo(13));
        assertThat(ACTUAL_SCHEDULE.routes.get(1).remainder, equalTo(12));
        assertThat(ACTUAL_SCHEDULE.routes.get(2).route, equalTo(59));
        assertThat(ACTUAL_SCHEDULE.routes.get(2).remainder, equalTo(55));
        assertThat(ACTUAL_SCHEDULE.routes.get(3).route, equalTo(31));
        assertThat(ACTUAL_SCHEDULE.routes.get(3).remainder, equalTo(25));
        assertThat(ACTUAL_SCHEDULE.routes.get(4).route, equalTo(19));
        assertThat(ACTUAL_SCHEDULE.routes.get(4).remainder, equalTo(12));
        assertThat(ACTUAL_SCHEDULE.routes.get(5).route, equalTo(5));
        assertThat(ACTUAL_SCHEDULE.routes.get(5).remainder, equalTo(2));
    }

    @Test
    public void testFindEarliestBusExample() {
        int actual = Day13.findEarliestBus(EXAMPLE_DATA);

        assertThat(actual, equalTo(295));
    }

    @Test
    public void testFindEarliestBusData() throws IOException {
        Day13.Schedule schedule = FileUtil.readFile("y2020/day13.txt", Day13.Schedule::new);

        int actual = Day13.findEarliestBus(schedule);

        assertThat(actual, equalTo(4808));
    }

    @Test
    public void testFindEarliestSequenceExample() {
        long actual = Day13.findEarliestSequence(EXAMPLE_DATA);

        assertThat(actual, equalTo(1068781L));
    }

    @Test
    public void testFindEarliestSequenceData() throws IOException {
        Day13.Schedule schedule = FileUtil.readFile("y2020/day13.txt", Day13.Schedule::new);

        long actual = Day13.findEarliestSequence(schedule);

        assertThat(actual, equalTo(741745043105674L));
    }
}