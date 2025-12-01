package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day10Test {
    private static final String EXPECTED_SIGNAL_EXAMPLE = "\n" +
            "██  ██  ██  ██  ██  ██  ██  ██  ██  ██  \n" +
            "███   ███   ███   ███   ███   ███   ███ \n" +
            "████    ████    ████    ████    ████    \n" +
            "█████     █████     █████     █████     \n" +
            "██████      ██████      ██████      ████\n" +
            "███████       ███████       ███████     ";

    private static final String EXPECTED_SIGNAL_DATA = "\n" +
            "███  ████ ███   ██  ████ ████   ██ ███  \n" +
            "█  █    █ █  █ █  █    █ █       █ █  █ \n" +
            "█  █   █  ███  █      █  ███     █ ███  \n" +
            "███   █   █  █ █ ██  █   █       █ █  █ \n" +
            "█    █    █  █ █  █ █    █    █  █ █  █ \n" +
            "█    ████ ███   ███ ████ ████  ██  ███  ";

    @Test
    public void testCalculateSignalStrengthExample() throws IOException {
        List<Day10.Operation> data = FileUtil.readLines("y2022/day10example.txt", Day10.Operation::new);

        int actual = Day10.calculateSignalStrength(data);

        assertThat(actual, equalTo(13140));
    }

    @Test
    public void testCalculateSignalStrengthData() throws IOException {
        List<Day10.Operation> data = FileUtil.readLines("y2022/day10.txt", Day10.Operation::new);

        int actual = Day10.calculateSignalStrength(data);

        assertThat(actual, equalTo(14520));
    }

    @Test
    public void testDrawSignalExample() throws IOException {
        List<Day10.Operation> data = FileUtil.readLines("y2022/day10example.txt", Day10.Operation::new);

        String actual = Day10.drawSignal(data);

        assertThat(actual, equalTo(EXPECTED_SIGNAL_EXAMPLE));
    }

    @Test
    public void testDrawSignalData() throws IOException {
        List<Day10.Operation> data = FileUtil.readLines("y2022/day10.txt", Day10.Operation::new);

        String actual = Day10.drawSignal(data);

        assertThat(actual, equalTo(EXPECTED_SIGNAL_DATA));
    }
}
