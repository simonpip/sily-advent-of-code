package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.Coordinate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day15 {
    private static final Pattern READING_PATTERN = Pattern.compile(
            "Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");

    static int countNoBeaconPoints(List<Reading> readings, int y) {
        if (readings.isEmpty()) {
            throw new RuntimeException("Empty readings list supplied");
        }
        int minX = readings.stream().mapToInt(e -> e.sensor.getX() - e.distance).min().getAsInt();
        int maxX = readings.stream().mapToInt(e -> e.sensor.getX() + e.distance).max().getAsInt();

        Set<Coordinate> knownBeacons = readings.stream().map(e -> e.beacon).collect(Collectors.toSet());
        int count = 0;
        for (int x = minX; x <= maxX; x++) {
            Coordinate candidate = new Coordinate(x, y);
            if (knownBeacons.contains(candidate)) {
                continue;
            }
            if (readings.stream().anyMatch(e -> manhattanDistance(e.sensor, candidate) <= e.distance)) {
                count++;
            }
        }
        return count;
    }

    static long findHiddenBeacon(List<Reading> readings, int maxCoord) {
        Set<Coordinate> borders = readings.stream()
                .flatMap(e -> findBorders(e, maxCoord).stream())
                .collect(Collectors.toSet());

        for (Coordinate border : borders) {
            if (readings.stream().noneMatch(reading -> manhattanDistance(reading.sensor, border) <= reading.distance)) {
                return (long)border.getX() * 4000000 + (long)border.getY();
            }
        }
        throw new RuntimeException("No hidden beacon found");
    }

    private static Set<Coordinate> findBorders(Reading reading, int maxCoord) {
        Set<Coordinate> borders = new HashSet<>();
        // Left point to bottom point
        for (int x = Math.max(reading.sensor.getX() - reading.distance - 1, 0); x < reading.sensor.getX(); x++) {
            // For each x-step to the left, step y one down
            int y = reading.sensor.getY() + x - (reading.sensor.getX() - reading.distance - 1);
            if (y > maxCoord) {
                break;
            }

            borders.add(new Coordinate(x, y));
        }

        // Bottom point to right point
        for (int y = Math.max(reading.sensor.getY() - reading.distance - 1, 0); y < reading.sensor.getY(); y++) {
            // For each y-step down, step x to the left
            int x = reading.sensor.getX() + y - (reading.sensor.getY() - reading.distance - 1);
            if (x > maxCoord) {
                break;
            }

            borders.add(new Coordinate(x, y));
        }

        // Right point to top point
        for (int x = Math.min(reading.sensor.getX() + reading.distance + 1, maxCoord); x > reading.sensor.getX(); x--) {
            // For each x-step to the right, step y one up
            int y = reading.sensor.getY() - x - (reading.sensor.getX() + reading.distance + 1);
            if (y < 0) {
                break;
            }

            borders.add(new Coordinate(x, y));
        }

        // Top point to left point
        for (int y = reading.sensor.getY() + reading.distance + 1; y > reading.sensor.getY(); y--) {
            // For each y-step up, step x to the left
            int x = reading.sensor.getX() - y - (reading.sensor.getY() + reading.distance + 1);
            if (x < 0) {
                break;
            }

            borders.add(new Coordinate(x, y));
        }

        return borders;
    }

    static int manhattanDistance(Coordinate p1, Coordinate p2) {
        return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
    }

    static class Reading {
        private final Coordinate sensor;
        private final Coordinate beacon;
        private final int distance;

        public Reading(String reading) {
            Matcher matcher = READING_PATTERN.matcher(reading);
            if (!matcher.matches() || matcher.groupCount() < 4)
                throw new RuntimeException("Line does not contain valid reading");

            sensor = new Coordinate(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            beacon = new Coordinate(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));

            distance = manhattanDistance(sensor, beacon);
        }
    }
}
