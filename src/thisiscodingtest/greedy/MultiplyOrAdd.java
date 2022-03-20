package thisiscodingtest.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class MultiplyOrAdd {

    private int[] nums;

    public static void main(String[] args) {
        MultiplyOrAdd main = new MultiplyOrAdd();
        main.run();
    }

    private void run() {
        getInput();

        int answer = 0;
        for (int num : nums) {
            if (answer * num <= answer) {
                answer += num;
            } else {
                answer *= num;
            }
        }

        System.out.println(answer);
    }

    private void getInput() {
        Scanner sc = new Scanner(System.in);
        String numsString = sc.nextLine();

        nums = Arrays.stream(numsString.split(""))
            .mapToInt(Integer::parseInt)
            .toArray();
    }

}
