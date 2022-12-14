package sp2.basic.data.structures;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Алла получила задание, связанное с мониторингом работы различных серверов.
 * Требуется понять, сколько времени обрабатываются определённые запросы на конкретных серверах.
 * Эту информацию нужно хранить в матрице, где номер столбца соответствуют идентификатору запроса,
 * а номер строки — идентификатору сервера. Алла перепутала строки и столбцы местами. С каждым бывает.
 * Помогите ей исправить баг.
 * Есть матрица размера m × n. Нужно написать функцию, которая её транспонирует.
 * Транспонированная матрица получается из исходной заменой строк на столбцы.
 * Например, для матрицы А (слева) транспонированной будет следующая матрица (справа):
 *
 *
 * В первой строке задано число n — количество строк матрицы.
 * Во второй строке задано m — число столбцов, m и n не превосходят 1000.
 * В следующих n строках задана матрица. Числа в ней не превосходят по модулю 1000.
 *
 * Напечатайте транспонированную матрицу в том же формате, который задан во входных данных.
 * Каждая строка матрицы выводится на отдельной строке, элементы разделяются пробелами.
 */
public class MatrixTransposition {

    private static List<List<Integer>> transpose(List<List<Integer>> matrix) {
        int rows = matrix.size();
        int columns = matrix.get(0).size();

        List<List<Integer>> result = new ArrayList<>();
        for (int col = 0; col < columns; col++) {
            result.add(new ArrayList<>());
            for (int row = 0; row < rows; row++) {
                int value = matrix.get(row).get(col);
                result.get(col).add(value);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            int m = readInt(reader);
            List<List<Integer>> matrix = readMatrix(reader, n);
            List<List<Integer>> result = transpose(matrix);

            for (int row = 0; row < result.size(); row++) {
                for (int col = 0; col < result.get(0).size(); col++) {
                    int value = result.get(row).get(col);
                    writer.write(value + " ");
                }
                writer.write("\n");
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
