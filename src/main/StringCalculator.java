package main;

import org.junit.function.ThrowingRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.*;

public class StringCalculator {

    private final List<Integer> integerList = new ArrayList<Integer>();
    private int calledCount = 0;

    public int add(String input) {
        calledCount++;
        if (isEmpty(input))
            return 0;

        if (containsLinebreakOrComma(input))
            return splitAndAddNumbers(input);

        parseIntAndAddToIntegerList(input);
        return checkForNegativeNumbersOrAdd();
    }

    public int getCalledCount() {
        return calledCount;
    }

    private boolean containsLinebreakOrComma(String input) {
        return input.contains("\n") || input.contains(",");
    }

    private int checkValidityAndParseInt(String input) {
        int result = parseInt(input);
        if (result > 1000)
            return 0;
        return result;
    }

    private void parseIntAndAddToIntegerList(String number) {
        integerList.add(checkValidityAndParseInt(number));
    }

    private int checkForNegativeNumbersOrAdd() {
        List<Integer> negativeList = integerList.stream().filter(i -> i < 0).toList();
        if (negativeList.size() > 0)
            throwNegativeNumberException(negativeList);
        return addIntegers();
    }

    private int addIntegers() {
        int result = 0;
        for (int i : integerList)
            result += i;
        integerList.clear();
        return result;
    }

    private void throwNegativeNumberException(List<Integer> negativeList) {
        StringBuilder message = new StringBuilder("Negatives not allowed ");
        for (int i : negativeList) {
            message.append(i);
            message.append(" ");
        }
        throw new NumberFormatException(message.toString());
    }

    private int splitAndAddNumbers(String input) {
        List<String> delimiters = new ArrayList<String>();
        delimiters.add("[\n,]");
        if (input.contains("\n")) {
            String[] parts = input.split("\n", 2);
            String first = parts[0];
            if (!isDelimiter(first)) {
                delimiters.clear();
                delimiters.add("[\n,]");
            } else {
                delimiters = checkAndRemoveBraces(first);
                input = parts[1];
            }
        }
        return splitByDelimiterAndAdd(input, delimiters);
    }

    private List<String> checkAndRemoveBraces(String input) {
        List<String> delimiters = new ArrayList<String>();
        if (input.contains("[")) {
            String[] parts = input.split("]\\[");
            for (String d : parts) {
                String result = d.replace("[", "")
                        .replace("]", "")
                        .replace("", "\\");
                delimiters.add(result.substring(0, result.length() - 1));
            }
            return delimiters;
        }
        delimiters.add("\\" + input);
        return delimiters;
    }

    private boolean isDelimiter(String string) {
        Pattern p = Pattern.compile("[[0-9],\n]");
        Matcher m = p.matcher(string);
        return !m.find();
    }

    private int splitByDelimiterAndAdd(String string, List<String> delimiters) {
        List<String> numbers;
        String cleanString = string;
        if (delimiters.size() == 1) {
            numbers = splitByDelimiter(string, delimiters.get(0));
        } else {
            for (String d : delimiters) {
                String delim = d.replace("\\", "");
                cleanString = cleanString.replace(delim, ",");
            }
            numbers = splitByDelimiter(cleanString, ",");
        }
        for (String number : numbers) {
            parseIntAndAddToIntegerList(number);
        }
        return checkForNegativeNumbersOrAdd();
    }

    private List<String> splitByDelimiter(String string, String delimiter) {
        return Arrays.stream(string.split(String.format("%s", delimiter))).toList();
    }

    private boolean isEmpty(String s) {
        return s.isEmpty();
    }
}
