package dk.nuuday.sily.aoc.y2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07 {
    private static final String CARD_RANKING = "23456789TJQKA";
    private static final String CARD_RANKING_JOKER = "J23456789TQKA";

    public static int calculateWinnings(List<Hand> hands, boolean joker) {
        List<Hand> sortedHands = new ArrayList<>(hands);
        sortedHands.sort((a, b) -> compare(a, b, joker));

        int sum = 0;
        int index = 1;
        for (Hand hand : sortedHands) {
            sum += hand.bid * index;

            index++;
        }

        return sum;
    }

    private static int compare(Hand lhs, Hand rhs, boolean joker) {
        int compare = joker
                ? lhs.typeJoker.rank - rhs.typeJoker.rank
                : lhs.type.rank - rhs.type.rank;
        if (compare != 0) {
            return compare;
        }

        String usedRanking = joker ? CARD_RANKING_JOKER : CARD_RANKING;
        for (int i = 0; i < 5; i++) {
            int lhsRank = usedRanking.indexOf(lhs.cards.charAt(i));
            int rhsRank = usedRanking.indexOf(rhs.cards.charAt(i));

            compare = lhsRank - rhsRank;
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    public static final class Hand {
        private final String cards;
        private final HandType type;
        private final HandType typeJoker;
        private final int bid;

        public Hand(String description) {
            String[] parts = description.split(" ");

            cards = parts[0];
            type = HandType.fromCards(cards, false);
            typeJoker = HandType.fromCards(cards, true);
            bid = Integer.parseInt(parts[1]);
        }
    }

    private enum HandType {
        HIGH_CARD(1),
        PAIR(2),
        TWO_PAIR(3),
        THREE_OF_A_KIND(4),
        FULL_HOUSE(5),
        FOUR_OF_A_KIND(6),
        FIVE_OF_A_KIND(7);

        private final int rank;

        HandType(int rank) {
            this.rank = rank;
        }

        private static HandType fromCards(String cards, boolean joker) {
            Map<Character, Integer> characterCount = new HashMap<>();

            cards.chars()
                    .mapToObj(e -> (char) e)
                    .forEach(character -> {
                        int count = characterCount.computeIfAbsent(character, k -> 0);
                        characterCount.put(character, count + 1);
                    });

            return joker
                    ? fromCardsJoker(characterCount)
                    : fromCards(characterCount);
        }

        private static HandType fromCards(Map<Character, Integer> characterCount) {
            if (characterCount.size() == 1) {
                return FIVE_OF_A_KIND;
            } else if (characterCount.size() == 2) {
                if (characterCount.values().stream().anyMatch(e -> e == 4)) {
                    return FOUR_OF_A_KIND;
                } else {
                    // Must be 2 and 3
                    return FULL_HOUSE;
                }
            } else if (characterCount.size() == 3) {
                if (characterCount.values().stream().anyMatch(e -> e == 2)) {
                    return TWO_PAIR;
                } else {
                    // Must be 3, 1, and 1
                    return THREE_OF_A_KIND;
                }
            } else if (characterCount.size() == 4) {
                return PAIR;
            } else {
                return HIGH_CARD;
            }
        }

        private static HandType fromCardsJoker(Map<Character, Integer> characterCount) {
            Integer jokerCount = characterCount.get('J');
            if (jokerCount == null) {
                return fromCards(characterCount);
            }

            if (characterCount.size() == 1 || characterCount.size() == 2) {
                // It is either all J or J and one other card type
                return FIVE_OF_A_KIND;
            } else if (characterCount.size() == 3) {
                // Four of a kind from 3 of a kind, 1 random, and 1 J
                // Four of a kind from 2 of a kind, 1 random, and 2 J
                // Four of a kind from 2 different random, 3 J
                if (characterCount.values().stream().anyMatch(e -> e + jokerCount == 4)) {
                    return FOUR_OF_A_KIND;
                } else {
                    // Must be two pairs and one J
                    return FULL_HOUSE;
                }
            } else if (characterCount.size() == 4) {
                // Three of a kind from a pair, 2 different random, and 1 J
                // Three of a kind from 3 different random, 2 J
                return THREE_OF_A_KIND;
            } else {
                // 5 different card types, one is a J
                return PAIR;
            }
        }
    }
}
