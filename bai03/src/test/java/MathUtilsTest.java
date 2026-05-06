import org.example.MathUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(MathUtils.class);
    private static MathUtils mathUtils;

    @BeforeAll
    public static void steup() {
        mathUtils = new MathUtils();
        logger.info("=== Bắt đầu chạy MathUtilsTest ===");
    }

    @Test
    public void testMax_EP() {
        assertEquals(19, mathUtils.max(19, 4));
        assertEquals(10, mathUtils.max(10, 10));
        assertEquals(19, mathUtils.max(4, 19));
    }

    @Test
    public void testBoundaryMax() {
        assertEquals(Integer.MAX_VALUE, mathUtils.max(Integer.MAX_VALUE, 100));
        assertEquals(100, mathUtils.max(100, Integer.MIN_VALUE));
        assertEquals(Integer.MAX_VALUE, mathUtils.max(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(Integer.MIN_VALUE, mathUtils.max(Integer.MIN_VALUE, Integer.MIN_VALUE));

    }

    @Test
    public void testDivide() {
        assertThrows(IllegalArgumentException.class, () -> mathUtils.divide(10, 0));
        assertEquals(3, mathUtils.divide(6, 2));
        assertEquals(-4, mathUtils.divide(8, -2));
    }

    @AfterAll
    public static void finish() {
        logger.info("=== Kết thúc ===");
    }
}