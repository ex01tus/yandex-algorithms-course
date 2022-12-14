package sp2.basic.data.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * У Тимофея было очень много стажёров, целых N (0 ≤ N ≤ 10^6) человек.
 * Каждый стажёр хотел быть лучше своих предшественников, поэтому i-й стажёр делал столько коммитов,
 * сколько делали два предыдущих стажёра в сумме. Два первых стажёра были менее инициативными — они сделали по одному коммиту.
 * Пусть Fi —– число коммитов, сделанных i-м стажёром (стажёры нумеруются с нуля).
 * Первые два стажёра сделали по одному коммиту: F0=F1=1. Для всех i≥ 2 выполнено Fi=Fi−1+Fi−2.
 * Определите, сколько кода напишет следующий стажёр –— найдите последние k цифр числа Fn.
 *
 * В первой строке записаны через пробел два целых числа n (0 ≤ n ≤ 10^6) и k (1 ≤ k ≤ 8).
 *
 * Выведите единственное число – последние k цифр числа Fn.
 * Если в искомом числе меньше k цифр, то выведите само число без ведущих нулей.
 */
public class FibonacciLastDigits {

    private static int getLastDigits(int n, int k) {
        List<Integer> prev = new ArrayList<>(n);
        prev.add(1);
        prev.add(1);

        int pow = 10;
        for (int i = 1; i < k; i++) {
            pow *= 10;
        }

        for (int i = 2; i <= n; i++) {
            int value = (prev.get(i - 2) + prev.get(i - 1)) % pow;
            prev.add(value);
        }

        return prev.get(n);
    }

    private static int getLastDigitsMemoryEffective(int n, int k) {
        int leftPrev = 1;
        int rightPrev = 1;

        int pow = 10;
        for (int i = 1; i < k; i++) {
            pow *= 10;
        }

        for (int i = 2; i <= n; i++) {
            int value = (leftPrev + rightPrev) % pow;
            leftPrev = rightPrev;
            rightPrev = value;
        }

        return rightPrev;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);

            int digits = getLastDigitsMemoryEffective(n, k);
            System.out.println(digits);
        }
    }
}
