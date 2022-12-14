package sp1.intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Метеорологическая служба вашего города решила исследовать погоду новым способом.
 * Под температурой воздуха в конкретный день будем понимать максимальную температуру в этот день.
 * Назовём хаотичностью погоды за n дней количество дней, в которые температура строго больше,
 * чем в день до (если такой существует) и в день после текущего (если такой существует).
 * Определите по ежедневным показаниям температуры хаотичность погоды за этот период.
 * Заметим, что если число показаний n=1, то единственный день будет хаотичным.
 *
 * В первой строке дано число n –— длина периода измерений в днях, 1 ≤ n≤ 105.
 * Во второй строке даны n целых чисел –— значения температуры в каждый из n дней.
 * Значения температуры не превосходят 273 по модулю.
 *
 * Выведите единственное число — хаотичность за данный период.
 */
public class WeatherRandomness {

    private static int getWeatherRandomness(List<Integer> w, int n) {
        if (n == 1) return 1;

        int chaos = 0;
        if (w.get(0) > w.get(1)) chaos++;
        if (w.get(n - 1) > w.get(n - 2)) chaos++;

        for (int i = 1; i < n - 1; i++) {
            if (w.get(i) > w.get(i - 1) && w.get(i) > w.get(i + 1)) chaos++;
        }

        return chaos;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> temperatures = readList(reader);
            int chaosNumber = getWeatherRandomness(temperatures, n);
            System.out.println(chaosNumber);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
