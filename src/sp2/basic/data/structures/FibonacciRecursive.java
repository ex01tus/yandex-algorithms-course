package sp2.basic.data.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * У Тимофея было 0<=n<=32 стажёров. Каждый стажёр хотел быть лучше своих предшественников, поэтому i-й стажёр
 * делал столько коммитов, сколько делали два предыдущих стажёра в сумме.
 * Два первых стажёра были менее инициативными —– они сделали по одному коммиту.
 * Определите, сколько кода напишет следующий стажёр.
 * Решение должно быть реализовано рекурсивно.
 *
 * На вход подаётся n — целое число в диапазоне от 0 до 32
 *
 * Нужно вывести Fn
 */
public class FibonacciRecursive {

    private static long fibonacci(int n) {
        if (n == 0 || n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            long fib = fibonacci(n);
            System.out.println(fib);
        }
    }
}
