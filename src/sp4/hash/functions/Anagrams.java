package sp4.hash.functions;

import java.io.*;
import java.util.*;

/**
 * Вася решил избавиться от проблем с произношением и стать певцом. Он обратился за помощью к логопеду.
 * Тот посоветовал Васе выполнять упражнение, которое называется анаграммная группировка.
 * В качестве подготовительного этапа нужно выбрать из множества строк анаграммы.
 * Анаграммы –— это строки, которые получаются друг из друга перестановкой символов.
 * Например, строки «SILENT» и «LISTEN» являются анаграммами.
 * Помогите Васе найти анаграммы.
 *
 * В первой строке записано число n —– количество строк.
 * Далее в строку через пробел записаны n строк.
 * n не превосходит 6000. Длина каждой строки не более 100 символов.
 *
 * Нужно вывести в отсортированном порядке индексы строк, которые являются анаграммами.
 * Каждая группа индексов должна быть выведена в отдельной строке. Индексы внутри одной группы должны быть отсортированы
 * по возрастанию. Группы между собой должны быть отсортированы по возрастанию первого индекса.
 * Обратите внимание, что группа анаграмм может состоять и из одной строки. Например, если в исходном наборе нет анаграмм,
 * то надо вывести n групп, каждая из которых состоит из одного индекса.
 */
public class Anagrams {

    private static Map<String, List<Integer>> find(List<String> strings) {
        Map<String, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < strings.size(); i++) {
            String current = strings.get(i);
            String hash = hash(current);

            List<Integer> group = groups.get(hash);
            if (group == null) {
                List<Integer> newGroup = new ArrayList<>();
                newGroup.add(i);
                groups.put(hash, newGroup);
            } else {
                group.add(i);
            }
        }

        return groups;
    }

    private static String hash(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return Arrays.toString(chars);
    }

    private static void print(Map<String, List<Integer>> groups) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            List<List<Integer>> groupsList = new ArrayList<>(groups.values());
            Collections.sort(groupsList, Comparator.comparing(a -> a.get(0)));

            for (List<Integer> group : groupsList) {
                Collections.sort(group);
                for (int idx : group) {
                    writer.write(idx + " ");
                }

                writer.newLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            if (n == 0) return;

            List<String> strings = List.of(reader.readLine().split(" "));
            Map<String, List<Integer>> groups = find(strings);
            print(groups);
        }
    }
}
