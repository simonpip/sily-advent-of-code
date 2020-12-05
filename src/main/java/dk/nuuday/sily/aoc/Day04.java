package dk.nuuday.sily.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Day04 {
    static long countValidPassports(List<Passport> passports) {
        return passports.stream().filter(Passport::isValid).count();
    }

    static long countSuperValidPassports(List<Passport> passports) {
        return passports.stream().filter(Passport::isSuperValid).count();
    }

    static List<Passport> convertToPassports(List<String> strings) {
        List<String> combinedStrings = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            if (string.isEmpty()) {
                combinedStrings.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(string);
        }
        combinedStrings.add(sb.toString());

        return combinedStrings.stream().map(Passport::new).collect(Collectors.toList());
    }

    static final class Passport {
        private String birthYear;
        private String issueYear;
        private String expirationYear;
        private String height;
        private String hairColor;
        private String eyeColor;
        private String passportId;
        private String countryId;

        Passport(String passport) {
            String[] values = passport.split(" ");
            for (String value : values) {
                String[] keyValue = value.split(":");
                switch (keyValue[0]) {
                    case "byr":
                        birthYear = keyValue[1];
                        break;
                    case "iyr":
                        issueYear = keyValue[1];
                        break;
                    case "eyr":
                        expirationYear = keyValue[1];
                        break;
                    case "hgt":
                        height = keyValue[1];
                        break;
                    case "hcl":
                        hairColor = keyValue[1];
                        break;
                    case "ecl":
                        eyeColor = keyValue[1];
                        break;
                    case "pid":
                        passportId = keyValue[1];
                        break;
                    case "cid":
                        countryId = keyValue[1];
                        break;
                }
            }
        }

        boolean isValid() {
            String[] strings = new String[]{
                    birthYear
                    , issueYear
                    , expirationYear
                    , height
                    , hairColor
                    , eyeColor
                    , passportId
                    //, countryId
            };

            return Arrays.stream(strings).noneMatch(Objects::isNull);
        }

        boolean isSuperValid() {
            List<Supplier<Boolean>> validSuppliers = List.of(
                    this::validateBirthYear
                    , this::validateIssueYear
                    , this::validateExpirationYear
                    , this::validateHeight
                    , this::validateHairColor
                    , this::validateEyeColor
                    , this::validatePassportId
                    //, countryId
            );

            return isValid() && validSuppliers.stream().allMatch(Supplier::get);
        }

        private boolean validateBirthYear() {
            return validateNumber(birthYear, 1920, 2002);
        }

        private boolean validateIssueYear() {
            return validateNumber(issueYear, 2010, 2020);
        }

        private boolean validateExpirationYear() {
            return validateNumber(expirationYear, 2020, 2030);
        }

        private boolean validateHeight() {
            String heightNumber = height.substring(0, height.length() - 2);

            return (height.contains("in") && validateNumber(heightNumber, 59, 76))
                    || (height.contains("cm") && validateNumber(heightNumber, 150, 193));
        }

        private boolean validateHairColor() {
            return hairColor.matches("^#{1}[0-9a-f]{6}$");
        }

        private boolean validateEyeColor() {
            return eyeColor.matches("^(amb|blu|brn|gry|grn|hzl|oth){1}$");
        }

        private boolean validatePassportId() {
            return passportId.matches("^[0-9]{9}$");
        }

        private static boolean validateNumber(String number, int min, int max) {
            if (!number.matches("^[0-9]+$")) {
                return false;
            }
            int parsed = Integer.valueOf(number);

            return parsed >= min && parsed <= max;
        }
    }
}
