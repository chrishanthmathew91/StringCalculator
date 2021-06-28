package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String delimiter = "[\n,]";
        if (input.contains("\n")) {
            String[] parts = input.split("\n", 2);
            String firstPart = parts[0];
            if (isDelimiter(firstPart)) {
                delimiter = firstPart;
                input = parts[1];
            }
        }
        return splitByDelimiterAndAdd(input, delimiter);
    }

    private boolean isDelimiter(String string) {
        Pattern p = Pattern.compile("[[0-9],\n]");
        Matcher m = p.matcher(string);
        return !m.find();
    }

    private int splitByDelimiterAndAdd(String string, String delimiter) {
        int result = 0;
        String[] numbers = string.split(String.format("%s", delimiter));
        for (String number : numbers)
            result += parseInt(number);
        return result;
    }

    private boolean isEmpty(String s) {
        return s.isEmpty();
    }

}
