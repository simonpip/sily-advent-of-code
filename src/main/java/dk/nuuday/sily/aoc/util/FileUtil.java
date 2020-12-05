package dk.nuuday.sily.aoc.util;

import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileUtil {
    public static <T> List<T> readLines(String resourceName, Function<String, T> convert) throws IOException {
        String text = readFile(resourceName);

        return Arrays.stream(text.split("\n"))
                .map(String::trim)
                .map(convert)
                .collect(Collectors.toList());
    }

    public static <T> T readFile(String resourceName, Function<List<String>, T> convert) throws IOException {
        String text = readFile(resourceName);

        List<String> strings = Arrays.stream(text.split("\n"))
                .map(String::trim)
                .collect(Collectors.toList());
        return convert.apply(strings);
    }

    private static String readFile(String resourceName) throws IOException {
        URL url = Resources.getResource(resourceName);
        return Resources.toString(url, StandardCharsets.UTF_8);
    }
}
