package sp6.graphs;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * Алла успешно справилась с предыдущим заданием, и теперь ей дали новое. На этот раз список рёбер ориентированного
 * графа надо переводить в матрицу смежности. Конечно же, Алла попросила вас помочь написать программу для этого.
 *
 * В первой строке дано число вершин n (1 ≤ n ≤ 100) и число рёбер m (1 ≤ m ≤ n(n-1)).
 * В следующих m строках заданы ребра в виде пар вершин (u,v), если ребро ведет от u к v.
 *
 * Выведите матрицу смежности n на n. На пересечении i-й строки и j-го столбца стоит единица,
 * если есть ребро, ведущее из i в j.
 */
public class GraphAdjacencyMatrix {

    private static int[][] toAdjacencyMatrix(List<Edge> edges, int verticesNumber) {
        int[][] result = new int[verticesNumber][verticesNumber];

        for (Edge edge : edges) {
            result[edge.from - 1][edge.to - 1] = 1;
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

            int[][] result = toAdjacencyMatrix(edges, verticesNumber);
            printAdjacencyMatrix(writer, result);
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

    private static void printAdjacencyMatrix(BufferedWriter writer, int[][] result) throws IOException {
        for (int[] line : result) {
            writer.write(Arrays.stream(line)
                    .mapToObj(String::valueOf)
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
