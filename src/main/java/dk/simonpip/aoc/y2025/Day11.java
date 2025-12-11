package dk.simonpip.aoc.y2025;

import java.util.*;
import java.util.stream.Collectors;

public class Day11 {
    public static long solveFirst(Map<String, Device> devices) {
        return solve(devices.get("you"), devices.get("out"));
    }

    public static long solveSecond(Map<String, Device> devices) {
        long fftPaths = solve(devices.get("svr"), devices.get("fft"));

        long fftToDacPaths = solve(devices.get("fft"), devices.get("dac"));

        long dacOutPaths = solve(devices.get("dac"), devices.get("out"));

        return fftPaths * fftToDacPaths * dacOutPaths;
    }

    private static long solve(Device start, Device end) {
        Map<String, Long> pathMap = new HashMap<>();
        pathMap.put(start.id, 1L);

        return pathsTo(end, pathMap);
    }

    private static long pathsTo(Device device, Map<String, Long> pathMap) {
        if (pathMap.containsKey(device.id)) {
            return pathMap.get(device.id);
        }

        long paths = 0;
        for (Device fromDevice : device.from) {
            paths += pathsTo(fromDevice, pathMap);
        }
        pathMap.put(device.id, paths);

        return paths;
    }

    public static Map<String, Device> convert(List<String> lines) {
        Map<String, List<String>> toMap = new HashMap<>(lines.size());
        for (String line : lines) {
            String id = line.substring(0, 3);
            List<String> to = Arrays.stream(line.substring(5).split(" ")).collect(Collectors.toList());

            toMap.put(id, to);
        }

        Set<String> allDevices = new HashSet<>(toMap.keySet());
        allDevices.addAll(toMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList()));

        Map<String, List<String>> fromMap = new HashMap<>(lines.size());
        for (String id : allDevices) {
            List<String> from = toMap.entrySet().stream().filter(e -> e.getValue().contains(id)).map(Map.Entry::getKey).collect(Collectors.toList());

            fromMap.put(id, from);
        }

        Map<String, Device> deviceMap = new HashMap<>(lines.size());
        for (String id : allDevices) {
            deviceMap.put(id, new Device(id));
        }
        for (Device device : deviceMap.values()) {
            device.from.addAll(fromMap.get(device.id).stream().map(deviceMap::get).collect(Collectors.toList()));
        }

        return deviceMap;
    }

    public static class Device {
        private final String id;
        private final List<Device> from;

        public Device(String id) {
            this.id = id;
            this.from = new ArrayList<>();
        }
    }
}
