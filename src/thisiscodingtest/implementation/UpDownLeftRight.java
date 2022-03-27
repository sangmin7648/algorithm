package thisiscodingtest.implementation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UpDownLeftRight {

    private int N;
    private List<String> moves;

    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        var main = new UpDownLeftRight();
        main.getInput();
        main.run();
    }

    private void getInput() {
        Scanner scanner = new Scanner(System.in);
        N = Integer.parseInt(scanner.nextLine());
        moves = Arrays.stream(scanner.nextLine().split(" ")).toList();
    }

    private void run() {
        int curX = 1;
        int curY = 1;
        for (String move : moves) {
            int dirNum = getDirNum(move);
            if (isValidMovement(curX, curY, dirNum)) {
                curX += dx[dirNum];
                curY += dy[dirNum];
            }
        }

        System.out.println(curX + " " + curY);
    }

    private int getDirNum(String move) {
        switch (move.toUpperCase()) {
            case "U": return 0;
            case "D": return 1;
            case "L": return 2;
            case "R": return 3;
        }

        throw new IllegalArgumentException("invalid move command");
    }

    private boolean isValidMovement(int curX, int curY, int dirNum) {
        int nextX = curX + dx[dirNum];
        int nextY = curY + dy[dirNum];

        return (nextX >= 1 && nextX <= N) && (nextY >= 1 && nextY <= N);
    }

}
