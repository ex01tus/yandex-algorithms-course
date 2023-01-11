package sp0.free;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Рита и Гоша играют в игру. У Риты есть n фишек, на каждой из которых написано количество очков.
 * Фишки лежат на столе в порядке неубывания очков на них. Сначала Гоша называет число k,
 * затем Рита должна выбрать две фишки, сумма очков на которых равна заданному числу.
 * Рите надоело искать фишки самой, и она решила применить свои навыки программирования для решения этой задачи.
 * Помогите ей написать программу для поиска нужных фишек.
 *
 * В первой строке записано количество фишек n, 2 ≤ n ≤ 10^5.
 * Во второй строке записано n целых чисел в порядке неубывания —– очки на фишках Риты в диапазоне от -10^5 до 10^5.
 * В третьей строке —– загаданное Гошей целое число k, -10^5 ≤ k ≤ 10^5.
 *
 * Нужно вывести два числа —– очки на двух фишках, в сумме дающие k.
 * Если таких пар несколько, то можно вывести любую из них.
 * Если таких пар не существует, то вывести «None».
 */
public class TwoSum {

    private static List<Integer> twoSum(List<Integer> arr, int targetSum) {
        Set<Integer> previous = new HashSet<>();

        for (int a : arr) {
            int b = targetSum - a;
            if (previous.contains(b)) return List.of(a, b);
            previous.add(a);
        }

        return List.of();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int targetSum = readInt(reader);
            List<Integer> result = twoSum(arr, targetSum);
            if (result.isEmpty()) {
                System.out.println("None");
            } else {
                System.out.println(result.get(0) + " " + result.get(1));
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return  Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}