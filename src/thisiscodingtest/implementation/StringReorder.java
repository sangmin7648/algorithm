package thisiscodingtest.implementation;

import java.util.*;
import java.util.stream.Collectors;

public class StringReorder {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        String input = sc.nextLine();

        var main = new StringReorder();
        main.run(input);
    }

    private void run(String input) {
        String alphabets = Arrays.stream(input.split(""))
                .filter(s -> s.matches("[A-Z]"))
                .sorted()
                .collect(Collectors.joining());

        int numSum = Arrays.stream(input.split(""))
                .filter(s -> s.matches("[0-9]"))
                .mapToInt(Integer::parseInt)
                .sum();

        System.out.println(String.join("", alphabets) + numSum);
    }

}
