package dk.nuuday.sily.aoc.y2023;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day03 {
    public static int findActiveParts(Schematic schematic) {
        return schematic.partNumbers.stream()
                .filter(e -> schematic.symbols.stream()
                        .anyMatch(f -> touches(e, f)))
                .mapToInt(e -> e.partNumber)
                .sum();
    }

    public static int findGearRatios(Schematic schematic) {
        return schematic.symbols.stream()
                .mapToInt(e -> {
                    List<PartNumber> partNumbers = schematic.partNumbers.stream().filter(f -> touches(f, e)).collect(Collectors.toList());

                    if (partNumbers.size() < 2) {
                        return 0;
                    }

                    return partNumbers.stream().mapToInt(f -> f.partNumber).reduce(1, (a, b) -> a * b);
                })
                .sum();
    }

    private static boolean touches(PartNumber partNumber, Symbol symbol) {
        return symbol.x >= partNumber.startX - 1
                && symbol.x <= partNumber.startX + partNumber.length()
                && symbol.y >= partNumber.y - 1
                && symbol.y <= partNumber.y + 1;
    }

    public static final class Schematic {
        private final List<PartNumber> partNumbers;
        private final List<Symbol> symbols;

        public Schematic(List<String> schematic) {
            List<PartNumber> partNumbersTemp = new ArrayList<>();
            List<Symbol> symbolsTemp = new ArrayList<>();

            StringBuilder partNumberBuilder = new StringBuilder();
            for (int i = 0; i < schematic.size(); i++) {
                String line = schematic.get(i);

                for (int j = 0; j < line.length(); j++) {
                    char symbol = line.charAt(j);

                    if (symbol >= '0' && symbol <= '9') {
                        partNumberBuilder.append(symbol);
                    } else {
                        if (partNumberBuilder.length() > 0) {
                            String partNumberCandidate = partNumberBuilder.toString();
                            partNumbersTemp.add(new PartNumber(Integer.parseInt(partNumberCandidate), j - partNumberCandidate.length(), i));
                            partNumberBuilder = new StringBuilder();
                        }

                        if (symbol != '.') {
                            symbolsTemp.add(new Symbol(symbol, j, i));
                        }
                    }
                }

                if (partNumberBuilder.length() > 0) {
                    String partNumberCandidate = partNumberBuilder.toString();
                    partNumbersTemp.add(new PartNumber(Integer.parseInt(partNumberCandidate), line.length() - partNumberCandidate.length(), i));
                    partNumberBuilder = new StringBuilder();
                }
            }

            partNumbers = ImmutableList.copyOf(partNumbersTemp);
            symbols = ImmutableList.copyOf(symbolsTemp);
        }
    }

    private static final class PartNumber {
        private final int partNumber;
        private final int startX;
        private final int y;

        private PartNumber(int partNumber, int startX, int y) {
            this.partNumber = partNumber;
            this.startX = startX;
            this.y = y;
        }

        private int length() {
            if (partNumber == 0) {
                return 1;
            }

            return (int) (Math.log10(partNumber) + 1);
        }
    }

    private static final class Symbol {
        private final char symbol;
        private final int x;
        private final int y;

        private Symbol(char symbol, int x, int y) {
            this.symbol = symbol;
            this.x = x;
            this.y = y;
        }
    }
}
