package thisiscodingtest.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class ChoosingBowlingBall {

    int[] ballWeights;

    public static void main(String[] args) {
        var main = new ChoosingBowlingBall();
        main.getInput();
        main.run();
    }

    private void getInput() {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ballWeights = new int[n];
        for (int i = 0; i < n; i++) {
            ballWeights[i] = scanner.nextInt();
        }
    }

    private void run() {

        Arrays.sort(ballWeights);

        int answer = 0;

        for (int cur = 0; cur < ballWeights.length; cur++) {
            // 현재 무게보다 큰 무게가 나올때까지 커서 이동
            int next = cur;
            while(isSameWeight(cur, next)) {
                next++;
            }

            // 현재 무게와 다른 무게의 공 개수
            int diffWeightCount = ballWeights.length - next;
            answer += diffWeightCount;
        }

        System.out.println(answer);
    }

    private boolean isSameWeight(int cursor, int nextWeightCursor) {
        return nextWeightCursor < ballWeights.length && ballWeights[cursor] == ballWeights[nextWeightCursor];
    }

}
