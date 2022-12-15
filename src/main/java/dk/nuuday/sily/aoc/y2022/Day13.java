package dk.nuuday.sily.aoc.y2022;

import java.util.*;
import java.util.stream.Collectors;

public class Day13 {
    static int countOrderedPacketPairs(List<String> dataStream) {
        int count = 0;
        for (int i = 0; i < dataStream.size(); i += 3) {
            if (isPacketPairInOrder(dataStream.get(i), dataStream.get(i + 1))) {
                count += (i / 3) + 1;
            }
        }
        return count;
    }

    static int orderPacketPairs(List<String> dataStream) {
        String decoderItem1 = "[[2]]";
        String decoderItem2 = "[[6]]";

        List<String> filteredStream = dataStream.stream().filter(e -> !e.isEmpty()).collect(Collectors.toList());
        filteredStream.add(decoderItem1);
        filteredStream.add(decoderItem2);

        filteredStream.sort((a, b) -> isPacketPairInOrder(a, b) ? -1 : 1);

        int decodeSignal = 1;
        for (int i = 0; i < filteredStream.size(); i++) {
            String packet = filteredStream.get(i);
            if (packet.equals(decoderItem1) || packet.equals(decoderItem2)) {
                decodeSignal *= i + 1;
            }
        }
        return decodeSignal;
    }

    private static boolean isPacketPairInOrder(String left, String right) {
        Stack<Item> leftStack = new Stack<>();
        leftStack.add(convert(left));
        Stack<Item> rightStack = new Stack<>();
        rightStack.add(convert(right));

        while (!leftStack.empty() && !rightStack.empty()) {
            Item leftItem = leftStack.peek();
            Item rightItem = rightStack.peek();
            if (leftItem.single() && rightItem.single()) {
                leftStack.pop(); rightStack.pop();
                if (leftItem.nextUnboxed() != rightItem.nextUnboxed()) {
                    return leftItem.nextUnboxed() < rightItem.nextUnboxed();
                }
            } else if (leftItem.single()) {
                leftStack.pop();
                leftStack.add(new ListItem(List.of(leftItem)));
            } else if (rightItem.single()) {
                rightStack.pop();
                rightStack.add(new ListItem(List.of(rightItem)));
            } else {
                // Both lists
                if (!leftItem.empty() && !rightItem.empty()) {
                    leftStack.add(leftItem.next());
                    rightStack.add(rightItem.next());
                } else if (leftItem.empty() && rightItem.empty()) {
                    leftStack.pop(); rightStack.pop();
                } else {
                    // The two list items are different lengths; to be in order, the left item has to be shorter
                    return leftItem.empty();
                }
            }
        }
        // Only one stack is empty; to be in order, the left list had to be shorter
        return leftStack.empty();
    }

    private static ListItem convert(String packetSide) {
        Stack<List<Item>> listStack = new Stack<>();
        StringBuilder numberSb = new StringBuilder();
        for (int i = 0; i < packetSide.length(); i++) {
            char next = packetSide.charAt(i);
            if (next == '[') {
                listStack.add(new ArrayList<>());
            } else if (next == ']') {
                String number = numberSb.toString();
                numberSb = new StringBuilder();

                if (!number.isEmpty()) {
                    listStack.peek().add(new SingleItem(Integer.parseInt(number)));
                }

                ListItem finishedItem = new ListItem(listStack.pop());
                if (listStack.empty()) {
                    return finishedItem;
                } else {
                    listStack.peek().add(finishedItem);
                }
            } else if (next == ',') {
                String number = numberSb.toString();
                numberSb = new StringBuilder();

                if (!number.isEmpty()) {
                    listStack.peek().add(new SingleItem(Integer.parseInt(number)));
                }
            } else {
                numberSb.append(next);
            }
        }

        throw new RuntimeException("More lists opened than closed");
    }

    private interface Item {
        boolean single();

        boolean empty();

        Item next();

        int nextUnboxed();
    }

    private static class SingleItem implements Item {
        private final int item;

        public SingleItem(int item) {
            this.item = item;
        }

        @Override
        public boolean single() {
            return true;
        }

        @Override
        public boolean empty() {
            return false;
        }

        @Override
        public Item next() {
            return this;
        }

        @Override
        public int nextUnboxed() {
            return item;
        }
    }

    private static class ListItem implements Item {
        private final Queue<Item> items;

        public ListItem(List<Item> items) {
            this.items = new ArrayDeque<>(items);
        }

        @Override
        public boolean single() {
            return false;
        }

        @Override
        public boolean empty() {
            return items.isEmpty();
        }

        @Override
        public Item next() {
            return items.poll();
        }

        @Override
        public int nextUnboxed() {
            return items.poll().nextUnboxed();
        }
    }
}
