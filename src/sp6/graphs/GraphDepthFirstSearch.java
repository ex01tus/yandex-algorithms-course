package sp6.graphs;

import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * Задан неориентированный граф. Обойдите с помощью DFS все вершины, достижимые из заданной вершины s,
 * и выведите их в порядке обхода, если начинать обход из s.
 *
 * В первой строке дано количество вершин n (1 ≤ n ≤ 10^5) и рёбер m (0 ≤ m ≤ 10^5).
 * Далее в m строках описаны рёбра графа. Каждое ребро описывается номерами двух вершин u и v (1 ≤ u, v ≤ n).
 * В последней строке дан номер стартовой вершины s (1 ≤ s ≤ n). В графе нет петель и кратных рёбер.
 *
 * Выведите вершины в порядке обхода, считая что при запуске от каждой конкретной вершины её соседи будут рассматриваться
 * в порядке возрастания (то есть если вершина 2 соединена с 1 и 3, то сначала обход пойдёт в 1, а уже потом в 3).
 */
public class GraphDepthFirstSearch {

    private static int[][] adjMatrix;
    private static int[] color; // 0 - white, 1 - grey, 2 - black

    private static List<Integer> dfs(int start) {
        List<Integer> result = new ArrayList<>();

        Deque<Integer> stack = new LinkedList<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (color[vertex - 1] == 0) {
                // white vertex -> recolor to gray & push all adjacent
                result.add(vertex);

                color[vertex - 1] = 1;
                stack.push(vertex);

                int[] vertices = adjMatrix[vertex - 1];
                for (int i = vertices.length - 1; i >= 0; i--) {
                    if (vertices[i] == 1) {
                        if (color[i] == 0) {
                            stack.push(i + 1);
                        }
                    }
                }
            } else if (color[vertex - 1] == 1) {
                // gray vertex -> recolor to black
                color[vertex - 1] = 2;
            } else {
                // black vertex -> do nothing
            }
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
            int start = Integer.parseInt(reader.readLine());

            adjMatrix = toAdjacencyMatrix(edges, verticesNumber);
            color = new int[verticesNumber];

            List<Integer> dfs = dfs(start);
            writer.write(dfs.stream()
                    .map(String::valueOf)
                    .collect(joining(" ")));
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

    private static class Edge {

        private final int from;
        private final int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
