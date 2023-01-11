package sp3.recursion.and.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Вася решил накопить денег на два одинаковых велосипеда — себе и сестре.
 * У Васи есть копилка, в которую каждый день он может добавлять деньги (если, конечно, у него есть такая финансовая возможность).
 * В процессе накопления Вася не вынимает деньги из копилки.
 * У вас есть информация о росте Васиных накоплений — сколько у Васи в копилке было денег в каждый из дней.
 * Ваша задача — по заданной стоимости велосипеда определить:
 * - первый день, в которой Вася смог бы купить один велосипед,
 * - и первый день, в который Вася смог бы купить два велосипеда.
 * Подсказка: решение должно работать за O(log n).
 *
 * В первой строке дано число дней n, по которым велись наблюдения за Васиными накоплениями. 1 ≤ n ≤ 10^6.
 * В следующей строке записаны n целых неотрицательных чисел. Числа идут в порядке неубывания. Каждое из чисел не превосходит 10^6.
 * В третьей строке записано целое положительное число s — стоимость велосипеда. Это число не превосходит 10^6.
 *
 * Нужно вывести два числа — номера дней по условию задачи.
 * Если необходимой суммы в копилке не нашлось, нужно вернуть -1 вместо номера дня.
 */
public class FirstGreaterBinarySearch {

    private static int find(List<Integer> arr, int value, int left, int right) {
        if (value > arr.get(right)) {
            return -1;
        }

        if (left == right) {
            return left + 1; // only because days are numbered from 1
        }

        int mid = (left + right) / 2;
        if (value <= arr.get(mid)) {
            return find(arr, value, left, mid);
        } else {
            return find(arr, value, mid + 1, right);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int days = Integer.parseInt(reader.readLine());
            List<Integer> arr = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());
            int cost = Integer.parseInt(reader.readLine());

            int firstBike = find(arr, cost, 0, arr.size() - 1);
            int secondBike = firstBike == -1 ? -1 : find(arr, cost * 2, firstBike - 1, arr.size() - 1);
            System.out.println(firstBike + " " + secondBike);
        }
    }
}
