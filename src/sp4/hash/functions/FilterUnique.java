package sp4.hash.functions;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * В компании, где работает Тимофей, заботятся о досуге сотрудников и устраивают различные кружки по интересам.
 * Когда кто-то записывается на занятие, в лог вносится название кружка.
 * По записям в логе составьте список всех кружков, в которые ходит хотя бы один человек.
 *
 * В первой строке даётся натуральное число n, не превосходящее 10 000 –— количество записей в логе.
 * В следующих n строках —– названия кружков.
 *
 * Выведите уникальные названия кружков по одному на строке, в порядке появления во входных данных.
 */
public class FilterUnique {

    private static void printUnique(List<String> groups) throws IOException {
        Set<String> previous = new HashSet<>();

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (String group : groups) {
                if (previous.contains(group)) continue;
                previous.add(group);
                writer.write(group);
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<String> groups = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                String group = reader.readLine();
                groups.add(group);
            }

            printUnique(groups);
        }
    }
}
