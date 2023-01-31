package sp8.strings;

import java.io.*;
import java.util.*;

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
