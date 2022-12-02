package dk.nuuday.sily.aoc.y2022;

import java.util.List;

public class Day02 {
    private static final String DIVIDER = " ";
    private static final String OPPONENT_ROCK = "A";
    private static final String OPPONENT_PAPER = "B";
    private static final String OPPONENT_SCISSORS = "C";
    private static final String PLAYER_ROCK = "X";
    private static final String PLAYER_PAPER = "Y";
    private static final String PLAYER_SCISSORS = "Z";
    private static final String LOSE = "X";
    private static final String DRAW = "Y";
    private static final String WIN = "Z";

    static int calculateScore(List<Round> rounds) {
        return rounds.stream().mapToInt(e -> e.opponentShoot.score(e.playerShoot)).sum();
    }

    static class Round {
        private final Shoot opponentShoot;
        private final Shoot playerShoot;

        public Round(String round, boolean advanced) {
            String[] shoots = round.split(DIVIDER);
            this.opponentShoot = Shoot.fromSignifier(shoots[0]);
            if (advanced) {
                this.playerShoot = Shoot.getResultShoot(opponentShoot, shoots[1]);
            } else {
                this.playerShoot = Shoot.fromSignifier(shoots[1]);
            }
        }
    }

    enum Shoot {
        ROCK(4, 8, 3),
        PAPER(1, 5, 9),
        SCISSORS(7, 2, 6);

        private final int rockScore;
        private final int paperScore;
        private final int scissorsScore;

        Shoot(int rockScore, int paperScore, int scissorsScore) {
            this.rockScore = rockScore;
            this.paperScore = paperScore;
            this.scissorsScore = scissorsScore;
        }

        int score(Shoot shoot) {
            switch (shoot) {
                case ROCK:
                    return rockScore;
                case SCISSORS:
                    return scissorsScore;
                case PAPER:
                    return paperScore;
                default:
                    throw new RuntimeException("Enum value not implemented: " + shoot.name());
            }
        }

        static Shoot fromSignifier(String signifier) {
            switch (signifier) {
                case OPPONENT_ROCK:
                case PLAYER_ROCK:
                    return ROCK;
                case OPPONENT_PAPER:
                case PLAYER_PAPER:
                    return PAPER;
                case OPPONENT_SCISSORS:
                case PLAYER_SCISSORS:
                    return SCISSORS;
                default:
                    throw new RuntimeException("Signifier not mapped: " + signifier);
            }
        }

        static Shoot getResultShoot(Shoot opponentShoot, String resultSignifier) {
            if (DRAW.equals(resultSignifier)) {
                return opponentShoot;
            }

            Shoot loseShoot;
            Shoot winShoot;

            switch (opponentShoot) {
                case ROCK:
                    loseShoot = SCISSORS;
                    winShoot = PAPER;
                    break;
                case PAPER:
                    loseShoot = ROCK;
                    winShoot = SCISSORS;
                    break;
                case SCISSORS:
                    loseShoot = PAPER;
                    winShoot = ROCK;
                    break;
                default:
                    throw new RuntimeException("Enum value not implemented: " + opponentShoot.name());
            }

            switch (resultSignifier) {
                case LOSE:
                    return loseShoot;
                case WIN:
                    return winShoot;
                default:
                    throw new RuntimeException("Signifier not mapped: " + resultSignifier);
            }
        }
    }
}
