package dk.simonpip.aoc.y2023;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day04 {
    public static int findPoints(List<Card> cards) {
        return cards.stream()
                .mapToInt(Day04::countWinners)
                .map(e -> (int)(Math.pow(2.0, (double) e) / 2.0))
                .sum();
    }

    public static int findNumberOfCards(List<Card> cards) {
        int[] cardCount = new int[cards.size()];
        Arrays.fill(cardCount, 1);

        int index = 0;
        for (Card card : cards) {
            int count = cardCount[index];

            int winners = countWinners(card);
            for (int i = index + 1; i <= index + winners && i < cardCount.length; i++) {
                cardCount[i] += count;
            }

            index++;
        }

        return Arrays.stream(cardCount).sum();
    }

    private static int countWinners(Card e) {
        return (int) e.actualNumbers.stream().filter(f -> e.winningNumbers.contains(f)).count();
    }

    public static final class Card {
        private final int index;
        private final List<Integer> winningNumbers;
        private final List<Integer> actualNumbers;

        public Card(String description) {
            String[] firstSplit = description.substring(4).split(":");

            index = Integer.parseInt(firstSplit[0].trim());

            String[] secondSplit = firstSplit[1].split("\\|");

            winningNumbers = convert(secondSplit[0]);
            actualNumbers = convert(secondSplit[1]);
        }

        private static List<Integer> convert(String numbers) {
            return Arrays.stream(numbers.trim().split(" "))
                    .filter(e -> !e.isEmpty())
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        }
    }
}
