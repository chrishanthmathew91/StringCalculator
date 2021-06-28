package main;

import org.junit.function.ThrowingRunnable;

import java.util.ArrayList;
import java.util.List;
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
        int i = parseInt(input);
        List<Integer> l = new ArrayList<Integer>();
        l.add(i);
        return addIntegers(l);
    }

    private int addIntegers(List<Integer> intList) {
        List<Integer> negativeList = intList.stream().filter(i -> i < 0).toList();
        if (negativeList.size() > 0) {
            String message = "Negatives not allowed ";
            for (int i : negativeList) {
                message += i;
                message += " ";
            }
           throw new NumberFormatException(message);
        } else {
            int result = 0;
            for (int i : intList)
                result += i;
            return result;
        }

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
        List<Integer> intList = new ArrayList<Integer>();
        String[] numbers = string.split(String.format("%s", delimiter));
        for (String number : numbers)
            intList.add(parseInt(number));
        return addIntegers(intList);
    }

    private boolean isEmpty(String s) {
        return s.isEmpty();
    }

}
