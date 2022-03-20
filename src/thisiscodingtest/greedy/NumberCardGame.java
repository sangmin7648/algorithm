package thisiscodingtest.greedy;

import java.util.Scanner;

public class NumberCardGame {

    private int N;

    private int M;

    private int[][] cards;

    public static void main(String[] args) {
        NumberCardGame main = new NumberCardGame();
        main.run();
    }

    private void run() {
        getInput();

        int answer = Integer.MIN_VALUE;
        for (int[] cardRow : cards) {
            // 행의 최소 숫자 카드 탐색
            int rowMin = Integer.MAX_VALUE;
            for (int card : cardRow) {
                rowMin = Math.min(rowMin, card);
            }

            answer = Math.max(answer, rowMin);
        }

        System.out.println(answer);
    }

    private void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        cards = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cards[i][j] = sc.nextInt();
            }
        }
    }

}
