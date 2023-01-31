package sp8.strings;

import java.io.*;

/**
 * В этой задаче вам необходимо посчитать префикс-функцию для заданной строки.
 *
 * На вход подаётся строка, состоящая из строчных латинских букв. Длина строки не превосходит 10^6.
 *
 * Если длина входной строки L, то выведите через пробел
 * L целых неотрицательных чисел —– массив значений префикс-функции исходной строки.
 */
public class PrefixFunction {

    private static int[] prefixFunction(String line) {
        int[] function = new int[line.length()];

        char[] arr = line.toCharArray();
        for (int i = 1; i < arr.length; i++) {
            int k = function[i - 1];

            while (k != 0 && arr[k] != arr[i]) {
                k = function[k - 1];
            }

            function[i] = arr[k] == arr[i] ? k + 1 : 0;
        }

        return function;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String line = reader.readLine();

            int[] function = prefixFunction(line);
            for (int e : function) {
                writer.write(e + " ");
            }
        }
    }
}
