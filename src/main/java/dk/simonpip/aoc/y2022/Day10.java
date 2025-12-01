package dk.simonpip.aoc.y2022;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Day10 {
    private static final List<Integer> KEY_CYCLES = List.of(20, 60, 100, 140, 180, 220);
    static int calculateSignalStrength(List<Operation> operations) {
        Queue<Integer> keyCycles = new ArrayDeque<>(KEY_CYCLES);

        int currentCycle = 0;
        int x = 1;
        Integer nextKeyCycle = keyCycles.poll();
        int signalStrength = 0;
        for (Operation operation : operations) {
            if (nextKeyCycle == null) {
                return signalStrength;
            }
            currentCycle = currentCycle + operation.cycles;
            if (nextKeyCycle <= currentCycle) {
                signalStrength += nextKeyCycle * x;
                nextKeyCycle = keyCycles.poll();
            }
            x += operation.value;
        }

        return signalStrength;
    }

    static String drawSignal(List<Operation> operations) {
        Queue<Operation> opQueue = new ArrayDeque<>(operations);
        int nextOp = 0;
        int opValue = 0;
        int x = 0;
        StringBuilder stringBuilder = new StringBuilder(240);
        for (int i = 0; i < 240; i++) {
            if (i == nextOp) {
                x += opValue;
                Operation queued = opQueue.poll();
                if (queued != null) {
                    nextOp = i + queued.cycles;
                    opValue = queued.value;
                }
            }

            int cursor = i % 40;
            if (cursor == 0) {
                stringBuilder.append('\n');
            }
            if (cursor >= x && cursor <= x + 2) {
                stringBuilder.append('█');
            } else {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }

    static class Operation {
        private final int cycles;
        private final int value;

        public Operation(String operation) {
            if ("noop".equals(operation)) {
                cycles = 1;
                value = 0;
            } else {
                cycles = 2;
                value = Integer.parseInt(operation.substring(5));
            }
        }
    }
}
