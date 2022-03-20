package thisiscodingtest.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class AdventurerGuild {

    private int[] scareScores;

    public static void main(String[] args) {
        AdventurerGuild main = new AdventurerGuild();
        main.getInput();
        main.run();
    }

    private void run() {

        Arrays.sort(scareScores);

        int answer = 0;
        int groupSize = 0;
        for (int scareScore : scareScores) {
            // 현재 모험가를 그룹에 추가
            groupSize++;
            // 그룹 크기가 공포점수가 가장 큰 멤버의 공포점수와 같거나 크다면 그룹 결성
            if (groupSize >= scareScore) {
                answer++;
                groupSize = 0;
            }
        }

        System.out.println(answer);
    }

    private void getInput() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        scareScores = new int[n];
        for (int i = 0; i < n; i++) {
            scareScores[i] = sc.nextInt();
        }
    }

}
