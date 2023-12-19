package dk.nuuday.sily.aoc.y2023;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day01 {
    private static final Map<String, String> REPLACEMENTS = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    public static int findCalibrationValues(List<String> calibrationDocument) {
        return calibrationDocument.stream()
                .filter(e -> !e.matches("[0-9]"))
                .mapToInt(e -> {
                    String reducedLine = e.replaceAll("[^0-9]", "");

                    String minimalLine = new String(new char[] {
                            reducedLine.charAt(0),
                            reducedLine.charAt(reducedLine.length() - 1)
                    });

                    return Integer.parseInt(minimalLine);
                })
                .sum();
    }

    public static int findCalibrationValuesExtended(List<String> calibrationDocument) {
        List<String> adjustedDocument = calibrationDocument.stream()
                .map(e -> {
                    StringBuilder sb = new StringBuilder(e.length());
                    for (int i = 0; i < e.length(); i++) {
                        String value = e;
                        int index = i;
                        Optional<String> candidate = REPLACEMENTS.entrySet().stream()
                                .filter(entry -> value.length() >= index + entry.getKey().length() && entry.getKey().equals(value.substring(index, index + entry.getKey().length())))
                                .map(Map.Entry::getValue)
                                .findAny();

                        if (candidate.isPresent()) {
                            sb.append(candidate.get());
                        } else {
                            sb.append(e.charAt(i));
                        }
                    }
                    return sb.toString();
                })
                .collect(Collectors.toList());

        return findCalibrationValues(adjustedDocument);
    }
}
