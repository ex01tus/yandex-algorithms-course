package sp3.recursion.and.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Рита решила оставить у себя одежду только трёх цветов: розового, жёлтого и малинового.
 * После того как вещи других расцветок были убраны, Рита захотела отсортировать свой новый гардероб по цветам.
 * Сначала должны идти вещи розового цвета, потом —– жёлтого, и в конце —– малинового. Помогите Рите справиться с этой задачей.
 * Примечание: попробуйте решить задачу за один проход по массиву!
 *
 * В первой строке задано количество предметов в гардеробе: n –— оно не превосходит 1000000.
 * Во второй строке даётся массив, в котором указан цвет для каждого предмета.
 * Розовый цвет обозначен 0, жёлтый —– 1, малиновый –— 2.
 *
 * Нужно вывести в строку через пробел цвета предметов в правильном порядке.
 */
public class CountingSort {

    private static void countingSort(int[] arr) {
        int[] count = new int[]{0, 0, 0};
        for (int current : arr) {
            count[current] = count[current] + 1;
        }

        int k = 0;
        for (int i = 0; i < count.length; i++) {
            int elementCount = count[i];
            for (int j = 0; j < elementCount; j++) {
                arr[k] = i;
                k++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(tokenizer.nextToken());
            }

            countingSort(arr);
            print(arr);
        }
    }

    private static void print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int j : arr) {
            sb.append(j).append(" ");
        }

        System.out.println(sb);
    }
}
