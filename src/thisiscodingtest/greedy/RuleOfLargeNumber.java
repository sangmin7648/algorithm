package thisiscodingtest.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class RuleOfLargeNumber {

    private int N;

    private int M;

    private int K;

    private int[] nums;

    public static void main(String[] args) {
        RuleOfLargeNumber main = new RuleOfLargeNumber();
        main.run();
    }

    private void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
    }

    private void run() {
        getInput();

        // 숫자 정렬
        Arrays.sort(nums);

        // 가장 큰 수와 두번째로 큰 수 구하기
        int maxNum = nums[N-1];
        int secondMaxNum = nums[N-2];

        // 가장 큰 수의 합 구하기
        int answer = 0;
        int maxNumUseCount = K;
        for (int i = 0; i < M; i++) {
            if (maxNumUseCount > 0) {
                // 제한 횟수에 걸리지 않았을 경우
                answer += maxNum;
                maxNumUseCount--;
            } else {
                // 제한 횟수에 걸렸을 경우
                answer += secondMaxNum;
                maxNumUseCount = K;
            }
        }

        System.out.println(answer);
    }

}
