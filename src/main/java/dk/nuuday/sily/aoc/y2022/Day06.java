package dk.nuuday.sily.aoc.y2022;

import java.util.*;

public class Day06 {
    static int indexOfMarker(String string, int markerLength) {
        char[] chars = string.toCharArray();
        for (int i = markerLength; i <= chars.length; i++) {
            if (allDifferent(Arrays.copyOfRange(chars, i - markerLength, i))) {
                return i;
            }
        }
        throw new RuntimeException("No sequence of 4 unique characters found");
    }

    private static boolean allDifferent(char[] characters) {
        Set<Character> set = new HashSet<>();
        for (char character : characters) {
            set.add(character);
        }
        return set.size() == characters.length;
    }
}
