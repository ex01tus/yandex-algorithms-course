package sp6.graphs;

import java.io.*;
import java.util.*;

import static java.util.Collections.emptyList;

/**
 * Вам дан ориентированный граф. Известно, что все его вершины достижимы из вершины s=1.
 * Найдите время входа и выхода при обходе в глубину, производя первый запуск из вершины s.
 * Считайте, что время входа в стартовую вершину равно 0.
 * Соседей каждой вершины обходите в порядке увеличения номеров.
 *
 * В первой строке дано число вершин n (1 ≤ n ≤ 2 ⋅ 10^5) и рёбер (0 ≤ m ≤ 2 ⋅ 10^5).
 * В каждой из следующих m строк записаны рёбра графа в виде пар (from, to),
 * 1 ≤ from ≤ n — начало ребра, 1 ≤ to ≤ n — его конец.
 * Гарантируется, что в графе нет петель и кратных рёбер.
 *
 * Выведите n строк, в каждой из которых записана пара чисел tini, touti — время входа и выхода для вершины i.
 */
public class GraphEntryAndLeaveTime {

    private static Map<Integer, List<Integer>> adjList;
    private static int[] color; // 0 - white, 1 - grey, 2 - black
    private static int[] entryTime;
    private static int[] leaveTime;

    private static void dfs(int start) {
        int counter = -1;

        Deque<Integer> stack = new LinkedList<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Integer vertex = stack.pop();

            if (color[vertex] == 0) {
                counter++;
                entryTime[vertex] = counter;
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
                counter++;
                leaveTime[vertex] = counter;
                color[vertex] = 2;
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
            int start = 0;

            adjList = toAdjacencyList(edges);
            color = new int[verticesNumber];
            entryTime = new int[verticesNumber];
            leaveTime = new int[verticesNumber];

            dfs(start);
            for (int i = 0; i < verticesNumber; i++) {
                writer.write(entryTime[i] + " " + leaveTime[i]);
                writer.newLine();
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
