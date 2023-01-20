package sp7.greedy.and.dynamic;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Гоша практикуется в динамическом программировании — он хочет быстро считать числа Фибоначчи.
 * Напомним, что числа Фибоначчи определены как последовательность . F0 = F1 = 1, Fn = Fn -1 + Fn-2, n ≥ 2.
 * Помогите Гоше решить эту задачу.
 *
 * В единственной строке дано целое число n (0 ≤ n ≤ 10^6).
 *
 * Вычислите значение Fn по модулю 10^9 + 7 и выведите его.
 */
public class Fibonacci {

    private static long fibonacci(int n) {
        Map<Integer, Long> memo = new HashMap<>();
        memo.put(0, 1L);
        memo.put(1, 1L);

        for (int i = 2; i <= n; i++) {
            long fib = memo.get(i - 2) + memo.get(i - 1);
//            fib = fib % ((long) Math.pow(10, 9) + 7);
            memo.put(i, fib);
        }

        return memo.get(n);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());

            long fib = fibonacci(n);
            System.out.println(fib);
        }
    }
}
