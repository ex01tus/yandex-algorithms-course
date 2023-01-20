package sp7.greedy.and.dynamic;

import java.io.*;

/**
 * Черепаха Кондратина путешествует по клетчатому полю из n строк и m столбцов.
 * В каждой клетке либо растёт цветочек, либо не растёт. Кондратине надо добраться из левого нижнего
 * в правый верхний угол и собрать как можно больше цветочков.
 * Помогите ей с этой сложной задачей и определите, какое наибольшее число цветочков она сможет собрать при условии,
 * что Кондратина умеет передвигаться только на одну клетку вверх или на одну клетку вправо за ход.
 *
 * В первой строке даны размеры поля n и m (через пробел). Оба числа лежат в диапазоне от 1 до 1000.
 * В следующих n строках задано поле. Каждая строка состоит из m символов 0 или 1, записанных подряд без пробелов,
 * и завершается переводом строки. Если в клетке записана единица, то в ней растёт цветочек.
 *
 * Выведите единственное число — максимальное количество цветочков, которое сможет собрать Кондратина.
 */
public class CollectingFlowers {

    private static int collect(int[][] field) {
        int[][] memo = new int[field.length][field[0].length];

        for (int i = field.length - 1; i >= 0; i--) {
            for (int j = 0; j < field[0].length; j++) {
                int left = j - 1 >= 0 ? memo[i][j - 1] : 0;
                int bottom = i + 1 < memo.length ? memo[i + 1][j] : 0;

                memo[i][j] = field[i][j] + Math.max(left, bottom);
            }
        }

        return memo[0][field[0].length - 1];
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

            int flowers = collect(field);
            System.out.println(flowers);
        }
    }
}
