package thisiscodingtest.greedy;

import java.util.Scanner;

public class ReversingString {

    char[] nums;

    public static void main(String[] args) {
        ReversingString main = new ReversingString();
        main.run();
    }

    private void run() {
        getInput();

        int startNum = nums[0];
        int answer = 0;

        // 첫 숫자와 커서에 숫자가 다르다면 그 숫자가 연속되는 곳 다음까지 커서를 움직인다
        for (int cursor = 1; cursor < nums.length; cursor++) {
            if (startNum != nums[cursor]) {
                while (cursor < nums.length && startNum != nums[cursor]) cursor++;
                answer++;
            }
        }

        System.out.println(answer);
    }

    private void getInput() {
        Scanner sc = new Scanner(System.in);
        nums = sc.nextLine().toCharArray();
    }

}
