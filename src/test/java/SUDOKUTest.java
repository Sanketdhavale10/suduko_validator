import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class SUDOKUTest {


@Test
void testFileInput() {
    String resourceName = "valid.txt";

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(resourceName).getFile());
    String absolutePath = file.getAbsolutePath();
    Exception ex = null;
    try {
        SuducoFileExtractor.getSolutionFromFile(file.getAbsolutePath());
    } catch (Exception | SudoException e) {
        ex = (Exception) e;
    }
    assertEquals(null,ex);
}

    @Test
    void whenExceptionThrown_thenAssertionSucceeds() {

        java.nio.file.NoSuchFileException thrown = Assertions.assertThrows(java.nio.file.NoSuchFileException.class, () -> {
            SuducoFileExtractor.getSolutionFromFile("file.txt");
        }, "FileNotFoundException was expected");

        Assertions.assertEquals("file.txt", thrown.getMessage());


    }
    @Test
    void ExceptionThrownInvalidData_thenAssertionSucceeds() {
        String resourceName = "contains_letter.txt";

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        String absolutePath = file.getAbsolutePath();

        SudoException thrown = Assertions.assertThrows(SudoException.class, () -> {
            SuducoFileExtractor.getSolutionFromFile(absolutePath);
        }, "FileNotFoundException was expected");

        Assertions.assertEquals("Invalid file data. Solution should be number seperated by comma", thrown.getMessage());


    }

    @Test
    @DisplayName("Regular Solution should work")
    void testValidSolution() {
        assertEquals(true, SUDOKUValidator.isSudokuValid(new int [][]{
                {5,	3,	4,	6,	7,	8,	9,	1,	2},
                {6,	7,	2,	1,	9,	5,	3,	4,	8},
                {1,	9,	8,	3,	4,	2,	5,	6,	7},
                {8,	5,	9,	7,	6,	1,	4,	2,	3},
                {4,	2,	6,	8,	5,	3,	7,	9,	1},
                {7,	1,	3,	9,	2,	4,	8,	5,	6},
                {9,	6,	1,	5,	3,	7,	2,	8,	4},
                {2,	8,	7,	4,	1,	9,	6,	3,	5},
                {3,	4,	5,	2,	8,	6,	1,	7,	9}}),
                "Regular Solution should work");
    }

    @Test
    @DisplayName("Invalid Solution should not work")
    void testInValidSolution() {
        assertEquals(false, SUDOKUValidator.isSudokuValid(new int [][]{
                        {5,	3,	1,	1,	7,	8,	9,	1,	2},
                        {6,	7,	2,	1,	9,	5,	3,	4,	8},
                        {1,	9,	8,	3,	4,	2,	5,	6,	7},
                        {8,	5,	9,	7,	6,	1,	4,	2,	3},
                        {4,	2,	6,	8,	5,	3,	7,	9,	1},
                        {7,	1,	3,	9,	2,	4,	8,	5,	6},
                        {9,	6,	1,	5,	3,	7,	2,	8,	4},
                        {2,	8,	7,	4,	1,	9,	6,	3,	5},
                        {3,	4,	5,	2,	8,	6,	1,	7,	9}}),
                "Invalid Solution should not work");
    }
}
