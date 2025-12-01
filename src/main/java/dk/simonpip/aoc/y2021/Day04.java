package dk.simonpip.aoc.y2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class Day04 {
    private static final int BOARD_SIZE = 5;

    private Day04() {}

    static int findWinningBoard(List<String> game) {
        Game parsedGame = new Game(game);

        return parsedGame.run();
    }

    static int findLastWinningBoard(List<String> game) {
        Game parsedGame = new Game(game);

        return parsedGame.runLong();
    }

    private static class Game {
        private final int[] draws;
        private final List<Board> boards;

        Game(List<String> game) {
            draws = Arrays.stream(game.get(0).split(",")).mapToInt(Integer::parseInt).toArray();

            boards = new ArrayList<>(game.size() / BOARD_SIZE);
            for (int i = 2; i < game.size(); i += BOARD_SIZE + 1) {
                boards.add(new Board(game.subList(i, i + BOARD_SIZE)));
            }
        }

        int run() {
            for (int draw : draws) {
                for (Board board : boards) {
                    if (board.mark(draw)) {
                        return board.score() * draw;
                    }
                }
            }
            return 0;
        }

        int runLong() {
            Board lastToWin = null;
            int lastWinningDraw = 0;
            for (int draw : draws) {
                for (Iterator<Board> iterator = boards.iterator(); iterator.hasNext(); ) {
                    Board board = iterator.next();
                    if (board.mark(draw)) {
                        lastToWin = board;
                        lastWinningDraw = draw;

                        iterator.remove();
                    }
                }
            }
            return lastToWin != null ? lastToWin.score() * lastWinningDraw : 0;
        }
    }

    private static class Board {
        private final int[][] numbers;
        private final boolean[][] marked;

        Board(List<String> rows) {
            numbers = new int[BOARD_SIZE][BOARD_SIZE];
            marked = new boolean[BOARD_SIZE][BOARD_SIZE];
            for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
                int row = i / BOARD_SIZE;
                int column = i % BOARD_SIZE;

                String unparsed = rows.get(row).trim().replace("  ", " ").split(" ")[column].trim();

                numbers[column][row] = Integer.parseInt(unparsed.trim());
            }
        }

        boolean mark(int draw) {
            int i = 0;
            for (; i < BOARD_SIZE * BOARD_SIZE; i++) {
                int row = i / BOARD_SIZE;
                int column = i % BOARD_SIZE;

                if (numbers[column][row] == draw) {
                    marked[column][row] = true;
                    break;
                }
            }

            return i != 25 && check(i / BOARD_SIZE, i % BOARD_SIZE);
        }

        private boolean check(int row, int column) {
            boolean rowFilled = true;
            boolean columnFilled = true;
            for (int i = 0; i < BOARD_SIZE; i++) {
                rowFilled &= marked[i][row];
                columnFilled &= marked[column][i];
            }

            return rowFilled || columnFilled;
        }

        int score() {
            int score = 0;
            for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
                int row = i / BOARD_SIZE;
                int column = i % BOARD_SIZE;

                if (!marked[column][row]) {
                    score += numbers[column][row];
                }
            }
            return score;
        }
    }
}
