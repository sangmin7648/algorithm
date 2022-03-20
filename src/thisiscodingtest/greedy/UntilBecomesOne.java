package thisiscodingtest.greedy;

import java.util.Scanner;

public class UntilBecomesOne {

    private int N;

    private int K;

    public static void main(String[] args) {
        UntilBecomesOne main = new UntilBecomesOne();
        main.run();
    }

    private void run() {
        getInput();

        // N이 K로 나누어 떨어질때까지 1씩 빼기
        int answer = 0;
        while (N % K != 0) {
            N--;
            answer++;
        }

        // N이 K로 나누어 떨어진다면 N이 1이 될때까지 나눈 횟수 가감
        while (N > 1) {
            N /= K;
            answer++;
        }

        System.out.println(answer);
    }

    private void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
    }

}
