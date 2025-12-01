package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day14 {

    public static final Coordinate SAND_ENTRYPOINT = new Coordinate(500, 0);

    static int measureSand(List<Line> lines, boolean bottomExists) {
        Set<Coordinate> coordinates = lines.stream()
                .flatMap(e -> e.getAllCoordinates().stream())
                .collect(Collectors.toSet());

        int highestY = coordinates.stream()
                .mapToInt(Coordinate::getY)
                .max()
                .orElseThrow(() -> new RuntimeException("No coordinates found"));

        Set<Coordinate> totalCoords = new HashSet<>(coordinates);
        Coordinate sand = SAND_ENTRYPOINT;
        while ((!bottomExists && sand.getY() < highestY)
                || (bottomExists && !totalCoords.contains(SAND_ENTRYPOINT))) {
            sand = modifySand(totalCoords, sand, bottomExists, highestY);
        }

        return totalCoords.size() - coordinates.size();
    }

    private static Coordinate modifySand(Set<Coordinate> totalCoords, Coordinate sand, boolean bottomExists, int highestY) {
        if (bottomExists && sand.getY() == highestY + 1) {
            totalCoords.add(sand);
            return SAND_ENTRYPOINT;
        } else if (!totalCoords.contains(new Coordinate(sand.getX(), sand.getY() + 1))) {
            return new Coordinate(sand.getX(), sand.getY() + 1);
        } else if (!totalCoords.contains(new Coordinate(sand.getX() - 1, sand.getY() + 1))) {
            return new Coordinate(sand.getX() - 1, sand.getY() + 1);
        } else if (!totalCoords.contains(new Coordinate(sand.getX() + 1, sand.getY() + 1))) {
            return new Coordinate(sand.getX() + 1, sand.getY() + 1);
        } else {
            totalCoords.add(sand);
            return SAND_ENTRYPOINT;
        }
    }
    
    static class Line {
        private final List<Coordinate> coordinates;

        public Line(String line) {
            String[] split = line.split(" -> ");
            coordinates = new ArrayList<>(split.length);
            for (String s : split) {
                String[] subsplit = s.split(",");
                coordinates.add(new Coordinate(Integer.parseInt(subsplit[0]), Integer.parseInt(subsplit[1])));
            }
        }
        
        public Set<Coordinate> getAllCoordinates() {
            Set<Coordinate> allCoordSet = new HashSet<>();
            for (int i = 1; i < coordinates.size(); i++) {
                allCoordSet.addAll(getAllCoordinates(coordinates.get(i - 1), coordinates.get(i)));
            }
            return allCoordSet;
        }

        private static Set<Coordinate> getAllCoordinates(Coordinate start, Coordinate end) {
            Set<Coordinate> allCoordSet = new HashSet<>();
            if (start.getX() == end.getX()) {
                for (int y = Math.min(start.getY(), end.getY()); y <= Math.max(start.getY(), end.getY()); y++) {
                    allCoordSet.add(new Coordinate(start.getX(), y));
                }
                return allCoordSet;
            } else if (start.getY() == end.getY()) {
                for (int x = Math.min(start.getX(), end.getX()); x <= Math.max(start.getX(), end.getX()); x++) {
                    allCoordSet.add(new Coordinate(x, start.getY()));
                }
                return allCoordSet;
            }
            throw new RuntimeException("Line is not either horizontal or vertical");
        }
    }
}
