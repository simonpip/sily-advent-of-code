package dk.nuuday.sily.aoc.y2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class Day14 {
    private Day14() {
    }

    static long sumMaskedNumbers(Program program) {
        Mask mask = null;
        Map<Long, Long> values = new HashMap<>();

        for (Object operation : program.operations) {
            if (operation instanceof Mask) {
                mask = (Mask) operation;
            } else {
                Modifier modifier = (Modifier) operation;
                values.put(modifier.index, maskNumber(modifier.value, mask));
            }
        }

        return values.values().stream().mapToLong(e -> e).sum();
    }

    private static long maskNumber(long number, Mask mask) {
        long sum = 0;
        long remainingNumber = number;
        for (int i = 0; i < mask.mask.length; i++) {
            MaskElement maskElement = mask.mask[i];
            long value = (long) Math.pow(2, i);
            long remainder = (remainingNumber % (value * 2));

            sum += maskElement.numberFunction.apply(remainder, value);
            remainingNumber -= remainder;
        }
        return sum + remainingNumber;
    }

    static long sumMaskedAddresses(Program program) {
        Mask mask = null;
        Map<Long, Long> values = new HashMap<>();

        for (Object operation : program.operations) {
            if (operation instanceof Mask) {
                mask = (Mask) operation;
            } else {
                Modifier modifier = (Modifier) operation;
                for (Long maskAddress : maskAddress(modifier.index, mask)) {
                    values.put(maskAddress, modifier.value);
                }
            }
        }

        return values.values().stream().mapToLong(e -> e).sum();
    }

    private static List<Long> maskAddress(long address, Mask mask) {
        List<Long> addresses = new ArrayList<>();
        addresses.add(0L);
        long remainingNumber = address;
        for (int i = 0; i < mask.mask.length; i++) {
            MaskElement maskElement = mask.mask[i];
            long value = (long) Math.pow(2, i);
            long remainder = (remainingNumber % (value * 2));

            addresses = maskElement.addressFunction.apply(addresses, remainder, value);
            remainingNumber -= remainder;
        }
        return addresses;
    }

    static final class Program {
        private final List<Object> operations;

        public Program(List<String> lines) {
            this.operations = lines.stream().map(e -> {
                if (e.contains("mask")) {
                    return new Mask(e);
                } else {
                    return new Modifier(e);
                }
            }).collect(Collectors.toList());
        }
    }

    private static final class Modifier {
        private final long index;
        private final long value;

        public Modifier(String string) {
            this.index = Long.parseLong(string.substring(string.indexOf('[') + 1, string.indexOf(']')));
            this.value = Long.parseLong(string.substring(string.indexOf('=') + 2));
        }
    }

    private static final class Mask {
        private final MaskElement[] mask;

        public Mask(String string) {
            String maskString = string.substring(7);
            this.mask = new MaskElement[maskString.length()];
            for (int i = 0; i < mask.length; i++) {
                mask[i] = MaskElement.fromChar(maskString.charAt(maskString.length() - 1 - i));
            }
        }
    }

    private enum MaskElement {
        X((rem, val) -> rem, (list, rem, val) -> {
            List<Long> newList = new ArrayList<>(list);
            for (Long old : list) {
                newList.add(old + val);
            }
            return newList;
        }),
        ZERO((rem, val) -> 0L, (list, rem, val) -> {
            List<Long> newList = new ArrayList<>();
            for (Long old : list) {
                newList.add(old + rem);
            }
            return newList;
        }),
        ONE((rem, val) -> val, (list, rem, val) -> {
            List<Long> newList = new ArrayList<>();
            for (Long old : list) {
                newList.add(old + val);
            }
            return newList;
        });

        private final BiFunction<Long, Long, Long> numberFunction;
        private final AddressFunction addressFunction;

        MaskElement(BiFunction<Long, Long, Long> numberFunction, AddressFunction addressFunction) {
            this.numberFunction = numberFunction;
            this.addressFunction = addressFunction;
        }

        private static MaskElement fromChar(char letter) {
            switch (letter) {
                case 'X':
                    return X;
                case '0':
                    return ZERO;
                case '1':
                    return ONE;
                default:
                    return null;
            }
        }
    }

    @FunctionalInterface
    private interface AddressFunction {
        List<Long> apply(List<Long> addresses, long remainder, long value);
    }
}
