package sp3.recursion.and.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Чтобы выбрать самый лучший алгоритм для решения задачи, Гоша продолжил изучать разные сортировки.
 * На очереди сортировка пузырьком — https://ru.wikipedia.org/wiki/Сортировка_пузырьком
 * Её алгоритм следующий (сортируем по неубыванию):
 * На каждой итерации проходим по массиву, поочередно сравнивая пары соседних элементов.
 * Если элемент на позиции i больше элемента на позиции i + 1, меняем их местами.
 * После первой итерации самый большой элемент всплывёт в конце массива.
 * Проходим по массиву, выполняя указанные действия до тех пор, пока на очередной итерации не окажется,
 * что обмены больше не нужны, то есть массив уже отсортирован.
 * После не более чем n – 1 итераций выполнение алгоритма заканчивается,
 * так как на каждой итерации хотя бы один элемент оказывается на правильной позиции.
 * Помогите Гоше написать код алгоритма.
 *
 * В первой строке на вход подаётся натуральное число n — длина массива, 2 ≤ n ≤ 1000.
 * Во второй строке через пробел записано n целых чисел.
 * Каждое из чисел по модулю не превосходит 1000.
 *
 * После каждого прохода по массиву, на котором какие-то элементы меняются местами, выводите его промежуточное состояние.
 * Таким образом, если сортировка завершена за k меняющих массив итераций, то надо вывести k строк по n чисел в каждой
 * — элементы массива после каждой из итераций.
 * Если массив был изначально отсортирован, то просто выведите его.
 */
public class BubbleSort {

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean wasSwapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    wasSwapped = true;
                }
            }

            if (!wasSwapped) {
                if (i == 0) print(arr);
                break;
            } else {
                print(arr);
            }
        }
    }

    private static void print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int j : arr) {
            sb.append(j).append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(tokenizer.nextToken());
            }

            bubbleSort(arr);
        }
    }
}
