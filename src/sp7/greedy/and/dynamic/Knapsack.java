package sp7.greedy.and.dynamic;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Тимофей решил отправиться в поход. Ему надо собрать рюкзак.
 * Так как поход долгий и трудный, необходимо подбирать вещи вдумчиво.
 * Каждому предмету Тимофей присвоил условную значимость: она равна ci для предмета с номером i.
 * Также каждый предмет весит mi килограммов. А грузоподъёмность рюкзака равна M килограмм.
 * Найдите максимальную суммарную значимость предметов, которые Тимофей может взять с собой,
 * не порвав рюкзак, и укажите, как набрать эту значимость.
 *
 * В первой строке вводится число предметов n, не превышающее 100 и грузоподъемность M, не превышающая 10^4.
 * Далее следуют описания предметов по одному в строке.
 * Каждый предмет описывается парой mi, ci, оба числа не превосходят 100 по модулю.
 *
 * Выведите в первой строке единственное число — сколько предметов надо взять.
 * Во второй строке перечислите их номера (нумерация с единицы). Если ответов несколько, то выведите любой.
 */
public class Knapsack {

    private static int[][] count(int capacity, int[] weight, int[] cost) {
        int[][] dp = new int[weight.length][capacity + 1];

        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                int prevBestCost = i > 0 ? dp[i - 1][j] : 0;
                int currentItemCost = cost[i];
                int remainingCapacityCost = i > 0 && j >= weight[i] ? dp[i - 1][j - weight[i]] : 0;
                int newBestCost = j >= weight[i] ? currentItemCost + remainingCapacityCost : 0;

                dp[i][j] = Math.max(prevBestCost, newBestCost);
            }
        }

        return dp;
    }

    private static Deque<Integer> items(int capacity, int[] weight, int[] cost, int[][] dp) {
        Deque<Integer> stack = new LinkedList<>();

        int i = weight.length - 1;
        int j = capacity;

        while (i >= 0 && j >= 0) {
            int prevCost = i > 0 && j >= weight[i] ? dp[i - 1][j - weight[i]] : 0;
            if (dp[i][j] - prevCost == cost[i]) {
                stack.push(i);
                j -= weight[i];
                i--;
            } else {
                i--;
            }
        }

        return stack;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int capacity = Integer.parseInt(s[1]);
            int[] weight = new int[n];
            int[] cost = new int[n];

            for (int i = 0; i < n; i++) {
                String[] split = reader.readLine().split(" ");
                weight[i] = Integer.parseInt(split[0]);
                cost[i] = Integer.parseInt(split[1]);
            }

            int[][] dp = count(capacity, weight, cost);
            Deque<Integer> items = items(capacity, weight, cost, dp);
            System.out.println(items.size());

            while (!items.isEmpty()) {
                writer.write(items.pop() + 1 + " ");
            }
        }
    }
}
