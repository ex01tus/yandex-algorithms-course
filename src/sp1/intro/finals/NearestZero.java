package sp1.intro.finals;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Улица, на которой хочет жить Тимофей, имеет длину n, то есть состоит из n одинаковых идущих подряд участков.
 * На каждом участке либо уже построен дом, либо участок пустой. Тимофей ищет место для строительства своего дома.
 * Он очень общителен и не хочет жить далеко от других людей, живущих на этой улице.
 * Чтобы оптимально выбрать место для строительства, Тимофей хочет для каждого участка знать расстояние
 * до ближайшего пустого участка. (Для пустого участка эта величина будет равна нулю –— расстояние до самого себя).
 * Ваша задача –— помочь Тимофею посчитать искомые расстояния. Для этого у вас есть карта улицы.
 * Дома в городе Тимофея нумеровались в том порядке, в котором строились, поэтому их номера на карте никак не упорядочены.
 * Пустые участки обозначены нулями.
 *
 * В первой строке дана длина улицы —– n (1 ≤ n ≤ 10^6).
 * В следующей строке записаны n целых неотрицательных чисел — номера домов и обозначения пустых участков на карте (нули).
 * Гарантируется, что в последовательности есть хотя бы один ноль.
 * Номера домов (положительные числа) уникальны и не превосходят 10^9.
 *
 * Для каждого из участков выведите расстояние до ближайшего нуля.
 * Числа выводите в одну строку, разделяя их пробелами.
 */
public class NearestZero {

    private static final int EMPTY_SPOT = 0;

    private static List<Integer> calculateDistances(List<Integer> houses) {
        List<Integer> distances = new ArrayList<>(houses.size());

        goRight(houses, distances);
        goLeft(houses, distances);

        return distances;
    }

    private static void goRight(List<Integer> houses, List<Integer> distances) {
        int distance = houses.size();
        for (int house : houses) {
            distance = house == EMPTY_SPOT ? 0 : ++distance;
            distances.add(distance);
        }
    }

    private static void goLeft(List<Integer> houses, List<Integer> distances) {
        int distance = houses.size();
        for (int i = houses.size() - 1; i >= 0; i--) {
            distance = houses.get(i) == EMPTY_SPOT ? 0 : ++distance;
            int minDistance = Math.min(distances.get(i), distance);
            distances.set(i, minDistance);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int streetLength = Integer.parseInt(reader.readLine());

            List<Integer> houses = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            List<Integer> distances = calculateDistances(houses);
            for (int distance : distances) {
                writer.write(distance + " ");
            }
        }
    }
}
