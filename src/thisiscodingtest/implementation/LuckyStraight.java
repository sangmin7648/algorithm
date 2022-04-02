package thisiscodingtest.implementation;

import java.util.Arrays;
import java.util.Scanner;

public class LuckyStraight {

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        String score = sc.nextLine();

        var main = new LuckyStraight();
        main.run(score);
    }

    private void run(String score) {
        int middleIdx = score.length() / 2;
        int leftSum = calcSum(score.substring(0, middleIdx));
        int rightSum = calcSum(score.substring(middleIdx, score.length()));

        if (leftSum == rightSum) {
            System.out.println("LUCKY");
        } else {
            System.out.println("READY");
        }
    }

    private int calcSum(String substring) {
        return Arrays.stream(substring.split(""))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
