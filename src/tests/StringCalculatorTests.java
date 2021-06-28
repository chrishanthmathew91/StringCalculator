package tests;

import main.StringCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTests {

    private StringCalculator sc;

    @Before
    public void setUp() {
        sc = new StringCalculator();
    }

    @Test
    public void add_EmptyString_ReturnsZero() {

        int result = sc.add("");

        assertEquals(0, result);
    }

    @Test
    public void add_SingleIntegerString_ReturnsSameInteger() {

        int result = sc.add("1");
        int result2 = sc.add("2");
        int result3 = sc.add("3");

        assertEquals(1, result);
        assertEquals(2, result2);
        assertEquals(3, result3);
    }

    @Test
    public void add_TwoIntegerString_ReturnsAddedResult() {

        int result = sc.add("1,2");

        assertEquals(3, result);
    }

    @Test
    public void add_MoreThanTwoIntegerString_ReturnsAddedResult() {

        int result = sc.add("1,2,3");
        int result2 = sc.add("1,2,3,4");

        assertEquals(6, result);
        assertEquals(10, result2);
    }
}
