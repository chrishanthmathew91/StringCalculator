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
}
