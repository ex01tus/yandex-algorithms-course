package sp1.intro;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Вася просил Аллу помочь решить задачу. На этот раз по информатике.
 * Для неотрицательного целого числа X списочная форма –— это массив его цифр слева направо.
 * К примеру, для 1231 списочная форма будет [1,2,3,1]. На вход подается количество цифр числа Х,
 * списочная форма неотрицательного числа Х и неотрицательное число K. Числа К и Х не превосходят 10000.
 * Нужно вернуть списочную форму числа X + K.
 *
 * В первой строке — длина списочной формы числа X. На следующей строке — сама списочная форма с цифрами записанными через пробел.
 * В последней строке записано число K, 0 ≤ K ≤ 10000.
 *
 * Выведите списочную форму числа X+K.
 */
public class ListFormatSum {

    private static List<Character> getSum(List<Integer> numberList, int k) {
        StringBuilder sb = new StringBuilder();
        for (int digit : numberList) {
            sb.append(digit);
        }

        String sum = String.valueOf(k + Integer.parseInt(sb.toString()));
        List<Character> result = new ArrayList<>(sum.length());
        for (int i = 0; i < sum.length(); i++) {
            result.add(sum.charAt(i));
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int numberLength = readInt(reader);
            List<Integer> numberList = readList(reader);
            int k = readInt(reader);
            List<Character> sum = getSum(numberList, k);
            for (char elem : sum) {
                writer.write(elem + " ");
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}