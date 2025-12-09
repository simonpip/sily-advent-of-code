package dk.simonpip.aoc.y2025;

import dk.simonpip.aoc.util.Coordinate3D;

import java.util.*;
import java.util.stream.Collectors;

public class Day08 {
    public static long solveFirst(List<Coordinate3D> junctionBoxes, int connections) {
        List<Connection> connectionList = getConnectionsByDistance(junctionBoxes);

        // Select top [connections]
        List<Connection> chosenConnections = connectionList.subList(0, connections);

        // For each connection
        Map<Integer, Integer> circuitMap = new HashMap<>();
        int nextCircuitId = 0;
        for (Connection connection : chosenConnections) {
            nextCircuitId = mapConnection(connection.lhs, connection.rhs, circuitMap, nextCircuitId);
        }

        // Find count of each circuit ID
        List<Long> circuitSizes = circuitMap.values().stream()
                .distinct()
                .map(circuit -> circuitMap.values().stream()
                        .filter(circuit::equals)
                        .count())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return circuitSizes.subList(0, 3).stream().reduce(1L, (a, b) -> a * b);
    }

    public static long solveSecond(List<Coordinate3D> junctionBoxes) {
        List<Connection> connectionList = getConnectionsByDistance(junctionBoxes);

        // For each connection
        Map<Integer, Integer> circuitMap = new HashMap<>();
        int nextCircuitId = 0;
        for (Connection connection : connectionList) {
            int lhs = connection.lhs;
            int rhs = connection.rhs;

            nextCircuitId = mapConnection(lhs, rhs, circuitMap, nextCircuitId);

            if (circuitMap.size() == junctionBoxes.size() || circuitMap.size() == 1) {
                return (long) junctionBoxes.get(lhs).getX() * (long) junctionBoxes.get(rhs).getX();
            }
        }
        throw new RuntimeException("All connections made, still not single network");
    }

    private static List<Connection> getConnectionsByDistance(List<Coordinate3D> junctionBoxes) {
        // For each, find all distances
        List<Connection> connectionList = new ArrayList<>(junctionBoxes.size() * junctionBoxes.size());
        for (int lhs = 0; lhs < junctionBoxes.size() - 1; lhs++) {
            for (int rhs = lhs + 1; rhs < junctionBoxes.size(); rhs++) {
                connectionList.add(new Connection(lhs, junctionBoxes.get(lhs), rhs, junctionBoxes.get(rhs)));
            }
        }

        // Sort by distance
        connectionList.sort(Comparator.comparing(conn -> conn.distance));
        return connectionList;
    }

    private static int mapConnection(int lhs, int rhs, Map<Integer, Integer> circuitMap, int nextCircuitId) {
        if (circuitMap.containsKey(lhs) && circuitMap.containsKey(rhs)) {
            if (circuitMap.get(lhs).equals(circuitMap.get(rhs))) {
                // If both are in same circuit, do nothing
                return nextCircuitId;
            }
            // If both are in separate circuits, remap all boxes mapped to highest circuit ID to lowest circuit ID
            int circuitToKeep = circuitMap.get(lhs);
            int circuitToRewire = circuitMap.get(rhs);
            List<Integer> idsToRewire = circuitMap.entrySet().stream()
                    .filter(entry -> entry.getValue() == circuitToRewire)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            idsToRewire.forEach(id -> circuitMap.put(id, circuitToKeep));
        } else if (circuitMap.containsKey(lhs)) {
            // If one is in circuit, add other to same ID
            circuitMap.put(rhs, circuitMap.get(lhs));
        } else if (circuitMap.containsKey(rhs)) {
            circuitMap.put(lhs, circuitMap.get(rhs));
        } else {
            // If neither is in circuit, pull next circuit ID, map both to this
            circuitMap.put(lhs, nextCircuitId);
            circuitMap.put(rhs, nextCircuitId);
            return nextCircuitId + 1;
        }
        return nextCircuitId;
    }

    public static class Connection {
        private final int lhs;
        private final Coordinate3D lhsCoordinate3D;
        private final int rhs;
        private final Coordinate3D rhsCoordinate3D;
        private final double distance;

        public Connection(int lhs, Coordinate3D lhsCoord, int rhs, Coordinate3D rhsCoord) {
            this.lhs = lhs;
            this.lhsCoordinate3D = lhsCoord;
            this.rhs = rhs;
            this.rhsCoordinate3D = rhsCoord;

            this.distance = lhsCoord.distance(rhsCoord);
        }

        @Override
        public String toString() {
            return "Connection{" +
                    "lhs=" + lhsCoordinate3D +
                    ", rhs=" + rhsCoordinate3D +
                    ", distance=" + distance +
                    '}';
        }
    }
}
