package main;

import static java.lang.Integer.*;

public class StringCalculator {

    public int add(String input) {
        if (isEmpty(input))
            return 0;
        if (containsLinebreakOrComma(input)) {
            return splitAndAddNumbers(input);
        }
        return parseInt(input);
    }

    private boolean containsLinebreakOrComma(String input) {
        return input.contains("\n") || input.contains(",");
    }

    private int splitAndAddNumbers(String input) {
        int result = 0;
        String[] numbers = input.split("[\n,]");
        for (String number : numbers) {
            result += parseInt(number);
        }
        return result;
    }

    private boolean isEmpty(String s) {
        return s.isEmpty();
    }

}
