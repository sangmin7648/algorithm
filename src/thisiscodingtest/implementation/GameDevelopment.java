package thisiscodingtest.implementation;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class GameDevelopment {

    int mapRow;
    int mapCol;
    int[][] map;
    Character character;

    public static void main(String[] args) {
        var main = new GameDevelopment();
        main.getInput();
        main.run();
    }

    private void getInput() {
        var sc = new Scanner(System.in);

        mapRow = sc.nextInt();
        mapCol = sc.nextInt();
        map = new int[mapRow][mapCol];

        Coordinate position = new Coordinate(sc.nextInt(), sc.nextInt());
        boolean[][] visited = new boolean[mapRow][mapCol];
        visited[position.x][position.y] = true;
        character = new Character(
                position,
                Direction.of(sc.nextInt()),
                visited
        );

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = sc.nextInt();
            }
        }
    }

    private void run() {
        Coordinate startingPosition = null;
        // 더이상 움직이지 않을 때까지 반복
        while(!character.position.equals(startingPosition)) {
            startingPosition = Coordinate.copy(character.position);
            // 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 갈 곳을 정한다
            for (int tries = 1; tries <= 4; tries++) {
                character.turnLeft();
                // 앞이 아직 가보지 않았고 갈 수 있다면 앞으로 간다
                if (character.canMoveFront(map)) {
                    character.moveFront();
                    break;
                }

                // 네 방향 모두 갈 수 없다면 뒤로 움직인다
                if (tries == 4 && character.canMoveBack()) {
                    character.moveBack();
                }
            }
        }

        System.out.println(character.calcMoveCount());
    }

    enum Direction {

        NORTH(0, new Coordinate(-1, 0)) {
            @Override
            Direction turnLeft() { return WEST; }
        },
        EAST(1, new Coordinate(0, 1)) {
            @Override
            Direction turnLeft() { return NORTH; }
        },
        SOUTH(2, new Coordinate(1, 0)) {
            @Override
            Direction turnLeft() { return EAST; }
        },
        WEST(3, new Coordinate(0, -1)) {
            @Override
            Direction turnLeft() { return SOUTH; }
        };

        final int directionNum;
        final Coordinate frontDelta;

        Direction(int directionNum, Coordinate frontPositionDelta) {
            this.directionNum = directionNum;
            this.frontDelta = frontPositionDelta;
        }

        abstract Direction turnLeft();

        static Direction of(int directionNum) {
            return Arrays.stream(Direction.values())
                    .filter(d -> d.directionNum == directionNum)
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("invalid direction"));
        }

    }

    class Character {

        Coordinate position;
        Direction direction;
        boolean[][] visited;

        Character(Coordinate position, Direction direction, boolean[][] visited) {
            this.position = position;
            this.direction = direction;
            this.visited = visited;
        }

        void turnLeft() {
            this.direction = this.direction.turnLeft();
        }

        public boolean canMoveFront(int[][] map) {
            int nextRow = position.x + direction.frontDelta.x;
            int nextCol = position.y + direction.frontDelta.y;

            // 존재하는 위치인지 확인
            if (nextRow < 0 || nextRow >= mapRow || nextCol < 0 || nextCol >= mapCol) {
                return false;
            }

            return !visited[nextRow][nextCol] && map[nextRow][nextCol] == 0;
        }

        public void moveFront() {
            this.position = new Coordinate(
                    position.x + direction.frontDelta.x,
                    position.y + direction.frontDelta.y
            );

            this.visited[position.x][position.y] = true;
        }

        public boolean canMoveBack() {
            int nextRow = position.x - direction.frontDelta.x;
            int nextCol = position.y - direction.frontDelta.y;

            // 존재하는 위치인지 확인
            if (nextRow < 0 || nextRow >= mapRow || nextCol < 0 || nextCol >= mapCol) {
                return false;
            }

            return map[nextRow][nextCol] == 0;
        }

        public void moveBack() {
            this.position = new Coordinate(
                    position.x - direction.frontDelta.x,
                    position.y - direction.frontDelta.y
            );

            this.visited[position.x][position.y] = true;
        }

        public int calcMoveCount() {
            int count = 0;

            for (boolean[] visitedArr : this.visited) {
                for (boolean visited : visitedArr) {
                    if (visited) count++;
                }
            }

            return count;
        }
    }

    static class Coordinate {

        final int x;
        final int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Coordinate copy(Coordinate position) {
            return new Coordinate(position.x, position.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

    }

}
