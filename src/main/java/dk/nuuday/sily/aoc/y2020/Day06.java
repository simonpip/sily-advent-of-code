package dk.nuuday.sily.aoc.y2020;

import dk.nuuday.sily.aoc.util.LineUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Day06 {
    private Day06() {
    }

    static int countUniqueAnswersPerGroup(List<String> lines) {
        List<String> groupAnswers = LineUtil.combineGroups(lines);

        return groupAnswers.stream()
                .map(Day06::getUniqueCharacters)
                .mapToInt(Set::size)
                .sum();
    }

    static long countCommonAnswersPerGroup(List<String> lines) {
        List<List<String>> groupAnswerList = LineUtil.separateGroups(lines);

        return groupAnswerList.stream().mapToLong(Day06::countCommonAnswers).sum();
    }

    private static long countCommonAnswers(List<String> groupAnswers) {
        List<Set<String>> uniqueAnswersPerGroup = groupAnswers.stream()
                .map(Day06::getUniqueCharacters)
                .collect(Collectors.toList());
        Set<String> allAnswers = uniqueAnswersPerGroup.stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        return allAnswers.stream().filter(e -> uniqueAnswersPerGroup.stream().allMatch(f -> f.contains(e))).count();
    }

    private static Set<String> getUniqueCharacters(String string) {
        return Arrays.stream(string.split("")).collect(Collectors.toSet());
    }
}
