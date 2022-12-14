package sp1.intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Дана матрица. Нужно написать функцию, которая для элемента возвращает всех его соседей.
 * Соседним считается элемент, находящийся от текущего на одну ячейку влево, вправо, вверх или вниз.
 * Диагональные элементы соседними не считаются.
 *
 * В первой строке задано n — количество строк матрицы. Во второй — количество столбцов m.
 * Числа m и n не превосходят 1000. В следующих n строках задана матрица. Элементы матрицы — целые числа,
 * по модулю не превосходящие 1000. В последних двух строках записаны координаты элемента (индексация начинается с нуля),
 * соседей которого нужно найти.
 *
 * Напечатайте нужные числа в возрастающем порядке через пробел.
 */
public class MatrixNeighbours {

    private static List<Integer> getNeighbours(List<List<Integer>> matrix, int n, int m, int row, int col) {
        List<Integer> neighbours = new ArrayList<>(4);

        if (row > 0) neighbours.add(matrix.get(row - 1).get(col));
        if (row < n - 1) neighbours.add(matrix.get(row + 1).get(col));
        if (col > 0) neighbours.add(matrix.get(row).get(col - 1));
        if (col < m - 1) neighbours.add(matrix.get(row).get(col + 1));

        Collections.sort(neighbours);
        return neighbours;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            int m = readInt(reader);
            List<List<Integer>> matrix = readMatrix(reader, n);
            int row = readInt(reader);
            int col = readInt(reader);
            List<Integer> neighbours = getNeighbours(matrix, n, m, row, col);
            for (int element : neighbours) {
                System.out.print(element + " ");
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<List<Integer>> readMatrix(BufferedReader reader, int rowsCount) throws IOException {
        List<List<Integer>> matrix = new ArrayList<>(rowsCount);
        for (int i = 0; i < rowsCount; i++) {
            matrix.add(readList(reader));
        }
        return matrix;
    }
}