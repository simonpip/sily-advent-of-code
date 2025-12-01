package dk.simonpip.aoc.y2020;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

class Day18 {
    private Day18() {
    }

    static long sumResults(List<String> functions) {
        return functions.stream().mapToLong(Day18::calculateFunction).sum();
    }

    static long calculateFunction(String function) {
        return calculateFunction(function, Day18::calculateSimpleFunction);
    }

    static long sumResultsOrdered(List<String> functions) {
        return functions.stream().mapToLong(Day18::calculateFunctionOrdered).sum();
    }

    static long calculateFunctionOrdered(String function) {
        return calculateFunction(function, Day18::calculateSimpleFunctionOrdered);
    }

    private static long calculateFunction(String function, Function<String, Long> calculateFunction) {
        String simplified = function;
        Deque<Integer> startParenthesis = new ArrayDeque<>();

        for (int i = 0; i < simplified.length(); i++) {
            char letter = simplified.charAt(i);
            if (letter == '(') {
                startParenthesis.push(i);
            } else if (letter == ')') {
                int startIndex = startParenthesis.pop();

                long value = calculateFunction.apply(simplified.substring(startIndex + 1, i));
                String valueString = String.valueOf(value);

                simplified = simplified.substring(0, startIndex) + valueString + simplified.substring(i + 1);

                i = startIndex;
            }
        }

        return calculateFunction.apply(simplified);
    }

    private static long calculateSimpleFunction(String function) {
        long sum = 0;
        Operation lastOperation = Operation.ADD;

        StringBuilder numberBuilder = new StringBuilder();
        for (int i = 0; i < function.length(); i++) {
            char letter = function.charAt(i);
            Optional<Operation> operationStream = Arrays.stream(Operation.values())
                    .filter(e -> e.letter == letter)
                    .findFirst();

            if (letter >= '0' && letter <= '9') {
                numberBuilder.append(letter);
            } else if (operationStream.isPresent()) {
                long value = Long.parseLong(numberBuilder.toString());
                sum = lastOperation.operation.apply(sum, value);

                numberBuilder = new StringBuilder();
                lastOperation = operationStream.get();
            }
        }
        long value = Long.parseLong(numberBuilder.toString());
        sum = lastOperation.operation.apply(sum, value);

        return sum;
    }

    private static long calculateSimpleFunctionOrdered(String function) {
        return Arrays.stream(function.split((" [*]{1} "))).mapToLong(
                e -> Arrays.stream(e.split(" [+]{1} ")).mapToLong(Long::valueOf).sum()
        ).reduce(1, (a, b) -> a * b);
    }

    private enum Operation {
        ADD('+', Long::sum),
        MULTIPLY('*', (a, b) -> a * b);

        private final char letter;
        private final BiFunction<Long, Long, Long> operation;

        Operation(char letter, BiFunction<Long, Long, Long> operation) {
            this.letter = letter;
            this.operation = operation;
        }
    }
}
