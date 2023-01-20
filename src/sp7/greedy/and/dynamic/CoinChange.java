package sp7.greedy.and.dynamic;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Алла хочет купить дом на Алгосах. Для этого ей надо много наличных, которые она собирается получить в банкомате.
 * Банкомат приличный, поэтому в нём есть бесконечно много банкнот каждого номинала.
 * Всего номиналов k штук. Дом мечты Аллы стоит x франков.
 * Найдите минимальное количество банкнот, которые в сумме дадут x франков. Если в набор входит несколько банкнот
 * одинакового номинала, то учитывать надо их все.
 * Например, если необходимо набрать 15 франков, а в банкомате купюры по 5 франков, то минимальное число купюр —- 3.
 *
 * В первой строке дана сумма, которую хочет получить Алла –— натуральное число x (1 ≤ x ≤ 10^4).
 * Во второй строке дано число различных номиналов k. В третьей строке даны k чисел (1 ≤ k ≤ 1000) —– номиналы купюр.
 * Все номиналы лежат в диапазоне от 1 до 10^4. Номиналы купюр могут повторяться.
 *
 * Выведите единственное число —– минимальное количество купюр, которыми можно набрать x франков.
 * Если нельзя набрать в точности x франков, то выведите -1.
 */
public class CoinChange {

    private static int minCoinsNumber(int amount, List<Integer> coins) {
        if (amount < 1) return 0;

        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);

        for (int i = 1; i <= amount; i++) {
            int count = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin < 0) continue;

                Integer prev = memo.get(i - coin);
                if (prev != null) {
                    count = Math.min(count, prev + 1);
                }
            }

            if (count != Integer.MAX_VALUE) {
                memo.put(i, count);
            }
        }

        return memo.getOrDefault(amount, -1);
    }



    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int amount = readInt(reader);
            int n = readInt(reader);
            List<Integer> coins = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int min = minCoinsNumber(amount, coins);
            System.out.println(min);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
