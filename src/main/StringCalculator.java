package main;

import static java.lang.Integer.*;

public class StringCalculator {

    public int add(String input) {
        if (isEmpty(input))
            return 0;
        if (input.contains(",")) {
            return splitAndAddNumbers(input);
        }
        return parseInt(input);
    }

    private int splitAndAddNumbers(String input) {
        String[] numbers = input.split(",");
        return parseInt(numbers[0]) + parseInt(numbers[1]);
    }

    private boolean isEmpty(String s) {
        return s.isEmpty();
    }

}
