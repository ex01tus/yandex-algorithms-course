package sp6.graphs;

import java.io.*;
import java.util.*;

import static java.util.Collections.emptyList;

/**
 * Дан ациклический ориентированный граф (так называемый DAG, directed acyclic graph).
 * Найдите его топологическую сортировку, то есть выведите его вершины в таком порядке,
 * что все рёбра графа идут слева направо. У графа может быть несколько подходящих перестановок вершин.
 * Вам надо найти любую топологическую сортировку.
 *
 * В первой строке даны два числа – количество вершин n (1 ≤ n ≤ 10^5) и количество рёбер m (0 ≤ m ≤ 10^5).
 * В каждой из следующих m строк описаны рёбра по одному на строке.
 * Каждое ребро представлено парой вершин (from, to), 1≤ from, to ≤ n, соответственно номерами вершин начала и конца.
 *
 * Выведите номера вершин в требуемом порядке.
 */
public class GraphTopologicalSort {

    private static Map<Integer, List<Integer>> adjList;
    private static int[] color; // 0 - white, 1 - grey, 2 - black
    private static Deque<Integer> sortedVertices;

    private static void sort(int start) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (color[vertex] == 0) {
                color[vertex] = 1;
                stack.push(vertex);

                List<Integer> vertices = Optional.ofNullable(adjList.get(vertex)).orElse(emptyList());
                vertices.sort(Comparator.reverseOrder());

                for (int v : vertices) {
                    if (color[v] == 0) {
                        stack.push(v);
                    }
                }
            } else if (color[vertex] == 1) {
                color[vertex] = 2;
                sortedVertices.push(vertex);
            }
        }
    }

    private static void mainSort(int verticesNumber) {
        for (int i = 0; i < verticesNumber; i++) {
            if (color[i] == 0) {
                sort(i);
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

            adjList = toAdjacencyList(edges);
            color = new int[verticesNumber];
            sortedVertices = new LinkedList<>();

            mainSort(verticesNumber);

            while (!sortedVertices.isEmpty()) {
                writer.write((sortedVertices.pop() + 1) + " ");
            }
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

    private static Map<Integer, List<Integer>> toAdjacencyList(List<Edge> edges) {
        Map<Integer, List<Integer>> result = new HashMap<>();

        for (Edge edge : edges) {
            List<Integer> vertices = result.get(edge.from - 1);

            if (vertices == null) {
                vertices = new ArrayList<>();
            }

            vertices.add(edge.to - 1);
            result.put(edge.from - 1, vertices);
        }

        return result;
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
