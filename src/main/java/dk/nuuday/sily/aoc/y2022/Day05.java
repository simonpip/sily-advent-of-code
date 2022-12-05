package dk.nuuday.sily.aoc.y2022;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day05 {
    private static final int ASCII_NUMBERS_START = 48;
    static String getTopCrates(WorkOrder workOrder, boolean advancedCrane) {
        if (advancedCrane) {
            Stack<Character> crane = new Stack<>();
            return getTopCrates(workOrder, crane::empty, crane::add, crane::pop);
        } else {
            Queue<Character> crane = new ArrayDeque<>();
            return getTopCrates(workOrder, crane::isEmpty, crane::add, crane::poll);
        }
    }

    private static String getTopCrates(WorkOrder workOrder,
                                       Supplier<Boolean> emptySupplier,
                                       Consumer<Character> crateConsumer,
                                       Supplier<Character> crateSupplier) {
        for (Order order : workOrder.orders) {
            Stack<Character> fromStack = workOrder.stacks.get(order.from - 1);
            for (int i = 0; i < order.amount; i++) {
                crateConsumer.accept(fromStack.pop());
            }

            Stack<Character> toStack = workOrder.stacks.get(order.to - 1);
            while (!emptySupplier.get()) {
                toStack.add(crateSupplier.get());
            }
        }

        StringBuilder sb = new StringBuilder(workOrder.stacks.size());
        workOrder.stacks.forEach(e -> sb.append(e.pop()));

        return sb.toString();
    }

    static class WorkOrder {
        private final List<Stack<Character>> stacks;
        private final List<Order> orders;

        public WorkOrder(List<String> lines) {
            int linebreak = lines.indexOf("");

            String indexLine = lines.get(linebreak - 1);
            int stackCount = indexLine.charAt(indexLine.length() - 1) - ASCII_NUMBERS_START;

            stacks = new ArrayList<>(stackCount);
            for (int i = 0; i < stackCount; i++) {
                stacks.add(new Stack<>());
            }

            for (int i = linebreak - 2; i >= 0; i--) {
                String stackLine = lines.get(i);

                for (int j = 0; j < stackCount && j * 4 + 1 < stackLine.length(); j++) {
                    char found = stackLine.charAt(j * 4 + 1);
                    if (found != ' ') {
                        stacks.get(j).add(found);
                    }
                }
            }

            orders = lines
                    .subList(linebreak + 1, lines.size())
                    .stream().map(Day05.Order::new)
                    .collect(Collectors.toList());
        }
    }

    private static class Order {
        private final int amount;
        private final int from;
        private final int to;

        public Order(String order) {
            Pattern pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
            Matcher matcher = pattern.matcher(order);

            if (!matcher.matches()) throw new RuntimeException("Order line is malformed: " + order);

            amount = Integer.parseInt(matcher.group(1));
            from = Integer.parseInt(matcher.group(2));
            to = Integer.parseInt(matcher.group(3));
        }
    }
}
