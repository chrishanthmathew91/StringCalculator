package tests;

import main.StringCalculator;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTests {

    @Test
    public void add_EmptyString_ReturnsZero() {
        StringCalculator sc = new StringCalculator();

        int result = sc.add("");

        assertEquals(0, result);
    }

    @Test
    public void add_SingleIntegerString_ReturnsSameInteger() {
        StringCalculator sc = new StringCalculator();

        int result = sc.add("1");
        int result2 = sc.add("2");

        assertEquals(1, result);
        assertEquals(2, result2);
    }
}
