package dk.nuuday.sily.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10 {
    static long getAdapterJumpsMultiplied(List<Integer> adapters) {
        long[] jumps = getAdapterJumps(adapters);
        if (jumps.length < 3) {
            return 0;
        }
        return jumps[0] * jumps[2];
    }

    private static long[] getAdapterJumps(List<Integer> adapters) {
        SortedSet<Integer> sorted = new TreeSet<>(adapters);
        sorted.add(sorted.last() + 3);

        long[] jumps = new long[3];
        int current = 0;
        for (Integer adapter : sorted) {
            int jump = adapter - current;
            current = adapter;
            if (jump > 3 || jump < 1) {
                return new long[0];
            }
            jumps[jump - 1] = jumps[jump - 1] + 1;
        }
        return jumps;
    }

    static long countDistinctAdapterConfigurations(List<Integer> adapters) {
        List<Integer> copy = new ArrayList<>(adapters);
        copy.add(0);
        Integer[] array = copy.toArray(new Integer[0]);
        Arrays.sort(array, Comparator.reverseOrder());

        long[] counts = new long[array.length];

        // Last two are always one
        counts[0] = 1;
        counts[1] = 1;
        // Third to last is 1 or 2, depending on if it can reach last.
        counts[2] = array[0] < array[2] + 3 ? 2 : 1;
        for (int i = 3; i < array.length; i++) {
            int max = array[i] + 3;
            // Current always reaches first in list, potentially more
            counts[i] = counts[i - 1]
                    + (array[i - 2] > max ? 0 : counts[i - 2])
                    + (array[i - 3] > max ? 0 : counts[i - 3]);
        }
        return counts[counts.length - 1];
    }
}
