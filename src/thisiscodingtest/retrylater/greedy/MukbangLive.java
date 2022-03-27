package thisiscodingtest.retrylater.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42891
 * 효율성 테스트 모두 실패 (답이 틀림)
 */

public class MukbangLive {

    public static void main(String[] args) {
        var main = new MukbangLive();

        System.out.println(main.run(new int[]{3, 1, 2}, 5));  // 1
        System.out.println(main.run(new int[]{1, 2, 3, 4, 5}, 1));  // 2
        System.out.println(main.run(new int[]{1, 2}, 5));  // -1
    }

    private int run(int[] foodTimes, long k) {
        // 모든 음식을 먹는데 걸리는 시간이 k보다 작거나 같다면 방송 중단 전에 모든 음식을 다 먹는다
        if (totalFoodTime(foodTimes) <= k) return -1;

        // 순서와 남은 음식양을 가진 음식 객체의 큐
        PriorityQueue<Food> foodPq = createFoodPq(foodTimes);

        long total = 0;
        long previous = 0;
        long length = foodTimes.length;

        // 가장 적게 남은 음식을 다 먹을 동안 방송이 멈추지 않으면
        while (timeToEatLeastRemainingFood(foodPq, total, previous, length) <= k) {
            int now = foodPq.poll().quantity;
            total += (now - previous) * length;
            length--;
            previous = now;
        }

        var result = new ArrayList<>(foodPq);
        result.sort(Comparator.comparingInt(o -> o.id));

        return result.get((int) ((k - total) % length)).id;
    }

    private long timeToEatLeastRemainingFood(PriorityQueue<Food> foodPq, long total, long previous, long length) {
        return total + ((foodPq.peek().quantity - previous) * length);
    }

    private PriorityQueue<Food> createFoodPq(int[] foodTimes) {
        var pq = new PriorityQueue<Food>();
        for (int i = 0; i < foodTimes.length; i++) {
            pq.add(new Food(i+1, foodTimes[i]));
        }
        return pq;
    }

    private long totalFoodTime(int[] foodTimes) {
        return Arrays.stream(foodTimes).sum();
    }

    private static class Food implements Comparable<Food> {
        final int id;
        int quantity;

        public Food(int id, int quantity) {
            this.id = id;
            this.quantity = quantity;
        }

        @Override
        public int compareTo(Food o) {
            return Integer.compare(this.quantity, o.quantity);
        }

    }

}
