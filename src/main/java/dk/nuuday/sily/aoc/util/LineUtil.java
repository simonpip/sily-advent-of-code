package dk.nuuday.sily.aoc.util;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LineUtil {
    private LineUtil() {
    }

    public static List<String> combineGroups(List<String> lines) {
        return combineGroups(lines, "");
    }

    public static List<String> combineGroups(List<String> lines, String separator) {
        return separateGroups(lines).stream().map(e -> Joiner.on(separator).join(e)).collect(Collectors.toList());
    }

    public static List<List<String>> separateGroups(List<String> lines) {
        List<List<String>> groups = new ArrayList<>();
        List<String> group = new ArrayList<>();
        for (String string : lines) {
            if (string.isEmpty()) {
                groups.add(group);
                group = new ArrayList<>();
                continue;
            }
            group.add(string);
        }
        groups.add(group);

        return groups;
    }
}
