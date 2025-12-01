package dk.simonpip.aoc.y2023;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day02Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    );

    @Test
    public void testValidateGamesExample() {
        List<Day02.Game> games = EXAMPLE_DATA.stream()
                .map(Day02.Game::new)
                .collect(Collectors.toList());

        int sum = Day02.validateGames(games);

        assertThat(sum, equalTo(8));
    }

    @Test
    public void testValidateGamesData() throws IOException {
        List<Day02.Game> games = FileUtil.readLines(
                "y2023/day02.txt",
                Day02.Game::new);

        int sum = Day02.validateGames(games);

        assertThat(sum, equalTo(2810));
    }

    @Test
    public void testFindMinimumCubesExample() {
        List<Day02.Game> games = EXAMPLE_DATA.stream()
                .map(Day02.Game::new)
                .collect(Collectors.toList());

        int sum = Day02.findMinimumCubes(games);

        assertThat(sum, equalTo(2286));
    }

    @Test
    public void testFindMinimumCubesData() throws IOException {
        List<Day02.Game> games = FileUtil.readLines(
                "y2023/day02.txt",
                Day02.Game::new);

        int sum = Day02.findMinimumCubes(games);

        assertThat(sum, equalTo(69110));
    }
}
