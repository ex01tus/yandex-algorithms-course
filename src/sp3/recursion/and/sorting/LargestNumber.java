package sp3.recursion.and.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Вечером ребята решили поиграть в игру «Большое число».
 * Даны числа. Нужно определить, какое самое большое число можно из них составить.
 *
 * В первой строке записано n — количество чисел. Оно не превосходит 100.
 * Во второй строке через пробел записаны n неотрицательных чисел, каждое из которых не превосходит 1000.
 *
 * Нужно вывести самое большое число, которое можно составить из данных чисел.
 */
public class LargestNumber {

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i;

            while (j > 0 && compare(arr[j - 1], key) < 0) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = key;
        }
    }

    private static int compare(int a, int b) {
        String first = "" + a + b;
        String second = "" + b + a;
        return first.compareTo(second);
    }

    public static void main(String[] args) throws IOException {
//        Comparator<Integer> comparator = (a, b) -> ("" + a + b).compareTo("" + b + a);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(tokenizer.nextToken());
            }

            insertionSort(arr);

            StringBuilder sb = new StringBuilder();
            for (int a : arr) {
                sb.append(a);
            }
            System.out.println(sb);
        }
    }
}
