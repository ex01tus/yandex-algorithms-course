package sp8.strings;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Гоша измерял температуру воздуха n дней подряд. В результате у него получился некоторый временной ряд.
 * Теперь он хочет посмотреть, как часто встречается некоторый шаблон в получившейся последовательности.
 * Однако температура — вещь относительная, поэтому Гоша решил, что при поиске шаблона длины m (a1, a2, ..., am)
 * стоит также рассматривать сдвинутые на константу вхождения. Это значит, что если для некоторого числа c в исходной
 * последовательности нашёлся участок вида (a1 + c, a2 + c, ... , am + c),
 * то он тоже считается вхождением шаблона (a1, a2, ..., am).
 * По заданной последовательности измерений X и шаблону A=(a1, a2, ..., am) определите все вхождения A в X,
 * допускающие сдвиг на константу.
 *
 * В первой строке дано количество сделанных измерений n — натуральное число, не превышающее 10^4.
 * Во второй строке через пробел записаны n целых чисел xi, 0 ≤ xi ≤ 10^3 –— результаты измерений.
 * В третьей строке дано натуральное число m –— длина искомого шаблона, 1≤ m ≤ n.
 * В четвёртой строке даны m целых чисел ai — элементы шаблона, 0 ≤ ai ≤ 10^3.
 *
 * Выведите через пробел в порядке возрастания все позиции, на которых начинаются вхождения шаблона A в последовательность X.
 * Нумерация позиций начинается с единицы.
 */
public class ShiftSearch {

    private static List<Integer> search(List<Integer> input, List<Integer> pattern) {
        if (pattern.size() > input.size()) return List.of();

        List<Integer> result = new ArrayList<>();

        for (int pos = 0; pos <= input.size() - pattern.size(); pos++) {
            boolean isMatch = true;
            int shift = input.get(pos) - pattern.get(0);
            for (int offset = 0; offset < pattern.size(); offset++) {
                int newShift = input.get(pos + offset) - pattern.get(offset);
                if (newShift != shift) {
                    isMatch = false;
                    break;
                }
            }

            if (isMatch) result.add(pos);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> input = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int m = Integer.parseInt(reader.readLine());
            List<Integer> pattern = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            List<Integer> result = search(input, pattern);
            for (int r : result) {
                writer.write(r + 1 + " ");
            }
        }
    }
}
