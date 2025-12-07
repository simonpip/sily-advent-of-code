package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.LineUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Day06 {
    public static long solve(List<Problem> problems) {
        return problems.stream()
                .mapToLong(problem -> problem.numbers.stream()
                        .reduce(identity(problem.operation), reduction(problem.operation)))
                .sum();
    }

    private static long identity(String operation) {
        switch (operation) {
            case "*":
                return 1;
            case "+":
                return 0;
            default:
                throw new RuntimeException("Invalid operation");
        }
    }

    private static BinaryOperator<Long> reduction(String operation) {
        switch (operation) {
            case "*":
                return (a, b) -> a * b;
            case "+":
                return Long::sum;
            default:
                throw new RuntimeException("Invalid operation");
        }
    }

    public static List<Problem> convertForFirst(List<String> lines) {
        List<String> copy = new ArrayList<>(lines);

        String operationLine = LineUtil.removeLast(copy);
        List<String> operations = getOperations(operationLine);

        List<List<Long>> numbers = new ArrayList<>();
        for (int i = 0; i < operations.size(); i++) {
            numbers.add(new ArrayList<>());
        }
        for (String line : copy) {
            List<Long> lineNumbers = Arrays.stream(line.split(" "))
                    .filter(s -> !s.isBlank()).map(Long::valueOf)
                    .collect(Collectors.toList());
            for (int i = 0; i < numbers.size(); i++) {
                numbers.get(i).add(lineNumbers.get(i));
            }
        }

        List<Problem> problems = new ArrayList<>(operations.size());
        for (int i = 0; i < operations.size(); i++) {
            problems.add(new Problem(
                    numbers.get(i),
                    operations.get(i)));
        }
        return problems;
    }

    public static List<Problem> convertForSecond(List<String> lines) {
        List<String> copy = new ArrayList<>(lines);

        String operationLine = LineUtil.removeLast(copy);
        List<String> operations = getOperations(operationLine);

        List<String> transposedStrings = transpose(copy);

        List<Problem> problems = new ArrayList<>(operations.size());
        List<Long> numbers = new ArrayList<>();
        for (String transposedString : transposedStrings) {
            if (transposedString.isBlank()) {
                problems.add(new Problem(numbers, operations.get(problems.size())));

                numbers = new ArrayList<>();

                continue;
            }
            numbers.add(Long.valueOf(transposedString));
        }
        return problems;
    }

    private static List<String> getOperations(String operationLine) {
        return Arrays.stream(operationLine.split(""))
                .filter(character -> !character.isBlank())
                .collect(Collectors.toList());
    }

    private static List<String> transpose(List<String> lines) {
        List<StringBuilder> newLines = new ArrayList<>();
        int length = lines.stream()
                .mapToInt(String::length)
                .max()
                .orElseThrow(() -> new RuntimeException("No lines?"));
        for (int i = 0; i < length; i++) {
            newLines.add(new StringBuilder(lines.size()));
        }

        for (String line : lines) {
            String[] characters = line.split("");
            for (int i = 0; i < characters.length; i++) {
                String character = characters[i];
                if (!character.isBlank()) {
                    newLines.get(i).append(character);
                }
            }
        }
        return newLines.stream().map(StringBuilder::toString).collect(Collectors.toList());
    }

    public static class Problem {
        List<Long> numbers;
        String operation;

        public Problem(List<Long> numbers, String operation) {
            this.numbers = numbers;
            this.operation = operation;
        }
    }
}
