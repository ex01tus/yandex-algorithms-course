package sp3.recursion.and.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Тимофей решил купить несколько домов на знаменитом среди разработчиков Алгосском архипелаге.
 * Он нашёл n объявлений о продаже, где указана стоимость каждого дома в алгосских франках. А у Тимофея есть k франков.
 * Помогите ему определить, какое наибольшее количество домов на Алгосах он сможет приобрести за эти деньги.
 *
 * В первой строке через пробел записаны натуральные числа n и k.
 * n — количество домов, которые рассматривает Тимофей, оно не превосходит 1000;
 * k — общий бюджет, не превосходит 10000;
 * В следующей строке через пробел записано n стоимостей домов. Каждое из чисел не превосходит 10000.
 * Все стоимости — натуральные числа.
 *
 * Выведите одно число —– наибольшее количество домов, которое может купить Тимофей.
 */
public class Houses {

    private static int count(List<Integer> houses, int budget) {
        Collections.sort(houses);

        int counter = 0;
        for (int house : houses) {
            if (house <= budget) {
                counter++;
                budget -= house;
            } else {
                break;
            }
        }

        return counter;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int budget = Integer.parseInt(reader.readLine().split(" ")[1]);
            List<Integer> houses = readList(reader);

            int result = count(houses, budget);
            System.out.println(result);
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
