package thisiscodingtest.implementation;

import java.time.LocalTime;
import java.util.Scanner;

public class Time {

    private int N;

    public static void main(String[] args) {
        var main = new Time();
        main.getInput();
        main.run();
    }

    private void getInput() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
    }

    private void run() {
        int answer = 0;

        for (int hour = 0; hour <= N; hour++) {
            for (int min = 0; min < 60; min++) {
                for (int sec = 0; sec < 60; sec++) {
                    if (hasThreeInTime(hour, min, sec)) {
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private boolean hasThreeInTime(int hour, int min, int sec) {
        String three = "3";
        return String.valueOf(hour).contains(three)
            || String.valueOf(min).contains(three)
            || String.valueOf(sec).contains(three);
    }

}
