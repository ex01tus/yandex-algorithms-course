package sp0.free;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Вам дана статистика по числу запросов в секунду к вашему любимому рекомендательному сервису.
 * Измерения велись n секунд.
 * В секунду i поступает qi запросов.
 * Примените метод скользящего среднего с длиной окна k к этим данным и выведите результат.
 *
 * В первой строке передаётся натуральное число n, количество секунд, в течение которых велись измерения. 1 ≤ n ≤ 105
 * Во второй строке через пробел записаны n целых неотрицательных чисел qi, каждое лежит в диапазоне от 0 до 103.
 * В третьей строке записано натуральное число k (1 ≤ k ≤ n) —– окно сглаживания.
 *
 * Выведите через пробел результат применения метода скользящего среднего к серии измерений.
 * Должно быть выведено n - k + 1 элементов, каждый элемент -— вещественное (дробное) число.
 */
public class MovingAverage {

    private static List<Double> movingAverage(int n, List<Integer> arr, int windowSize) {
        List<Double> result = new ArrayList<>(n - windowSize + 1);

        int tmpSum = 0;
        for (int i = 0; i < windowSize; i++) {
            tmpSum += arr.get(i);
        }
        result.add((double) tmpSum / windowSize);

        for (int i = 0; i < n - windowSize; i++) {
            tmpSum -= arr.get(i);
            tmpSum += arr.get(i + windowSize);
            result.add((double) tmpSum / windowSize);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int windowSize = readInt(reader);
            List<Double> result = movingAverage(n, arr, windowSize);
            for (double elem : result) {
                writer.write(elem + " ");
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}