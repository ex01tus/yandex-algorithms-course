package sp6.graphs;

import java.io.*;
import java.util.*;

/**
 * Под расстоянием между двумя вершинами в графе будем понимать длину кратчайшего пути между ними в рёбрах.
 * Для данной вершины s определите максимальное расстояние от неё до другой вершины неориентированного графа.
 *
 * В первой строке дано количество вершин n (1 ≤ n ≤ 10^5) и рёбер m (0 ≤ m ≤ 10^5).
 * Далее в m строках описаны рёбра графа. Каждое ребро описывается номерами двух вершин u и v (1 ≤ u, v ≤ n).
 * В последней строке дан номер вершины s (1 ≤ s ≤ n). Гарантируется, что граф связный и что в нём нет петель и кратных рёбер.
 *
 * Выведите длину наибольшего пути от s до одной из вершин графа.
 */
public class GraphMaxDistance {

    private static int[][] adjMatrix;
    private static int[] color; // 0 - white, 1 - grey, 2 - black
    private static int[] distance;

    private static void bfs(int start) {
        Queue<Integer> planned = new LinkedList<>();

        color[start] = 1;
        planned.add(start);
        distance[start] = 0;

        while (!planned.isEmpty()) {
            int vertex = planned.poll();

            int[] vertices = adjMatrix[vertex];
            for (int v = 0; v < vertices.length; v++) {
                if (vertices[v] == 1 && color[v] == 0) {
                    distance[v] = distance[vertex] + 1;
                    color[v] = 1;
                    planned.add(v);
                }
            }

            color[vertex] = 2;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] sizes = reader.readLine().split(" ");
            int verticesNumber = Integer.parseInt(sizes[0]);
            int edgesNumber = Integer.parseInt(sizes[1]);
            List<Edge> edges = readEdges(reader, edgesNumber);
            int start = Integer.parseInt(reader.readLine()) - 1;

            adjMatrix = toAdjacencyMatrix(edges, verticesNumber);
            color = new int[verticesNumber];
            distance = new int[verticesNumber];
            Arrays.fill(distance, -1);

            bfs(start);

            int max = -1;
            for (int d : distance) {
                max = Math.max(max, d);
            }

            System.out.println(max);
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
