package sp8.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Напишите программу, которая будет заменять в тексте все вхождения строки s на строку t.
 * Гарантируется, что никакие два вхождения шаблона s не пересекаются друг с другом.
 *
 * В первой строке дан текст —– это строка из строчных букв английского алфавита, длина которой не превышает 10^6.
 * Во второй строке записан шаблон s, вхождения которого будут заменены.
 * В третьей строке дана строка t, которая будет заменять вхождения.
 * Обе строки s и t состоят из строчных букв английского алфавита, длина каждой строки не превосходит 10^5.
 * Размер итоговой строки не превосходит 2⋅10^6.
 *
 * В единственной строке выведите результат всех замен — текст, в котором все вхождения s заменены на t.
 */
public class GlobalReplacement {

    private static String replace(String original, String pattern, String replacement) {
        int[] prefixFunction = prefixFunction(pattern + "#" + original);
        List<Integer> insertionPositions = getInsertionPositions(pattern, prefixFunction);

        int current = 0;
        StringBuilder sb = new StringBuilder();
        for (int pos : insertionPositions) {
            sb.append(original, current, pos);
            sb.append(replacement);
            current = pos + pattern.length();
        }

        sb.append(original.substring(current));

        return sb.toString();
    }

    private static int[] prefixFunction(String line) {
        char[] arr = line.toCharArray();
        int[] function = new int[line.length()];

        for (int i = 1; i < arr.length; i++) {
            int k = function[i - 1];

            while (k != 0 && arr[i] != arr[k]) {
                k = function[k - 1];
            }

            function[i] = arr[i] == arr[k] ? k + 1 : 0;
        }

        return function;
    }

    private static List<Integer> getInsertionPositions(String pattern, int[] prefixFunction) {
        List<Integer> insertionPositions = new ArrayList<>();
        for (int i = 0; i < prefixFunction.length; i++) {
            if (prefixFunction[i] == pattern.length()) {
                insertionPositions.add(i - 2 * pattern.length());
            }
        }

        return insertionPositions;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String original = reader.readLine();
            String pattern = reader.readLine();
            String replacement = reader.readLine();

            String result = replace(original, pattern, replacement);
            System.out.println(result);
        }
    }
}
