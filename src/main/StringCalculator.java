package main;

public class StringCalculator {

    public int add(String s) {
        if (s.isEmpty())
            return 0;
        return Integer.parseInt(s);
    }
}
