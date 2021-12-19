package dk.nuuday.sily.aoc.y2020;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

class Day16 {
    private Day16() {
    }

    static int findErrorRate(DataSet dataSet) {
        List<Interval> mergedIntervals = mergeIntervals(
                dataSet.fields.stream()
                        .flatMap(e -> e.allowedIntervals.stream())
                        .collect(Collectors.toList()));

        return dataSet.nearbyTickets.stream().mapToInt(e -> findErrorRate(e, mergedIntervals)).sum();
    }

    private static int findErrorRate(int[] ticket, List<Interval> allowedIntervals) {
        return Arrays.stream(ticket)
                .filter(e -> allowedIntervals.stream()
                        .noneMatch(f -> f.isAllowed(e)))
                .sum();
    }

    static long findProductOfMatchingFields(DataSet dataSet, String requirement) {
        List<Interval> mergedIntervals = mergeIntervals(
                dataSet.fields.stream()
                        .flatMap(e -> e.allowedIntervals.stream())
                        .collect(Collectors.toList()));

        FieldList[] fieldArray = new FieldList[dataSet.ticket.length];
        Arrays.setAll(fieldArray, i -> new FieldList(dataSet));

        removeNonmatches(fieldArray, dataSet.ticket);

        dataSet.nearbyTickets.stream()
                .filter(e -> isAllowed(e, mergedIntervals))
                .forEach(e -> removeNonmatches(fieldArray, e));

        sanititizeFieldLists(fieldArray);

        return getProductOfMatchingFields(dataSet, requirement, fieldArray);
    }

    private static void sanititizeFieldLists(FieldList[] fieldArray) {
        List<FieldList> fieldLists = Arrays.stream(fieldArray)
                .sorted(Comparator.comparing(e -> e.fields.size()))
                .collect(Collectors.toList());

        for (int i = 0; i < fieldLists.size(); i++) {
            FieldList fieldList = fieldLists.get(i);
            if (fieldList.fields.size() <= 1) {
                continue;
            }

            for (int j = 0; j < i; j++) {
                FieldList other = fieldLists.get(j);
                fieldList.fields.removeAll(other.fields);
            }
        }
    }

    private static void removeNonmatches(FieldList[] fieldArray, int[] ticket) {
        for (int i = 0; i < ticket.length; i++) {
            fieldArray[i].removeNonmatches(ticket[i]);
        }
    }

    private static long getProductOfMatchingFields(DataSet dataSet, String requirement, FieldList[] fieldArray) {
        long product = 1;
        for (int i = 0; i < dataSet.ticket.length; i++) {
            if (fieldArray[i].fields.get(0).name.contains(requirement)) {
                product *= (long) dataSet.ticket[i];
            }
        }
        return product;
    }

    private static List<Interval> mergeIntervals(List<Interval> unmerged) {
        List<Interval> sorted = new ArrayList<>(unmerged);
        sorted.sort(Comparator.comparing(e -> e.min));

        Deque<Interval> stack = new ArrayDeque<>(sorted.size());
        stack.add(sorted.get(0));
        sorted.remove(0);

        for (Interval interval : sorted) {
            Interval peeked = stack.peek();
            if (interval.min <= peeked.max + 1) {
                // New interval start overlaps or abuts popped interval
                if (interval.max > peeked.max) {
                    // New interval extends popped interval, remove peeked and add update.
                    stack.pop();
                    stack.push(new Interval(peeked.min, interval.max));
                }
            } else {
                stack.push(interval);
            }
        }

        return new ArrayList<>(stack);
    }

    private static boolean isAllowed(int[] ticket, List<Interval> allowedIntervals) {
        return Arrays.stream(ticket).noneMatch(
                e -> allowedIntervals.stream().noneMatch(f -> f.isAllowed(e)));
    }

    static final class DataSet {
        private static final String FIELD_REGEX = "[a-z ]+: [0-9\\-or ]+";
        private static final String TICKET_REGEX = "^[0-9,]+$";

        private final List<Field> fields;
        private final int[] ticket;
        private final List<int[]> nearbyTickets;

        public DataSet(List<String> lines) {
            List<Field> fields = new ArrayList<>();
            int[] ticket = null;
            List<int[]> nearbyTickets = new ArrayList<>();

            for (String line : lines) {
                if (line.matches(FIELD_REGEX)) {
                    fields.add(new Field(line));
                } else if (line.matches(TICKET_REGEX)) {
                    int[] ticketCandidate = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                    if (ticket == null) {
                        ticket = ticketCandidate;
                    } else {
                        nearbyTickets.add(ticketCandidate);
                    }
                }
            }

            this.fields = fields;
            this.ticket = ticket;
            this.nearbyTickets = nearbyTickets;
        }
    }

    private static final class FieldList {
        private final List<Field> fields;

        private FieldList(DataSet dataSet) {
            this.fields = new ArrayList<>(dataSet.fields);
        }

        private void removeNonmatches(int value) {
            fields.removeIf(e -> !e.isAllowed(value));
        }
    }

    private static final class Field {
        private final String name;
        private final List<Interval> allowedIntervals;

        private Field(String line) {
            int endOfNameIndex = line.indexOf(':');
            this.name = line.substring(0, endOfNameIndex);

            this.allowedIntervals = Arrays
                    .stream(line.substring(endOfNameIndex + 2).split(" or "))
                    .map(Interval::new)
                    .collect(Collectors.toList());
        }

        private boolean isAllowed(int value) {
            return allowedIntervals.stream().anyMatch(e -> e.isAllowed(value));
        }
    }

    private static final class Interval {
        private final int min;
        private final int max;

        private Interval(String string) {
            String[] numbers = string.split("-");
            this.min = Integer.parseInt(numbers[0]);
            this.max = Integer.parseInt(numbers[1]);
        }

        private Interval(int min, int max) {
            this.min = min;
            this.max = max;
        }

        private boolean isAllowed(int value) {
            return min <= value && max >= value;
        }
    }
}
