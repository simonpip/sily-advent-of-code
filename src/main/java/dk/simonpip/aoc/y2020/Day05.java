package dk.simonpip.aoc.y2020;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Day05 {
    private Day05() {
    }

    static int findHighestTakenSeat(List<BoardingPass> boardingPasses) {
        return boardingPasses.stream().map(b -> b.seatId).max(Comparator.naturalOrder()).get();
    }

    static int findUnfilledSeat(List<BoardingPass> boardingPasses) {
        List<BoardingPass> sorted = new ArrayList<>(boardingPasses);
        sorted.sort(Comparator.comparing(b -> b.seatId));

        int expectedSeatId = sorted.get(0).seatId;
        for (BoardingPass boardingPass : sorted) {
            if (boardingPass.seatId != expectedSeatId) {
                return expectedSeatId;
            }
            expectedSeatId++;
        }
        return 0;
    }

    static final class BoardingPass {
        private static final int[] STEP_VALUES = new int[] {
                // Rows
                64 * 8,
                32 * 8,
                16 * 8,
                8 * 8,
                4 * 8,
                2 * 8,
                8,
                // Columns
                4,
                2,
                1
        };
        private static final int NUM_COLUMNS = 8;

        private final int row;
        private final int column;
        private final int seatId;

        BoardingPass(String boardingPass) {
            int sum = 0;
            for (int i = 0; i < boardingPass.length(); i++) {
                 char letter = boardingPass.charAt(i);

                 if (letter == 'B' || letter == 'R') {
                     sum += STEP_VALUES[i];
                 }
            }

            seatId = sum;

            row = sum / NUM_COLUMNS;
            column = sum % NUM_COLUMNS;
        }

        @Override
        public String toString() {
            return "BoardingPass{" +
                    "row=" + row +
                    ", column=" + column +
                    ", seatId=" + seatId +
                    '}';
        }
    }
}
