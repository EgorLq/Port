import static org.junit.jupiter.api.Assertions.*;

import org.example.Port;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PortTest {

    private Port port;

    @BeforeEach
    void setUp() {
        port = new Port(new String[] {"1,3-5", "2", "3-4"});
    }

    @Test
    void testConvertIndexes() {
        int[][] expected = {{1, 2, 3}, {1, 2, 4}, {3, 2, 3}, {3, 2, 4}, {4, 2, 3}, {4, 2, 4}, {5, 2, 3}, {5, 2, 4}};
        int[][] result = port.convertIndexes();
        assertArrayEquals(expected, result);
    }
    @Test
    void testConvertIndexesWithEmptyInput() {
        Port port = new Port(new String[] {});
        int[][] result = port.convertIndexes();
        assertEquals(0, result.length);
    }

    @Test
    void testConvertIndexesWithSingleNumber() {
        Port port = new Port(new String[] {"5"});
        int[][] result = port.convertIndexes();
        assertArrayEquals(new int[][] {{5}}, result);
    }

    @Test
    void testConvertIndexesWithMultipleNumbers() {
        Port port = new Port(new String[] {"1,2", "3-5", "7"});
        int[][] result = port.convertIndexes();
        assertArrayEquals(new int[][] {{1, 3, 7}, {1, 4, 7}, {1, 5, 7}, {2, 3, 7}, {2, 4, 7}, {2, 5, 7}}, result);
    }

    @Test
    void testConvertIndexesWithInvalidInput() {
        Port port = new Port(new String[] {"1-", "2-4-", "7"});
        assertThrows(NumberFormatException.class, port::convertIndexes);
    }

    @Test
    public void testCartesianProduct() {
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4, 5),
                Arrays.asList(6)
        );
        int[][] expectedOutput = {
                {1, 3, 6},
                {1, 4, 6},
                {1, 5, 6},
                {2, 3, 6},
                {2, 4, 6},
                {2, 5, 6}
        };

        Port port = new Port(new String[] {"1-2", "3-5", "6"});
        int[][] output = port.cartesianProduct(input);

        assertArrayEquals(expectedOutput, output);
    }

    @Test
    void testCartesianProductWithEmptyInput() {
        Port port = new Port(new String[] {});
        List<List<Integer>> input = new ArrayList<>();
        int[][] result = port.cartesianProduct(input);
        assertEquals(0, result.length);
    }


    @Test
    void testCartesianProductWithSingleArray() {
        Port port = new Port(new String[] {});
        List<List<Integer>> input = Arrays.asList(Arrays.asList(1, 2, 3));
        int[][] result = port.cartesianProduct(input);
        assertArrayEquals(new int[][] {{1}, {2}, {3}}, result);
    }


    @Test
    void testCartesianProductWithMultipleArrays() {
        Port port = new Port(new String[] {});
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3),
                Arrays.asList(4, 5)
        );
        int[][] expected = {{1, 3, 4}, {1, 3, 5}, {2, 3, 4}, {2, 3, 5}};
        int[][] result = port.cartesianProduct(input);
        assertArrayEquals(expected, result);
    }


    @Test
    void testCartesianProductWithEmptyArray() {
        Port port = new Port(new String[]{});
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(1, 2),
                Collections.emptyList(),
                Arrays.asList(4, 5));
        int[][] result = port.cartesianProduct(input);
        assertEquals(0, result.length);
    }


}
