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
        grid = new Object[width][height];

        for (int i = 0; i < strings.size(); i++) {
            String string = strings.get(i);

            for (int j = 0; j < string.length(); j++) {
                char letter = string.charAt(j);

                grid[j][i] = convert.apply(letter);
            }
        }
    }

    public Grid(Grid<T> original) {
        this.height = original.height;
        this.width = original.width;
        this.grid = new Object[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, original.get(x, y));
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
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                stringBuilder.append(stringFunction.apply(get(x, y)));
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
