package dk.simonpip.aoc.util;

import java.util.List;
import java.util.function.Function;

public class Grid<T> {
    private final int height;
    private final int width;
    private final Object[][] grid;

    public Grid(List<String> strings, Function<Character, T> convert) {
        height = strings.size();
        width = strings.get(0).length();
        grid = new Object[height][width];

        for (int x = 0; x < strings.size(); x++) {
            String string = strings.get(x);

            for (int y = 0; y < string.length(); y++) {
                char letter = string.charAt(y);

                set(x, y, convert.apply(letter));
            }
        }
    }

    public Grid(Grid<T> original) {
        this.height = original.height;
        this.width = original.width;
        this.grid = new Object[height][width];

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                set(x, y, original.get(x, y));
            }
        }
    }

    public Grid(int height, int width, T defaultValue) {
        this.height = height;
        this.width = width;
        this.grid = new Object[height][width];

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                set(x, y, defaultValue);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public T get(int x, int y) {
        return (T) grid[x][y];
    }

    public void set(int x, int y, T value) {
        grid[x][y] = value;
    }

    protected String stringValue(Function<T, String> stringFunction) {
        StringBuilder stringBuilder = new StringBuilder(width * height);
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                stringBuilder.append(stringFunction.apply(get(x, y)));
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
