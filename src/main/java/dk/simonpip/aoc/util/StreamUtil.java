package dk.simonpip.aoc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtil {
    public static boolean[] toArray(Stream<Boolean> booleanStream) {
        List<Boolean> booleanList = booleanStream.collect(Collectors.toList());
        boolean[] array = new boolean[booleanList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = booleanList.get(i);
        }
        return array;
    }

    public static Stream<Boolean> stream(boolean[] array) {
        List<Boolean> booleanList = new ArrayList<>(array.length);
        for (boolean b : array) {
            booleanList.add(b);
        }
        return booleanList.stream();
    }
}
