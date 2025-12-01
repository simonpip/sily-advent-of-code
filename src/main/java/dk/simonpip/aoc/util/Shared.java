package dk.simonpip.aoc.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Shared {
    private Shared() {
    }

    public static long[] findNumbersSummingTo(List<Long> numbers, long wantedSum) {
        List<Long> sorted = numbers.stream()
                .filter(e -> e < wantedSum)
                .sorted()
                .collect(Collectors.toList());

        for (int i = sorted.size() - 1; i >= 1; i--) {
            long high = sorted.get(i);

            boolean matchingLow = sorted.subList(0, i).stream().anyMatch(e -> e == wantedSum - high);
            if (matchingLow) {
                return new long[] {wantedSum - high, high};
            }
        }
        return new long[0];
    }

    public static char[][] convertToCharArray(List<String> lines) {
        char[][] charArray = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            charArray[i] = lines.get(i).toCharArray();
        }
        return charArray;
    }

    public static Stream<Character> stream(char[] chars) {
        Character[] characters = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            characters[i] = chars[i];
        }
        return Arrays.stream(characters);
    }
}
