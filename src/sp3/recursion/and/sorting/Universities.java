package sp3.recursion.and.sorting;

import java.io.*;
import java.util.*;

/**
 * На IT-конференции присутствовали студенты из разных вузов со всей страны.
 * Для каждого студента известен ID университета, в котором он учится.
 * Тимофей предложил Рите выяснить, из каких k вузов на конференцию пришло больше всего учащихся.
 *
 * В первой строке дано количество студентов в списке —– n (1 ≤ n ≤ 15 000).
 * Во второй строке через пробел записаны n целых чисел —– ID вуза каждого студента.
 * Каждое из чисел находится в диапазоне от 0 до 10 000.
 * В третьей строке записано одно число k.
 *
 * Выведите через пробел k ID вузов с максимальным числом участников.
 * Они должны быть отсортированы по убыванию популярности (по количеству гостей от конкретного вуза).
 * Если более одного вуза имеет одно и то же количество учащихся, то выводить их ID нужно в порядке возрастания.
 */
public class Universities {

    public static class University {

        public int id;
        public int students;

        public University(int id, int students) {
            this.id = id;
            this.students = students;
        }
    }

    private static List<Integer> max(int[] input, int k) {
        University[] universities = new University[10_000];

        for (int i = 0; i < 10_000; i++) {
            universities[i] = new University(i, 0);
        }

        for (int id : input) {
            University university = universities[id];
            university.students += 1;
        }

        Comparator<University> comparator = (a, b) -> Integer.compare(b.students, a.students);
        comparator.thenComparing((a, b) -> Integer.compare(a.id, b.id));
        Arrays.sort(universities, comparator);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(universities[i].id);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            int[] input = new int[n];

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                input[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            int k = readInt(reader);

            List<Integer> result = max(input, k);
            for (int id : result) {
                writer.write(id + " ");
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
