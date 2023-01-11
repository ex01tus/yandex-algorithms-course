package sp6.graphs;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * Алла пошла на стажировку в студию графического дизайна, где ей дали такое задание: для очень большого числа
 * ориентированных графов преобразовать их список рёбер в список смежности. Чтобы побыстрее решить эту задачу,
 * она решила автоматизировать процесс.
 * Помогите Алле написать программу, которая по списку рёбер графа будет строить его список смежности.
 *
 * В первой строке дано число вершин n (1 ≤ n ≤ 100) и число ребер m (1 ≤ m ≤ n(n-1)).
 * В следующих m строках заданы ребра в виде пар вершин (u,v), если ребро ведет от u к v.
 *
 * Выведите информацию о рёбрах, исходящих из каждой вершины.
 * В строке i надо написать число рёбер, исходящих из вершины i, а затем перечислить вершины,
 * в которые ведут эти рёбра –— в порядке возрастания их номеров.
 */
public class GraphAdjacencyList {

    private static Map<Integer, List<Integer>> toAdjacencyList(List<Edge> edges) {
        Map<Integer, List<Integer>> result = new HashMap<>();

        for (Edge edge : edges) {
            List<Integer> vertices = result.get(edge.from);

            if (vertices == null) {
                vertices = new ArrayList<>();
            }

            vertices.add(edge.to);
            result.put(edge.from, vertices);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] sizes = reader.readLine().split(" ");
            int verticesNumber = Integer.parseInt(sizes[0]);
            int edgesNumber = Integer.parseInt(sizes[1]);
            List<Edge> edges = readEdges(reader, edgesNumber);

            Map<Integer, List<Integer>> result = toAdjacencyList(edges);
            printAdjacencyList(writer, verticesNumber, result);
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

    private static void printAdjacencyList(
            BufferedWriter writer,
            int verticesNumber,
            Map<Integer, List<Integer>> result) throws IOException {
        for (int i = 1; i <= verticesNumber; i++) {
            List<Integer> vertices = result.get(i);
            if (vertices == null) {
                writer.write("0");
                writer.newLine();
            } else {
                writer.write(vertices.size() + " " + vertices.stream()
                        .map(String::valueOf)
                        .collect(joining(" ")));
                writer.newLine();
            }
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
