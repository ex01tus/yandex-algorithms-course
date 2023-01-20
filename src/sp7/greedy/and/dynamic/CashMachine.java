package sp7.greedy.and.dynamic;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Тимофей пошёл снять деньги в банкомат. Ему нужно m франков. В банкомате в бесконечном количестве имеются купюры
 * различных достоинств. Всего различных достоинств n. Купюр каждого достоинства можно взять бесконечно много.
 * Нужно определить число способов, которыми Тимофей сможет набрать нужную сумму.
 *
 * В первой строке записано целое число m — сумма, которую нужно набрать. Во второй строке n — количество монет в банкомате.
 * Оба числа не превосходят 300. Далее в третьей строке записано n уникальных натуральных чисел,
 * каждое в диапазоне от 1 до 1000 –– достоинства купюр.
 *
 * Нужно вывести число способов, которым Тимофей сможет набрать нужную сумму.
 */
public class CashMachine {

    private static long count(int amount, List<Integer> coins) {
        if (amount < 1) return 0;

        long[] memo = new long[amount + 1];
        memo[0] = 1;

        for (int coin : coins) {
            for (int currentAmount = coin; currentAmount <= amount; currentAmount++) {
                int prev = currentAmount - coin;
                memo[currentAmount] += memo[prev];
            }
        }

        return memo[amount];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int amount = readInt(reader);
            int n = readInt(reader);
            List<Integer> coins = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            long count = count(amount, coins);
            System.out.println(count);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
