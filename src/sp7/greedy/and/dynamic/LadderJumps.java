package sp7.greedy.and.dynamic;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Алла хочет доказать, что она умеет прыгать вверх по лестнице быстрее всех.
 * На этот раз соревнования будут проходить на специальной прыгательной лестнице.
 * С каждой её ступеньки можно прыгнуть вверх на любое расстояние от 1 до k. Число k придумывает Алла.
 * Гоша не хочет проиграть, поэтому просит вас посчитать количество способов допрыгать от первой ступеньки до n-й.
 * Изначально все стоят на первой ступеньке.
 *
 * В единственной строке даны два числа — n и k (1 ≤ n ≤ 1000, 1 ≤ k ≤ n).
 *
 * Выведите количество способов по модулю 10^9 + 7.
 */
public class LadderJumps {

    private static long count(int k, int size) {
        if (size == 1) return 1;

        Map<Integer, Long> memo = new HashMap<>();
        memo.put(1, 1L);

        for (int stair = 2; stair <= size; stair++) {
            long count = 0L;
            for (int step = 1; step <= k; step++) {
                if (stair - step < 1) break;
                count += memo.get(stair - step);
            }

//            count = count % ((long) Math.pow(10, 9) + 7);
            memo.put(stair, count);
        }

        return memo.get(size);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] s = reader.readLine().split(" ");
            int size = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);

            long count = count(k , size);
            System.out.println(count);
        }
    }
}
