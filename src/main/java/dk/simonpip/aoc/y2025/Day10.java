package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.Pair;
import dk.simonpip.aoc.util.StreamUtil;
import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Variable;

import java.util.*;

public class Day10 {
    public static int solveFirst(List<Indicator> indicators) {
        return indicators.stream().mapToInt(Day10::solveFirst).sum();
    }

    private static int solveFirst(Indicator indicator) {
        int[][] buttons = indicator.buttons;
        boolean[] lights = indicator.lights;

        Queue<Pair<boolean[], boolean[]>> states = new ArrayDeque<>();
        states.add(new Pair<>(
                new boolean[lights.length],
                new boolean[buttons.length]));
        while (!states.isEmpty()) {
            Pair<boolean[], boolean[]> state = states.poll();
            if (StreamUtil.stream(state.getB()).allMatch(b -> b)) {
                // All buttons turned on
                continue;
            }

            for (int i = 0; i < buttons.length; i++) {
                if (state.getB()[i]) {
                    // Already turned on
                    continue;
                }

                boolean[] newLights = Arrays.copyOf(state.getA(), lights.length);
                for (int lightIndex : buttons[i]) {
                    newLights[lightIndex] = !newLights[lightIndex];
                }

                if (Arrays.equals(lights, newLights)) {
                    // Count number of buttons, including the last one pressed
                    return StreamUtil.stream(state.getB()).mapToInt(b -> b ? 1 : 0).sum() + 1;
                }

                boolean[] newButtons  = Arrays.copyOf(state.getB(), buttons.length);
                newButtons[i] = true;

                states.add(new Pair<>(newLights, newButtons));
            }
        }
        throw new RuntimeException("No solution found");
    }

    public static int solveSecond(List<Indicator> indicators) {
        return indicators.stream().mapToInt(Day10::solveSecond).sum();
    }

    private static int solveSecond(Indicator indicator) {
        ExpressionsBasedModel model = new ExpressionsBasedModel();

        Variable[] buttonVars = Arrays.stream(indicator.buttons)
                .map(b -> model.addVariable().integer().lower(0).weight(1))
                .toArray(Variable[]::new);

        Expression[] joltageExps = Arrays.stream(indicator.joltage)
                .mapToObj(j -> model.addExpression().upper(j).lower(j))
                .toArray(Expression[]::new);

        for (int i = 0; i < indicator.buttons.length; i++) {
            int[] button = indicator.buttons[i];
            Variable buttonVar = buttonVars[i];
            for (int joltageIndex : button) {
                joltageExps[joltageIndex].set(buttonVar, 1);
            }
        }

        model.minimise();

        return Arrays.stream(buttonVars).mapToInt(var -> Math.round(var.getValue().floatValue())).sum();
    }

    public static class Indicator {
        private final int[][] buttons;
        private final boolean[] lights;
        private final int[] joltage;

        public Indicator(String string) {
            int indexEndBracket = string.indexOf(']');
            int indexStartBrace = string.indexOf('{');

            String requiredIndicatorString = string.substring(1, indexEndBracket);
            String buttonString = string.substring(indexEndBracket + 2, indexStartBrace - 1);
            String joltageString = string.substring(indexStartBrace + 1, string.length() - 1);

            this.buttons = Arrays.stream(buttonString.split(" "))
                    .map(s -> Arrays.stream(s.substring(1, s.length() - 1).split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray())
                    .toArray(int[][]::new);
            this.lights = StreamUtil.toArray(Arrays.stream(requiredIndicatorString.split("")).map("#"::equals));
            this.joltage = Arrays.stream(joltageString.split(","))
                    .mapToInt(Integer::valueOf)
                    .toArray();
        }
    }
}
