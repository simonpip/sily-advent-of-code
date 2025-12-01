package dk.simonpip.aoc.y2021;

import java.util.List;

class Day01 {
    private Day01() {}

    static long findIncreaseInDepth(List<Integer> depths) {
        long count = 0;
        int[] depthsArray = depths.stream().mapToInt(e -> e).toArray();

        for (int i = 1; i < depthsArray.length; i++) {
            if (depthsArray[i - 1] < depthsArray[i]) {
                count++;
            }
        }

        return count;
    }

    static long findIncreaseInAverageDepth(List<Integer> depths) {
        long count = 0;
        int[] depthsArray = depths.stream().mapToInt(e -> e).toArray();

        int previousSum = sumLastThree(depthsArray, 2);
        for (int i = 3; i < depthsArray.length; i++) {
            int sum = sumLastThree(depthsArray, i);
            if (previousSum < sum) {
                count++;
            }
            previousSum = sum;
        }

        return count;
    }

    private static int sumLastThree(int[] array, int i) {
        return array[i - 2] + array[i - 1] + array[i];
    }
}
