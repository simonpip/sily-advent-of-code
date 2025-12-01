package dk.simonpip.aoc.y2022;

import dk.simonpip.aoc.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day07Test {
    private static final List<String> EXAMPLE_DATA = List.of(
            "$ cd /",
            "$ ls",
            "dir a",
            "14848514 b.txt",
            "8504156 c.dat",
            "dir d",
            "$ cd a",
            "$ ls",
            "dir e",
            "29116 f",
            "2557 g",
            "62596 h.lst",
            "$ cd e",
            "$ ls",
            "584 i",
            "$ cd ..",
            "$ cd ..",
            "$ cd d",
            "$ ls",
            "4060174 j",
            "8033020 d.log",
            "5626152 d.ext",
            "7214296 k");

    @Test
    public void testFindSizeOfSmallDirectoriesExample() {
        int actual = Day07.findSizeOfSmallDirectories(EXAMPLE_DATA);

        assertThat(actual, equalTo(95437));
    }

    @Test
    public void testFindSizeOfSmallDirectoriesData() throws IOException {
        List<String> data = FileUtil.readLines("y2022/day07.txt");

        int actual = Day07.findSizeOfSmallDirectories(data);

        assertThat(actual, equalTo(1611443));
    }

    @Test
    public void testFindSmallestValidDirectoryExample() {
        int actual = Day07.findSmallestValidDirectory(EXAMPLE_DATA);

        assertThat(actual, equalTo(24933642));
    }

    @Test
    public void testFindSmallestValidDirectoryData() throws IOException {
        List<String> data = FileUtil.readLines("y2022/day07.txt");

        int actual = Day07.findSmallestValidDirectory(data);

        assertThat(actual, equalTo(2086088));
    }
}
