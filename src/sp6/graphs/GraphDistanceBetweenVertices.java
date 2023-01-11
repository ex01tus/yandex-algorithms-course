package sp6.graphs;

import java.io.*;
import java.util.*;

/**
 * Найдите кратчайшее расстояние между парой вершин в неориентированном графе. Граф может быть несвязным.
 *
 * В первой строке дано количество вершин n (1 ≤ n ≤ 10^5) и рёбер m (1 ≤ m ≤ 10^5).
 * Далее в m строках описаны рёбра графа. Каждое ребро описывается номерами двух вершин u и v (1 ≤ u, v ≤ n).
 * В последней строке дан номер стартовой вершины s (1 ≤ s ≤ n) и конечной t (1 ≤ t ≤ n).
 * Гарантируется, что в графе нет петель и кратных рёбер.
 *
 * Выведите длину кратчайшего пути (в рёбрах) между заданной парой вершин. Если пути не существует, то выведите -1.
 */
public class GraphDistanceBetweenVertices {

    private static int[][] adjMatrix;
    private static int[] color; // 0 - white, 1 - grey, 2 - black
    private static int[] distance;

    private static void bfs(int start) {
        Queue<Integer> planned = new LinkedList<>();

        planned.offer(start);
        color[start] = 1;
        distance[start] = 0;

        while (!planned.isEmpty()) {
            int vertex = planned.poll();

            int[] vertices = adjMatrix[vertex];
            for (int v = 0; v < vertices.length; v++) {
                if (vertices[v] == 1 && color[v] == 0) {
                    color[v] = 1;
                    distance[v] = distance[vertex] + 1;
                    planned.offer(v);
                }
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
            String[] startAndEnd = reader.readLine().split(" ");
            int start = Integer.parseInt(startAndEnd[0]) - 1;
            int end = Integer.parseInt(startAndEnd[1]) - 1;

            adjMatrix = toAdjacencyMatrix(edges, verticesNumber);
            color = new int[verticesNumber];
            distance = new int[verticesNumber];
            Arrays.fill(distance, -1);

            bfs(start);

            System.out.println(distance[end]);
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
