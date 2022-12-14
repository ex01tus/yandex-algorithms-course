package sp4.hash.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Гоша увлёкся хоккеем и часто смотрит трансляции матчей. Чтобы более-менее разумно оценивать силы команд,
 * он сравнивает очки, набранные во всех матчах каждой командой.
 * Гоша попросил вас написать программу, которая по результатам игр двух выбранных команд найдёт наибольший
 * по длине отрезок матчей, когда эти команды зарабатывали одинаковые очки.
 * Рассмотрим первый пример:
 * Результаты первой команды: [1 2 3 2 1].
 * Результаты второй команды: [3 2 1 5 6].
 * Наиболее продолжительный общий отрезок этих массивов имеет длину 3 –— это [3 2 1].
 *
 * В первой строке находится число n (1 ≤ n ≤ 10000) –— количество матчей, которые были сыграны первой командой.
 * Во второй строке записано n целых чисел –— очки в этих играх.
 * В третьей строке дано число m (1 ≤ m ≤ 10000) —– количество матчей, которые сыграла вторая команда.
 * В четвертой строке заданы m целых чисел —– результаты второй команды.
 * Число очков, заработанных в одной игре, лежит в диапазоне от 0 до 255.
 *
 * Выведите целое неотрицательное число —– максимальное количество матчей подряд, в которых команды зарабатывали одинаковые очки.
 */
public class LongestCommonSubsequence {

    private static int longestCommonSubsequence(List<Integer> first, List<Integer> second) {
        Map<Integer, List<Integer>> secondStarts = findStarts(second);

        int max = 0;
        for (int i = 0; i < first.size(); i++) {
            int value = first.get(i);
            List<Integer> starts = secondStarts.get(value);

            if (starts == null) {
                continue;
            }

            for (int j : starts) {
                int k = 0;
                while (i + k < first.size()
                        && j + k < second.size()
                        && Objects.equals(first.get(i + k), second.get(j + k))) {
                    k++;
                }

                max = Math.max(max, k);
            }
        }

        return max;
    }

    private static Map<Integer, List<Integer>> findStarts(List<Integer> second) {
        Map<Integer, List<Integer>> secondStarts = new HashMap<>();
        for (int i = 0; i < second.size(); i++) {
            int value = second.get(i);
            List<Integer> starts = secondStarts.get(value);
            if (starts == null) {
                List<Integer> newStarts = new ArrayList<>();
                newStarts.add(i);
                secondStarts.put(value, newStarts);
            } else {
                starts.add(i);
            }
        }

        return secondStarts;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int firstSize = Integer.parseInt(reader.readLine());
            List<Integer> first = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int secondSize = Integer.parseInt(reader.readLine());
            List<Integer> second = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            int max = longestCommonSubsequence(first, second);
            System.out.println(max);
        }
    }
}
