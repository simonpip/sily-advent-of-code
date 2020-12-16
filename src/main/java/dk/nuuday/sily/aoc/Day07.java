package dk.nuuday.sily.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day07 {
    private Day07() {
    }

    static int findCountOfOuterBags(List<String> descriptions, String innerBag) {
        Map<String, Bag> bagMap = decodeBags(descriptions);

        Set<String> outermost = new HashSet<>();
        Queue<Bag> queue = new LinkedList<>();
        queue.add(bagMap.get(innerBag));
        while (!queue.isEmpty()) {
            Bag bag = queue.poll();
            outermost.add(bag.color);
            queue.addAll(bag.parents);
        }
        // Remove innerBag from count as it needs to be within at least one other bag
        return outermost.size() - 1;
    }

    static int sumInnerBags(List<String> descriptions, String outerBag) {
        Map<String, Bag> bagMap = decodeBags(descriptions);

        int bags = 0;
        Queue<BagAmount> queue = new LinkedList<>();
        queue.add(new BagAmount(1, bagMap.get(outerBag)));
        while (!queue.isEmpty()) {
            BagAmount bag = queue.poll();
            bags += bag.amount;
            List<BagAmount> multiplied = bag.bag.children.stream()
                    .map(e -> new BagAmount(e.amount * bag.amount, e.bag))
                    .collect(Collectors.toList());
            queue.addAll(multiplied);
        }
        // Remove outerBag from count as we only want to count bags inside
        return bags - 1;
    }

    private static Map<String, Bag> decodeBags(List<String> descriptions) {
        Map<String, Bag> bagMap = new HashMap<>();

        descriptions.forEach(e -> mapBag(e, bagMap));

        return bagMap;
    }

    private static void mapBag(String description, Map<String, Bag> bagMap) {
        Pattern pattern = Pattern.compile("([0-9]*)( )?([a-z]+ [a-z]+) bag");

        Matcher matcher = pattern.matcher(description);
        matcher.find();
        Bag parentBag = findBag(matcher.group(3), bagMap);

        while (matcher.find()) {
            if (matcher.group(1).isEmpty()) {
                continue;
            }
            int childAmount = Integer.parseInt(matcher.group(1));
            String childColor = matcher.group(3);

            Bag childBag = findBag(childColor, bagMap);

            childBag.parents.add(parentBag);
            parentBag.children.add(new BagAmount(childAmount, childBag));
        }
    }

    private static Bag findBag(String color, Map<String, Bag> bagMap) {
        return bagMap.computeIfAbsent(color, k -> new Bag(color));
    }

    static final class Bag {
        private final String color;
        private final List<Bag> parents;
        private final List<BagAmount> children;

        public Bag(String color) {
            this.color = color;

            parents = new ArrayList<>();
            children = new ArrayList<>();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(color);
            sb.append(" bags contain ");
            for (int i = 0; i < children.size(); i++) {
                BagAmount child = children.get(i);

                sb.append(child.amount);
                sb.append(" ");
                sb.append(child.bag.color);
                sb.append(" bag");
                if (child.amount > 1) {
                    sb.append("s");
                }
                if (i < children.size() - 1) {
                    sb.append(", ");
                }
            }
            if (children.isEmpty()) {
                sb.append("no other bags");
            }
            sb.append(".");

            return sb.toString();
        }
    }

    static final class BagAmount {
        private final int amount;
        private final Bag bag;

        public BagAmount(int amount, Bag bag) {
            this.amount = amount;
            this.bag = bag;
        }
    }
}
