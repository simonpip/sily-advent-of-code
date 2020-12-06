package dk.nuuday.sily.aoc;

import java.util.List;

class Day02 {
    private Day02() {
    }

    static int countValidPasswords(List<Password> passwords) {
        return (int) passwords.stream().filter(Day02::isValidPassword).count();
    }

    private static boolean isValidPassword(Password password) {
        int matches = (int) password.password.chars().filter(ch -> ch == password.letter).count();

        return password.min <= matches && password.max >= matches;
    }

    static int countValidPasswordsPart2(List<Password> passwords) {
        return (int) passwords.stream().filter(Day02::isValidPasswordPart2).count();
    }

    private static boolean isValidPasswordPart2(Password password) {
        char firstPosition = password.password.charAt(password.min - 1);
        char secondPosition = password.password.charAt(password.max - 1);

        return firstPosition == password.letter ^ secondPosition == password.letter;
    }

    static final class Password {
        private final int min;
        private final int max;
        private final char letter;
        private final String password;

        Password(String string) {
            int hyphenIndex = string.indexOf("-");
            int spaceIndex = string.indexOf(" ");
            this.min = Integer.parseInt(string.substring(0, hyphenIndex));
            this.max = Integer.parseInt(string.substring(hyphenIndex + 1, spaceIndex));
            this.letter = string.charAt(spaceIndex + 1);
            this.password = string.substring(spaceIndex + 4);
        }
    }
}
