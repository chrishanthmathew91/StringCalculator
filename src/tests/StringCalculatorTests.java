package tests;

import main.StringCalculator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

    @Test
    public void add_InputWithLineBreakAndComma_ReturnsAddedResult() {

        int result = sc.add("1\n2,3");
        int result2 = sc.add("1,2\n3,4\n5");

        assertEquals(6, result);
        assertEquals(15, result2);
    }

    @Test
    public void add_InputWithCustomDelimiter_ReturnsAddedResult() {

        int result = sc.add(";\n1;2");
        int result2 = sc.add("-\n1-2-3-4-5");
        int result3 = sc.add("*\n1*2*3*4*5");

        assertEquals(3, result);
        assertEquals(15, result2);
        assertEquals(15, result3);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void add_InputWithSingleNegativeNumber_ThrowsException() {

        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("Negatives not allowed -1");
        sc.add("-1");
    }

    @Test
    public void add_InputWithMultipleNegativeNumbers_ThrowsNumberFormatExceptionWithMessage() {

        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("Negatives not allowed -1 -2 -3");
        sc.add("-1\n-2,-3");
    }

    @Test
    public void getCalledCount_ReturnsNumberOfTimesAddIsCalled() {

        sc.add("1");
        sc.add("-\n1-2-3-4-5");
        sc.add("1,2\n3,4\n5");

        int callCount = sc.getCalledCount();

        assertEquals(3, callCount);
    }

    @Test
    public void add_InputWithIntegerLargerThan1000_ShouldReturnAddedResultWithoutThatInteger() {

        int result = sc.add("1001,1");
        int result2 = sc.add("%\n1001%1002%1");

        assertEquals(1, result);
        assertEquals(1, result2);

    }

    @Test
    public void add_InputWithMultiLengthDelimiter_ShouldReturnAddedResult() {

        int result = sc.add("[**]\n1**2**3");
        int result2 = sc.add("[&&&]\n1&&&2&&&3");

        assertEquals(6, result);
        assertEquals(6, result2);
    }

    @Test
    public void add_InputWithMultipleDelimiters_ShouldReturnAddedResult() {

        int result = sc.add("[*][%]\n1*2%3");

        assertEquals(6, result);
    }


    @Test
    public void add_InputWithMultipleDelimitersWithMultipleChars_ShouldReturnAddedResult() {

        int result = sc.add("[***][%%%]\n1***2%%%3");

        assertEquals(6, result);
    }
}
