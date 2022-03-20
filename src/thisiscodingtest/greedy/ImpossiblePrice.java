package thisiscodingtest.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class ImpossiblePrice {

    private int[] coins;

    public static void main(String[] args) {
        ImpossiblePrice main = new ImpossiblePrice();
        main.getInput();
        main.run();
    }

    private void run() {
        Arrays.sort(coins);

        int target = 1;
        for (int coin : coins) {
            // 다음 동전이 목표 금액 보다 크다면, 목표 금액은 만들 수 없다
            if (target < coin) break;
            // 다음 동전이 목표 금액 보다 작다면, 목표 금액 + 다음 동전까지 만들 수 있다
            target += coin;
        }

        System.out.println(target);
    }

    private void getInput() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }
    }

}
