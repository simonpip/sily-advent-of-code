package dk.nuuday.sily.aoc.y2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Day13 {
    private Day13() {
    }

    static int findEarliestBus(Schedule schedule) {
        int waitTime = schedule.departTime;
        int route = 0;
        for (ScheduleItem scheduleItem : schedule.routes) {
            int routeCandidate = scheduleItem.route;
            int candidateWaitTime = routeCandidate - schedule.departTime % routeCandidate;

            if (candidateWaitTime < waitTime) {
                waitTime = candidateWaitTime;
                route = routeCandidate;
            }
        }
        return route * waitTime;
    }

    static long findEarliestSequence(Schedule schedule) {
        long[] routes = schedule.routes.stream().mapToLong(e -> e.route).toArray();
        long[] remainders = schedule.routes.stream().mapToLong(e -> e.remainder).toArray();

        long product = Arrays.stream(routes).reduce(1, (l, r) -> l * r);
        long[] partialProducts = Arrays.stream(routes).map(e -> product / e).toArray();

        long[] inverse = new long[routes.length];
        for (int i = 0; i < routes.length; i++) {
            long route = routes[i];
            long partialProduct = partialProducts[i];

            inverse[i] = computeInverse(partialProduct, route);
        }

        long sum = 0;
        for (int i = 0; i < routes.length; i++) {
            sum += partialProducts[i] * inverse[i] * remainders[i];
        }

        return sum % product;
    }

    private static long computeInverse(long a, long b) {
        long m = b, t, q;
        long x = 0, y = 1;
        if (b == 1)
            return 0;
        // Apply extended Euclid Algorithm
        while (a > 1) {
            // q is quotient
            q = a / b;
            t = b;
            // now proceed same as Euclid's algorithm
            b = a % b;
            a = t;
            t = x;
            x = y - q * x;
            y = t;
        }
        // Make x1 positive
        if (y < 0)
            y += m;
        return y;
    }

    static final class Schedule {
        final int departTime;
        final List<ScheduleItem> routes;

        Schedule(List<String> lines) {
            departTime = Integer.parseInt(lines.get(0));
            List<ScheduleItem> items = new ArrayList<>();
            String[] routeCandidates = lines.get(1).split(",");

            int afterTimestamp = 0;
            for (int i = 0; i < routeCandidates.length; i++) {
                String routeCandidate = routeCandidates[i];
                if (routeCandidate.matches("[0-9]+")) {
                    int route = Integer.parseInt(routeCandidate);
                    items.add(new ScheduleItem(route, ((route * 100) - afterTimestamp) % route));
                }
                afterTimestamp++;
            }
            this.routes = items;
        }
    }

    static final class ScheduleItem {
        final int route;
        final int remainder;

        private ScheduleItem(int route, int remainder) {
            this.route = route;
            this.remainder = remainder;
        }
    }
}
