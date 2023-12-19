package dk.nuuday.sily.aoc.y2023;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {
    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;

    public static int validateGames(List<Game> games) {
        return games.stream()
                .filter(e -> e.draws.stream()
                        .noneMatch(f -> f.red > MAX_RED
                                || f.green > MAX_GREEN
                                || f.blue > MAX_BLUE))
                .mapToInt(e -> e.index)
                .sum();
    }

    public static int findMinimumCubes(List<Game> games) {
        return games.stream().mapToInt(e -> {
            int red = e.draws.stream().mapToInt(f -> f.red).max().orElse(0);
            int green = e.draws.stream().mapToInt(f -> f.green).max().orElse(0);
            int blue = e.draws.stream().mapToInt(f -> f.blue).max().orElse(0);

            return red * green * blue;
        }).sum();
    }

    public static final class Game {
        private final int index;
        private final List<Draw> draws;

        public Game(String description) {
            String minimized = description
                    .replaceAll(" ", "")
                    .replaceAll("Game", "")
                    .replaceAll("red", "r")
                    .replaceAll("green", "g")
                    .replaceAll("blue", "b");

            String[] split = minimized.split(":");

            index = Integer.parseInt(split[0]);

            String[] drawStrings = split[1].split(";");

            draws = Arrays.stream(drawStrings).map(e -> {
                int red = 0;
                int green = 0;
                int blue = 0;

                String[] individualDraws = e.split(",");

                for (String individualDraw : individualDraws) {
                    int value = Integer.parseInt(individualDraw.substring(0, individualDraw.length() - 1));

                    switch (individualDraw.substring(individualDraw.length() - 1)) {
                        case "r":
                            red = value;
                            break;
                        case "g":
                            green = value;
                            break;
                        case "b":
                            blue = value;
                            break;
                    }
                }
                return new Draw(red, green, blue);
            }).collect(Collectors.toList());
        }
    }

    private static final class Draw {
        private final int red;
        private final int green;
        private final int blue;

        private Draw(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
    }
}
