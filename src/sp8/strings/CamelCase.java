package sp8.strings;

import java.io.*;
import java.util.*;

/**
 * В некоторых IDE поддерживается навигация по файлам через их сокращённые названия. Если в языке принято называть
 * классы CamelCase'ом (как в Java, например), то по заглавным буквам названия можно быстро найти нужный класс.
 * Например, если название класса «MyFavouriteConfigurableScannerFactory», то его можно найти по строке «MFCSF».
 * Но если в проекте есть класс «theMultiFunctionalCommaSeparatedFile», то он тоже будет подходить под этот паттерн,
 * и при поиске надо будет выбрать между этими двумя вариантами.
 * Вам дан набор строк в CamelCase. Далее будут поступать запросы в виде строк-паттернов из прописных букв
 * английского алфавита. Вам надо находить такие строки среди исходных, которые удовлетворяют заданному шаблону,
 * и выводить их в лексикографическом порядке.
 * Также в паттерне может быть только несколько первых заглавных букв. Например, если бы в указанном выше примере был
 * бы паттерн «MFCS», то существующие две строки походили бы под него, а также подходил бы, например,
 * «MamaFicusCodingSouthWestNorth». А вот «MamaCodingSouthWestNorth» –— уже нет.
 *
 * В первой строке записано число — количество названий классов в исходном наборе n (1 ≤ n ≤ 10^5).
 * Все названия состоят из строчных и прописных букв английского алфавита.
 * В следующих n строках даны сами названия по одному в строке. Суммарная длина этих строк не превосходит 10^7.
 * Затем дано количество запросов m (1 ≤ m ≤ 100).
 * В следующих *m* строках даны сами запросы. Каждый запрос –— это шаблон, строка из прописных букв английского алфавита,
 * в длину не превышающая 10^5. Шаблон может быть пустым. Заметьте: шаблону из нуля прописных букв удовлетворяет любое название.
 *
 * Для каждого отдельного запроса (в порядке их поступления) выведите в лексикографическом порядке все строки,
 * которые подходят под данный шаблон. Если какие-то строки одинаковые, то выведите все экземпляры.
 * Если ни одна из строк не подходит под шаблон, то выведите для данного запроса пустую строку.
 */
public class CamelCase {

    private static List<String> search(List<String> classes, List<String> requests) {
        Node trie = constructTrie(classes);

        List<String> allResults = new ArrayList<>();
        for (String request : requests) {
            List<String> results = searchTrie(trie, request);
            Collections.sort(results);
            allResults.addAll(results);
        }

        return allResults;
    }

    private static Node constructTrie(List<String> classes) {
        Node root = new Node();

        for (String clazz : classes) {
            Node current = root;
            for (char c : clazz.toCharArray()) {
                if (Character.isLowerCase(c)) continue;

                Node child = current.children.computeIfAbsent(c, __ -> new Node());
                current = child;
            }

            current.fullWords.add(clazz);
        }

        return root;
    }

    private static List<String> searchTrie(Node trie, String request) {
        Node current = trie;
        for (char c : request.toCharArray()) {
            if (Character.isLowerCase(c)) continue;

            Node next = current.children.get(c);
            if (next == null) return new ArrayList<>(List.of(""));


            current = next;
        }

        List<String> results = new ArrayList<>();
        backtrack(current, results);

        return results;
    }

    private static void backtrack(Node current, List<String> results) {
        if (current == null) return;

        results.addAll(current.fullWords);
        for (Node child : current.children.values()) {
            backtrack(child, results);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            List<String> classes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                classes.add(reader.readLine());
            }

            int m = Integer.parseInt(reader.readLine());
            List<String> requests = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                requests.add(reader.readLine());
            }

            List<String> results = search(classes, requests);
            for (String result : results) {
                writer.write(result);
                writer.newLine();
            }
        }
    }

    private static class Node {

        public final Map<Character, Node> children;
        public final List<String> fullWords;

        public Node() {
            this.children = new HashMap<>();
            this.fullWords = new ArrayList<>();
        }
    }
}
