package sp7.greedy.and.dynamic;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Теперь черепашке Кондратине надо узнать не только, сколько цветочков она может собрать,
 * но и как ей построить свой маршрут для этого. Помогите ей!
 * Напомним, что Кондратине надо дойти от левого нижнего до правого верхнего угла,
 * а передвигаться она умеет только вверх и вправо.
 *
 * В первой строке даны размеры поля n и m (через пробел). Оба числа лежат в диапазоне от 1 до 1000.
 * В следующих n строках задано поле. Каждая строка состоит из m символов 0 или 1 и завершается переводом строки.
 * Если в клетке записана единица, то в ней растет цветочек.
 *
 * Выведите в первой строке максимальное количество цветочков, которое сможет собрать Кондратина.
 * Во второй строке выведите маршрут в виде последовательности символов «U» и «R»,
 * где «U» означает передвижение вверх, а «R» – передвижение вправо.
 * Если возможных оптимальных путей несколько, то выведите любой.
 */
public class CollectingFlowersWithPath {

    private static Deque<Character> path(int[][] memo) {
        Deque<Character> path = new LinkedList<>();

        int i = 0;
        int j = memo[0].length - 1;
        while (i < memo.length && j >= 0) {
            int left = j - 1 >= 0 ? memo[i][j - 1] : -1;
            int bottom = i + 1 < memo.length ? memo[i + 1][j] : -1;

            if (left == -1 && bottom == -1) break;

            if (left == -1 || left < bottom) {
                path.push('U');
                i += 1;
            } else {
                path.push('R');
                j -= 1;
            }
        }

        return path;
    }

    private static int[][] count(int[][] field) {
        int[][] memo = new int[field.length][field[0].length];

        for (int i = field.length - 1; i >= 0; i--) {
            for (int j = 0; j < field[0].length; j++) {
                int left = j - 1 >= 0 ? memo[i][j - 1] : 0;
                int bottom = i + 1 < memo.length ? memo[i + 1][j] : 0;

                memo[i][j] = field[i][j] + Math.max(left, bottom);
            }
        }

        return memo;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            int[][] field = new int[n][m];
            for (int i = 0; i < n; i++) {
                String line = reader.readLine();
                for (int c = 0; c < line.length(); c++) {
                    field[i][c] = Character.getNumericValue(line.charAt(c));
                }
            }

            int[][] count = count(field);
            Deque<Character> path = path(count);

            System.out.println(count[0][field[0].length - 1]);
            while (!path.isEmpty()) {
                writer.write(path.pop());
            }
        }
    }
}
