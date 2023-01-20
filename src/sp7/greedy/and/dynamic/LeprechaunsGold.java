package sp7.greedy.and.dynamic;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Лепреконы в данной задаче появились по соображениям общей морали, так как грабить банки — нехорошо.
 * Вам удалось заключить неплохую сделку с лепреконами, поэтому они пустили вас в своё хранилище золотых слитков.
 * Все слитки имеют единую пробу, то есть стоимость 1 грамма золота в двух разных слитках одинакова. 
 * В хранилище есть n слитков, вес i-го слитка равен wi кг. У вас есть рюкзак, вместимость которого M килограмм.
 * Выясните максимальную суммарную массу золотых слитков, которую вы сможете унести.
 *
 * В первой строке дано число слитков —– натуральное число n (1 ≤ n ≤ 1000)
 * и вместимость рюкзака –— целое число M (0 ≤ M ≤ 10^4).
 * Во второй строке записано n натуральных чисел wi (1 ≤ wi ≤ 10^4) -— массы слитков.
 *
 * Выведите единственное число — максимальную массу, которую можно забрать с собой.
 */
public class LeprechaunsGold {

    private static int max2D(List<Integer> ingots, int capacity) {
        int[][] dp = new int[ingots.size()][capacity + 1];

        for (int i = 0; i < ingots.size(); i++) {
            for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {
                int ingotWeight = ingots.get(i);
                int prevMaxWeight = i > 0 ? dp[i - 1][currentCapacity] : 0;
                int remainingCapacityWeight = (i > 0 && currentCapacity >= ingotWeight)
                        ? dp[i - 1][currentCapacity - ingotWeight] : 0;
                int newMaxWeight = currentCapacity >= ingotWeight
                        ? ingotWeight + remainingCapacityWeight : 0;

                dp[i][currentCapacity] = Math.max(prevMaxWeight, newMaxWeight);
            }
        }

        return dp[ingots.size() - 1][capacity];
    }

    private static int max1D(List<Integer> ingots, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int ingot : ingots) {
            for (int currentCapacity = capacity; currentCapacity >= ingot; currentCapacity--) {
                dp[currentCapacity] = Math.max(dp[currentCapacity], ingot + dp[currentCapacity - ingot]);
            }
        }

        return dp[capacity];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int capacity = Integer.parseInt(s[1]);
            List<Integer> ingots = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int max = max1D(ingots, capacity);
            System.out.println(max);
        }
    }
}
