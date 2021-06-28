package main;

public class StringCalculator {

    public int add(String number) {
        if (isEmpty(number))
            return 0;
        if (number.contains(",")) {
            String[] numbers = number.split(",");
            return Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
        }
        return Integer.parseInt(number);
    }

    private boolean isEmpty(String s) {
        return s.isEmpty();
    }
}
