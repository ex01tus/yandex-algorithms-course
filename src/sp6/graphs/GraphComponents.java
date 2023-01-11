package sp6.graphs;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.joining;

/**
 * Вам дан неориентированный граф. Найдите его компоненты связности.
 *
 * В первой строке дано количество вершин n (1≤ n ≤ 10^5) и рёбер m (0 ≤ m ≤ 2 ⋅ 10^5).
 * В каждой из следующих m строк записано по ребру в виде пары вершин 1 ≤ u, v ≤ n.
 * Гарантируется, что в графе нет петель и кратных рёбер.
 *
 * Выведите все компоненты связности в следующем формате: в первой строке выведите общее количество компонент.
 * Затем на отдельных строках выведите вершины каждой компоненты, отсортированные по возрастанию номеров.
 * Компоненты между собой упорядочивайте по номеру первой вершины.
 */
public class GraphComponents {

    private static int[][] adjMatrix;
    private static int[] color; // 0 - white, 1 - grey, 2+ - component colors
    private static int currentColor = 2;

    private static void search(int start) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (color[vertex] == 0) {
                color[vertex] = 1;
                stack.push(vertex);

                int[] vertices = adjMatrix[vertex];
                for (int v = 0; v < vertices.length; v++) {
                    if (vertices[v] == 1 && color[v] == 0) {
                        stack.push(v);
                    }
                }
            } else if (color[vertex] == 1) {
                color[vertex] = currentColor;
            }
        }

        currentColor++;
    }

    private static void mainSearch(int verticesNumber) {
        for (int i = 0; i < verticesNumber; i++) {
            if (color[i] == 0) {
                search(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] sizes = reader.readLine().split(" ");
            int verticesNumber = Integer.parseInt(sizes[0]);
            int edgesNumber = Integer.parseInt(sizes[1]);
            List<Edge> edges = readEdges(reader, edgesNumber);

            adjMatrix = toAdjacencyMatrix(edges, verticesNumber);
            color = new int[verticesNumber];

            mainSearch(verticesNumber);
            formatOutput(writer);
        }
    }

    private static List<Edge> readEdges(BufferedReader reader, int edgesNumber) throws IOException {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < edgesNumber; i++) {
            String[] edge = reader.readLine().split(" ");
            edges.add(new Edge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1])));
        }

        return edges;
    }

    private static int[][] toAdjacencyMatrix(List<Edge> edges, int verticesNumber) {
        int[][] result = new int[verticesNumber][verticesNumber];

        for (Edge edge : edges) {
            result[edge.from - 1][edge.to - 1] = 1;
            result[edge.to - 1][edge.from - 1] = 1;
        }

        return result;
    }

    private static void formatOutput(BufferedWriter writer) throws IOException {
        List<List<Integer>> components = new ArrayList<>();
        for (int i = 0; i < color.length; i++) {
            int idx = color[i] - 2;
            if (idx >= components.size()) {
                components.add(new ArrayList<>());
            }

            components.get(idx).add(i + 1);
        }

        for (List<Integer> c : components) {
            Collections.sort(c);
        }

        components.sort(Comparator.comparing(e -> e.get(0)));

        System.out.println(components.size());
        for (List<Integer> c : components) {
            writer.write(c.stream()
                    .map(String::valueOf)
                    .collect(joining(" ")));
            writer.newLine();
        }
    }

    private static class Edge {

        private final int from;
        private final int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
