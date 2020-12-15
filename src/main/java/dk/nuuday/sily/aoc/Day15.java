package dk.nuuday.sily.aoc;

import java.util.HashMap;
import java.util.Map;

public class Day15 {
    static long findNumber(String initialSequence, int wantedIndex) {
        String[] initialSequenceParts = initialSequence.split(",");
        long[] array = new long[wantedIndex];
        Map<Long, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < initialSequenceParts.length; i++) {
            array[i] = Long.parseLong(initialSequenceParts[i]);
            if (i != initialSequenceParts.length - 1) {
                indexMap.put(array[i], i);
            }
        }

        long previous = array[initialSequenceParts.length - 1];
        for (int i = initialSequenceParts.length; i < array.length; i++) {
            if (!indexMap.containsKey(previous)) {
                array[i] = 0;
            } else {
                int previousIndex = indexMap.get(previous);
                array[i] = i - previousIndex - 1;
            }

            indexMap.put(previous, i - 1);
            previous = array[i];
        }

        // Account for zero-indexing
        return array[wantedIndex - 1];
    }

    private static void addTurn(long value, int index, long[] array, Map<Long, Integer> indexMap) {
        array[index] = value;
        indexMap.put(value, index);
    }
}
