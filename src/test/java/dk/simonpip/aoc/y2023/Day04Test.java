package dk.simonpip.aoc.y2023;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day04Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
    );

    @Test
    public void testFindPointsExample() {
        List<Day04.Card> cards = EXAMPLE_DATA.stream()
                .map(Day04.Card::new)
                .collect(Collectors.toList());

        int sum = Day04.findPoints(cards);

        assertThat(sum, equalTo(13));
    }

    @Test
    public void testFindPointsData() throws IOException {
        List<Day04.Card> cards = FileUtil.readLines(
                "y2023/day04.txt",
                Day04.Card::new);

        int sum = Day04.findPoints(cards);

        assertThat(sum, equalTo(27059));
    }

    @Test
    public void testFindNumberOfCardsExample() {
        List<Day04.Card> cards = EXAMPLE_DATA.stream()
                .map(Day04.Card::new)
                .collect(Collectors.toList());

        int sum = Day04.findNumberOfCards(cards);

        assertThat(sum, equalTo(30));
    }

    @Test
    public void testFindNumberOfCardsData() throws IOException {
        List<Day04.Card> cards = FileUtil.readLines(
                "y2023/day04.txt",
                Day04.Card::new);

        int sum = Day04.findNumberOfCards(cards);

        assertThat(sum, equalTo(5744979));
    }
}
