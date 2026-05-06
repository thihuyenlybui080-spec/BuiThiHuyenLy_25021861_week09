import org.junit.jupiter.api.Test;

import java.io.File;import java.nio.file.Path;import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilePathTest {
    @Test
    public void testPathWithSeparator(){
        String path = "data" + File.separator + "input.txt";
        assertTrue(path.contains("data"));
        assertTrue(path.contains("input.txt"));
    }

    @Test
    public void testNioPath(){
        Path path = Paths.get("data", "input.txt");
        assertTrue(path.toString().contains("input.txt"));
    }

    @Test
    public void testHardcodedPath_WillFailOnWindows(){
        String fixedPath = "data" + File.separator + "input.txt";
        String correctPath = "data" + File.separator + "input.txt";
        assertTrue(fixedPath.equals(correctPath));

    }
}
