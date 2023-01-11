package sp4.hash.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Гоша едет в гости к друзьям. Ему придётся сначала ехать на метро, а потом пересаживаться на автобус.
 * Гоша не любит долго ждать, поэтому хочет выбрать такую станцию метро, рядом с которой расположено как можно больше
 * остановок автобуса. Гоша считает, что остановка находится рядом с метро, если расстояние между ними не превосходит 20 метров.
 * Гоше известны все координаты автобусных остановок и координаты выходов из метро.
 * Помогите ему найти выход из метро, рядом с которым расположено больше всего остановок.
 *
 * В первой строке дано количество выходов из метро –— натуральное число n (1 ≤ n ≤ 10^4).
 * В следующих n строках даны координаты выходов из метро. Каждый выход описывается двумя координатами x и y, записанными через пробел.
 * В следующей строке дано количество автобусных остановок —– натуральное число m (1 ≤ m ≤ 10^6).
 * В следующих m строках заданы координаты остановок. Каждая остановка описывается двумя числами —– своими x и y координатами, записанными через пробел.
 * Все координаты —– целые числа, не превосходящие 10^9 по модулю. Единицы измерения —– метры.
 *
 * Выведите единственное число –— номер того выхода из метро, рядом с которым расположено больше всего остановок.
 * Номер выхода –— его порядковый номер во входных данных (нумерация с единицы).
 * Если подходящих выходов из метро несколько, выведите тот, который встречается раньше во входных данных.
 */
public class Distances {

    private static final double MAX_DISTANCE = 20.0;

    private static class Coordinates {
        public final int x;
        public final int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int findOptimalStation(
            Coordinates[] metroStations,
            Coordinates[] busStops) {
        int[] counter = new int[metroStations.length];

        int max = 0;
        int result = 0;
        for (int i = 0; i < metroStations.length; i++) {
            Coordinates metroStation = metroStations[i];
            for (Coordinates busStop : busStops) {
                double distance = computeDistance(metroStation, busStop);

                if (distance <= MAX_DISTANCE) {
                    counter[i] += 1;
                }
            }

            if (counter[i] > max) {
                max = counter[i];
                result = i;
            }
        }

        return result;
    }

    private static double computeDistance(Coordinates metroStation, Coordinates busStop) {
        return Math.sqrt(
                Math.pow(busStop.x - metroStation.x, 2)
                        + Math.pow(busStop.y - metroStation.y, 2));
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            Coordinates[] metroStations = new Coordinates[n];
            for (int i = 0; i < n; i++) {
                String[] s = reader.readLine().split(" ");
                metroStations[i] = new Coordinates(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            }

            int m = Integer.parseInt(reader.readLine());
            Coordinates[] busStops = new Coordinates[m];
            for (int i = 0; i < m; i++) {
                String[] s = reader.readLine().split(" ");
                busStops[i] = new Coordinates(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            }

            int idx = findOptimalStation(metroStations, busStops);
            System.out.println(idx + 1); // stations are numbered from 1
        }
    }
}
