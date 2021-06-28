package main;

public class StringCalculator {

    public int add(String number) {
        if (isEmpty(number))
            return 0;
        return Integer.parseInt(number);
    }

    private boolean isEmpty(String s) {
        return s.isEmpty();
    }
}
