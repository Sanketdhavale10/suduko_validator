import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SuducoFileExtractor {

    private static String ROW_REGEX = "([1-9],){8}[1-9]";

    /**
     * Extracts a `int[][]` representing provided sudoku puzzle solution.
     *
     * @param filepath file name of the solution
     * @return `int[][]`
     * @throws SudoException when failed to extract the solution or provided data is invalid
     */

    static public int[][] getSolutionFromFile(String filepath) throws IOException, SudoException {

        Path path = Paths.get(filepath);
        long lineCount;
        Pattern pattern = Pattern.compile(ROW_REGEX);
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            lineCount = stream.count();
            if (lineCount != 9) {
                throw new SudoException("Invalid No of line: No of line should be 9");
            }
        }
        long count = Files.lines(path, StandardCharsets.UTF_8).flatMap(s -> pattern.matcher(s).results()).count();
        if (count != 9) {
            throw new SudoException("Invalid file data. Solution should be number seperated by comma");
        }
            return Files.lines(path)
                    .map((l) -> l.trim().split(","))
                    .map((sa) -> Stream.of(sa).mapToInt(Integer::parseInt).toArray())
                    .toArray(int[][]::new);
        }
    }