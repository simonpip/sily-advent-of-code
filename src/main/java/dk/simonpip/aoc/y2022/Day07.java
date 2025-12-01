package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {
    private static final int MAX_SMALL_DIR_SIZE = 100000;
    private static final int TOTAL_DISK_SPACE = 70000000;
    private static final int REQUIRED_DISK_SPACE = 30000000;

    static int findSizeOfSmallDirectories(List<String> log) {
        Node root = convertToTree(log);

        return flatMap(root).stream()
                .filter(e -> e instanceof Directory && e.size() < MAX_SMALL_DIR_SIZE)
                .mapToInt(Node::size)
                .sum();
    }

    static int findSmallestValidDirectory(List<String> log) {
        Node root = convertToTree(log);

        int requiredSpace = REQUIRED_DISK_SPACE - (TOTAL_DISK_SPACE - root.size());

        return flatMap(root).stream()
                .filter(e -> e instanceof Directory && e.size() > requiredSpace)
                .mapToInt(Node::size)
                .min()
                .orElseThrow(() -> new RuntimeException("No node matches predicate"));
    }

    private static Node convertToTree(List<String> log) {
        // Always starts with root
        return convertToDirectory("/", log, 1).getB();
    }

    private static Pair<Integer, Node> convertToDirectory(String name, List<String> log, int index) {
        List<Node> nodes = new ArrayList<>();
        for (int i = index; i < log.size(); i++) {
            String logLine = log.get(i);

            if ("$ cd ..".equals(logLine)) {
                return new Pair<>(i, new Directory(name, nodes));
            } else if ("$ cd".equals(logLine.substring(0, 4))) {
                Pair<Integer, Node> pair = convertToDirectory(
                        logLine.substring(5),
                        log,
                        i + 1);
                nodes.add(pair.getB());
                i = pair.getA();
            } else if (!"$ ls".equals(logLine) && !"dir".equals(logLine.substring(0, 3))) {
                nodes.add(convertToFile(logLine));
            }
        }
        return new Pair<>(log.size(), new Directory(name, nodes));
    }

    private static Node convertToFile(String logLine) {
        Pattern pattern = Pattern.compile("(\\d+) ([a-z.]+)");
        Matcher matcher = pattern.matcher(logLine);
        if (!matcher.matches()) {
            throw new RuntimeException("File line unexpectedly did not contain file: " + logLine);
        }
        String filename = matcher.group(2);
        int size = Integer.parseInt(matcher.group(1));

        return new File(filename, size);
    }

    private static List<Node> flatMap(Node node) {
        Queue<Node> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(node);

        List<Node> nodes = new ArrayList<>();
        nodes.add(node);
        while (!nodeQueue.isEmpty()) {
            Node current = nodeQueue.poll();
            nodeQueue.addAll(current.branches());
            nodes.addAll(current.branches());
        }

        return nodes;
    }

    private static abstract class Node {
        private final String name;

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        abstract int size();

        abstract List<Node> branches();
    }

    private static class Directory extends Node {
        private final List<Node> nodes;
        private int sizeCache;
        private boolean sizeCalculated;

        public Directory(String name, List<Node> nodes) {
            super(name);
            this.nodes = nodes;
        }

        @Override
        public int size() {
            if (!sizeCalculated) {
                sizeCache = nodes.stream().mapToInt(Node::size).sum();
                sizeCalculated = true;
            }
            return sizeCache;
        }

        @Override
        List<Node> branches() {
            return nodes;
        }
    }

    private static class File extends Node {
        private final int size;

        public File(String name, int size) {
            super(name);
            this.size = size;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        List<Node> branches() {
            return new ArrayList<>();
        }
    }
}
