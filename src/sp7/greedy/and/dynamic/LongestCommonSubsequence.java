package sp7.greedy.and.dynamic;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * В мире последовательностей нет гороскопов. Поэтому когда две последовательности хотят понять,
 * могут ли они счастливо жить вместе, они оценивают свою совместимость как длину их наибольшей общей подпоследовательности.
 * Подпоследовательность получается из последовательности удалением некоторого (возможно, нулевого) числа элементов.
 * То есть элементы сохраняют свой относительный порядок, но не обязаны изначально идти подряд.
 * Найдите наибольшую общую подпоследовательность двух одиноких последовательностей и выведите её!
 *
 * В первой строке дано число n — количество элементов в первой последовательности (1 ≤ n ≤ 1000).
 * Во второй строке даны n чисел ai (0 ≤ |ai| ≤ 10^9) — элементы первой последовательности.
 * Аналогично в третьей строке дано m (1 ≤ m ≤ 1000) — число элементов второй последовательности.
 * В четвертой строке даны элементы второй последовательности через пробел bi (0 ≤ |bi| ≤ 10^9).
 *
 * Сначала выведите длину найденной наибольшей общей подпоследовательности, во второй строке выведите индексы элементов
 * первой последовательности, которые в ней участвуют, в третьей строке — индексы элементов второй последовательности.
 * Нумерация индексов с единицы, индексы должны идти в корректном порядке.
 * Если возможных НОП несколько, то выведите любую.
 */
public class LongestCommonSubsequence {

    private static int[][] count(List<Integer> first, List<Integer> second) {
        int[][] memo = new int[first.size()][second.size()];

        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                if (Objects.equals(first.get(i), second.get(j))) {
                    int prevLongest = (i - 1  >= 0 && j - 1>= 0) ? memo[i - 1][j - 1] : 0;
                    memo[i][j] = prevLongest + 1;
                } else {
                    int prevFirst = i - 1 >= 0 ? memo[i - 1][j] : 0;
                    int prevSecond = j - 1 >= 0 ? memo[i][j - 1] : 0;
                    memo[i][j] = Math.max(prevFirst, prevSecond);
                }
            }
        }

        return memo;
    }

    private static List<Deque<Integer>> commonIndices(List<Integer> first, List<Integer> second, int[][] memo) {
        Deque<Integer> firstIndices = new LinkedList<>();
        Deque<Integer> secondIndices = new LinkedList<>();

        int i = first.size() - 1;
        int j = second.size() - 1;

        while (i >= 0 && j >= 0) {
            if (Objects.equals(first.get(i), second.get(j))) {
                firstIndices.push(i);
                secondIndices.push(j);
                i--;
                j--;
            } else if (i - 1 >= 0 && memo[i - 1][j] == memo[i][j]) {
                i--;
            } else if (j - 1 >= 0 && memo[i][j - 1] == memo[i][j]) {
                j--;
            } else {
                break;
            }
        }

        return List.of(firstIndices, secondIndices);
    }

    private static Deque<Integer> commonSubsequence(List<Integer> first, List<Integer> second, int[][] memo) {
        Deque<Integer> subsequence = new LinkedList<>();

        int i = first.size() - 1;
        int j = second.size() - 1;

        while (i >= 0 && j >= 0) {
            if (Objects.equals(first.get(i), second.get(j))) {
                subsequence.push(first.get(i));
                i--;
                j--;
            } else if (i - 1 >= 0 && memo[i - 1][j] == memo[i][j]) {
                i--;
            } else if (j - 1 >= 0 && memo[i][j - 1] == memo[i][j]) {
                j--;
            } else {
                break;
            }
        }

        return subsequence;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> first = readList(reader);
            int m = readInt(reader);
            List<Integer> second = readList(reader);

            int[][] memo = count(first, second);
            System.out.println(memo[first.size() - 1][second.size() - 1]);
            List<Deque<Integer>> indices = commonIndices(first, second, memo);

            printResult(writer, indices);
        }
    }

    private static void printResult(BufferedWriter writer, List<Deque<Integer>> indices) throws IOException {
        while (!indices.get(0).isEmpty()) {
            writer.write(indices.get(0).pop() + 1 + " ");
        }

        writer.newLine();

        while (!indices.get(1).isEmpty()) {
            writer.write(indices.get(1).pop() + 1 + " ");
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(toList());
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
