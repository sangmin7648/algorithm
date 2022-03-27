package thisiscodingtest.implementation;

import java.util.Arrays;
import java.util.Scanner;

public class GameDevelopment {

    // 북 동 남 서
    final static int[] dx = {-1, 0, 1, 0};
    final static int[] dy = {0, 1, 0, -1};

    final static int LAND = 0;
    final static int OCEAN = 1;

    int N;
    int M;
    GameCharacter gameCharacter;
    int[][] map;

    public static void main(String[] args) {
        var main = new GameDevelopment();
        main.getInput();
        main.run();
    }

    private void getInput() {
        var sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        gameCharacter = new GameCharacter(sc.nextInt(), sc.nextInt(), sc.nextInt());

        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                map[row][col] = sc.nextInt();
            }
        }
    }

    private void run() {
        int[][] visited = new int[N][M];
        visited[gameCharacter.x][gameCharacter.y] = 1;

        while (gameCharacter.move(visited, map));

        System.out.println(Arrays.stream(visited)
            .flatMapToInt(Arrays::stream)
            .sum());
    }

    class GameCharacter {
        int x;
        int y;
        int dir;

        public GameCharacter(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public boolean move(int[][] visited, int[][] map) {
            // 시계 반대 방향부터 아직 방문하지 않은 위치라면 이동
            for (int tries = 0; tries < 4; tries++) {
                this.dir = getNextDir();
                if (canMoveBy(map, dx[this.dir], dy[this.dir]) && isNotVisited(visited, this.dir)) {
                    this.x = dx[this.dir];
                    this.y = dy[this.dir];
                    return true;
                }
            }

            // 4방향 모두 불가 시 바다가 아니라면 뒤로 한칸 이동
            if (canMoveBy(map, -dx[this.dir], -dy[this.dir])) {
                this.x = x - dx[this.dir];
                this.y = y - dy[this.dir];
                return true;
            }

            return false;
        }

        private boolean isNotVisited(int[][] visited, int dir) {
            int nextX = this.x + dx[dir];
            int nextY = this.y + dy[dir];
            return visited[nextX][nextY] == 0;
        }

        private boolean canMoveBy(int[][] map, int deltaX, int deltaY) {
            int nextX = x + deltaX;
            int nextY = y + deltaY;

            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                return false;
            }

            if (map[nextX][nextY] == OCEAN) {
                return false;
            }

            return true;
        }

        private int getNextDir() {
            return (this.dir + 1) % 4;
        }
    }

}
