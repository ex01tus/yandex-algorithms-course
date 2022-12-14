package sp4.hash.functions;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Дана длинная строка, состоящая из маленьких латинских букв.
 * Нужно найти все её подстроки длины n, которые встречаются хотя бы k раз.
 *
 * В первой строчке через пробел записаны два натуральных числа n и k.
 * Во второй строчке записана строка, состоящая из маленьких латинских букв. Длина строки 1 ≤ L ≤ 10^6.
 * n ≤ L, k ≤ L.
 *
 * Для каждой найденной подстроки выведите индекс начала её первого вхождения (нумерация в строке начинается с нуля).
 * Выводите индексы в любом порядке, в одну строку, через пробел.
 */
public class SubstringsNumber {

    private static class Tuple{
        public int idx;
        public int count;
    }

    private static List<Integer> find(String input, int window, int times) {
        if (input.length() < window) return List.of();
        if (input.length() == window && times == 1) return List.of(1);

        Map<String, Tuple> map = new HashMap<>();
        for (int i = window; i < input.length(); i++) {
            int start = i - window;
            String substring = input.substring(start, i);

            Tuple tuple = map.get(substring);
            if (tuple == null) {
                Tuple newTuple = new Tuple();
                newTuple.idx = start;
                newTuple.count = 1;

                map.put(substring, newTuple);
            } else {
                tuple.count++;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (Tuple tuple : map.values()) {
            if (tuple.count >= times) {
                result.add(tuple.idx);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] s = reader.readLine().split(" ");
            int window = Integer.parseInt(s[0]);
            int times = Integer.parseInt(s[1]);
            String input = reader.readLine();

            List<Integer> starts = find(input, window, times);
            for (int start : starts) {
                writer.write(start + " ");
            }
        }
    }
}
