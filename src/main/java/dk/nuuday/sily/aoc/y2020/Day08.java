package dk.nuuday.sily.aoc.y2020;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day08 {
    private Day08() {
    }

    static int runProgram(List<String> operations) {
        String[] array = operations.toArray(new String[0]);

        return runProgram(array).output;
    }

    static int fixAndRunProgram(List<String> operations) {
        for (int i = 0; i < operations.size(); i++) {
            String[] array = operations.toArray(new String[0]);
            String current = array[i];

            if (current.contains("jmp")) {
                array[i] = current.replace("jmp", "nop" );
            } else if (current.contains("nop")) {
                array[i] = current.replace("nop", "jmp" );
            }

            Result result = runProgram(array);
            if (result.completed) {
                return result.output;
            }
        }
        return 0;
    }

    private static Result runProgram(String[] operations) {
        int count = 0;
        int index = 0;
        Set<Integer> metIndices = new HashSet<>();
        while (!metIndices.contains(index) && index < operations.length) {
            metIndices.add(index);

            String s = operations[index];
            String operation = s.substring(0, 3);
            int value = Integer.parseInt(s.substring(4));

            switch (operation) {
                case "acc":
                    count += value;
                case "nop":
                    index++;
                    break;
                case "jmp":
                    index += value;
                    break;
            }
        }

        return new Result(count, index >= operations.length);
    }

    static final class Result {
        private final int output;
        private final boolean completed;

        public Result(int output, boolean completed) {
            this.output = output;
            this.completed = completed;
        }
    }
}
