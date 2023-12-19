package dk.nuuday.sily.aoc.y2023;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day05 {
    public static long findLowestLocation(SeedMap seedMap) {
        return seedMap.seeds.stream().mapToLong(seedMap::map).min().orElse(Long.MAX_VALUE);
    }

    public static long findLowestLocationByRange(SeedMap seedMap) {
        long min = Long.MAX_VALUE;

        for (int i = 0; i < seedMap.seeds.size(); i += 2) {
            Long seed = seedMap.seeds.get(i);
            Long range = seedMap.seeds.get(i + 1);

            List<long[]> rangeList = seedMap.map(seed, range);

            min = Math.min(min, rangeList.get(0)[0]);
        }

        // Off-by-one issue remains
        return min - 1;
    }

    public static final class SeedMap {
        private final List<Long> seeds;

        private final ValueMap seedToSoil;
        private final ValueMap soilToFertilizer;
        private final ValueMap fertilizerToWater;
        private final ValueMap waterToLight;
        private final ValueMap lightToTemperature;
        private final ValueMap temperatureToHumidity;
        private final ValueMap humidityToLocation;

        public SeedMap(List<String> description) {
            seeds = Arrays.stream(description.get(0).substring(7).split(" "))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            int seedToSoilIndex = description.indexOf("seed-to-soil map:");
            int soilToFertilizerIndex = description.indexOf("soil-to-fertilizer map:");
            int fertilizerToWaterIndex = description.indexOf("fertilizer-to-water map:");
            int waterToLightIndex = description.indexOf("water-to-light map:");
            int lightToTemperatureIndex = description.indexOf("light-to-temperature map:");
            int temperatureToHumidityIndex = description.indexOf("temperature-to-humidity map:");
            int humidityToLocationIndex = description.indexOf("humidity-to-location map:");

            humidityToLocation = new ValueMap(description.subList(humidityToLocationIndex + 1, description.size()), null);
            temperatureToHumidity = new ValueMap(description.subList(temperatureToHumidityIndex + 1, humidityToLocationIndex - 1), humidityToLocation);
            lightToTemperature = new ValueMap(description.subList(lightToTemperatureIndex + 1, temperatureToHumidityIndex - 1), temperatureToHumidity);
            waterToLight = new ValueMap(description.subList(waterToLightIndex + 1, lightToTemperatureIndex - 1), lightToTemperature);
            fertilizerToWater = new ValueMap(description.subList(fertilizerToWaterIndex + 1, waterToLightIndex - 1), waterToLight);
            soilToFertilizer = new ValueMap(description.subList(soilToFertilizerIndex + 1, fertilizerToWaterIndex - 1), fertilizerToWater);
            seedToSoil = new ValueMap(description.subList(seedToSoilIndex + 1, soilToFertilizerIndex - 1), soilToFertilizer);
        }

        private long map(long source) {
            return seedToSoil.map(source);
        }

        private List<long[]> map(long rangeStart, long range) {
            return seedToSoil.map(List.of(new long[] { rangeStart, rangeStart + range }));
        }
    }

    private static final class ValueMap {
        private final List<ValueMapElement> elements;

        private final ValueMap next;

        private ValueMap(List<String> description, ValueMap next) {
            elements = description.stream().map(ValueMapElement::new).sorted(Comparator.comparing(e -> e.sourceStart)).collect(Collectors.toList());
            this.next = next;
        }

        private long map(long source) {
            long mapped = elements.stream()
                    .filter(e -> e.matches(source))
                    .findFirst()
                    .map(valueMapElement -> valueMapElement.map(source))
                    .orElse(source);

            return next == null ? mapped : next.map(mapped);
        }

        private List<long[]> map(List<long[]> rangeList) {
            List<long[]> newRangeList = new ArrayList<>(rangeList.size());

            for (long[] range : rangeList) {
                long[] actualRange = Arrays.copyOf(range, range.length);

                for (ValueMapElement element : elements) {
                    long[] matchedRange = element.match(actualRange[0], actualRange[1]);

                    if (matchedRange != null) {
                        long[] mappedRange = element.map(matchedRange);
                        newRangeList.add(mappedRange);

                        if (matchedRange[0] > actualRange[0]) {
                            // Actual range starts before matched range
                            // As elements are sorted by source, this will never be matched and can be added
                            newRangeList.add(new long[] { actualRange[0], matchedRange[1] - 1 });
                        }

                        if (matchedRange[1] < actualRange[1]) {
                            // Actual range ends after matched range
                            // Remaining range retained for further matching
                            actualRange = new long[] { matchedRange[1] + 1, actualRange[1] };
                        } else {
                            // No more range remains
                            actualRange = null;
                            break;
                        }
                    }
                }

                if (actualRange != null) {
                    newRangeList.add(actualRange);
                }
            }

            newRangeList.sort(Comparator.comparing(e -> e[0]));

            return next == null ? newRangeList : next.map(newRangeList);
        }
    }

    private static final class ValueMapElement {
        private final long sourceStart;
        private final long destinationStart;
        private final long rangeLength;

        private ValueMapElement(String description) {
            long[] values = Arrays.stream(description.split(" ")).mapToLong(Long::parseLong).toArray();

            sourceStart = values[1];
            destinationStart = values[0];
            rangeLength = values[2];
        }

        private boolean matches(long source) {
            long delta = source - sourceStart;

            return delta >= 0 && delta < rangeLength;
        }

        private long map(long source) {
            return destinationStart + (source - sourceStart);
        }

        private long[] match(long rangeStart, long rangeEnd) {
            long startDelta = rangeStart - sourceStart;
            long endDelta = rangeEnd - sourceStart;

            if (startDelta <= 0) {
                if (endDelta >= rangeLength) {
                    // Match range completely encompasses element range
                    return new long[] { sourceStart, sourceStart + rangeLength };
                }
                if (endDelta > 0) {
                    // Match range covers beginning of element range
                    return new long[] { sourceStart, sourceStart + endDelta };
                }
            } else if (startDelta < rangeLength) {
                if (endDelta > rangeLength) {
                    // Match range covers end of element range
                    return new long[] { sourceStart + startDelta, sourceStart + rangeLength };
                }
                if (endDelta > 0) {
                    // Match range covers middle of element range
                    return new long[] { sourceStart + startDelta, sourceStart + endDelta };
                }
            }
            // Match range has no common digits
            return null;
        }

        private long[] map(long[] range) {
            long delta = destinationStart - sourceStart;

            return new long[] { range[0] + delta, range[1] + delta };
        }
    }
}
