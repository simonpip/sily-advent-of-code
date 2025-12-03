package dk.simonpip.aoc.y2025;

import java.util.Arrays;
import java.util.List;

public class Day03 {
    public static long solveFirst(List<int[]> arrays) {
        return solve(arrays, 2);
    }

    public static long solveSecond(List<int[]> arrays) {
        return solve(arrays, 12);
    }

    private static long solve(List<int[]> arrays, int length) {
        long sum = 0;
        for (int[] array : arrays) {
            int[] joltageIndex = new int[length];
            for (int i = 0; i < length; i++) {
                int startIndex = i == 0 ? 0 : joltageIndex[i - 1] + 1;
                int[] subarray = Arrays.copyOfRange(array, startIndex, array.length - (length - (i + 1)));

                joltageIndex[i] = findIndexOfLargestNumber(subarray) + startIndex;
                sum += (long)array[joltageIndex[i]] * (long)Math.pow(10, length - (i + 1));
            }
        }
        return sum;
    }

    private static int findIndexOfLargestNumber(int[] numbers) {
        int index = 0;
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[index]) {
                index = i;
            }
        }
        return index;
    }

    public static int[] convert(String line) {
        int[] output = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            output[i] = Integer.parseInt(line.substring(i, i + 1));
        }
        return output;
    }
}
