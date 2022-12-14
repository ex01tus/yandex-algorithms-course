package sp3.recursion.and.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MaxTrianglePerimeter {

    private static int max(List<Integer> input) {
        Collections.sort(input, (a, b) -> b.compareTo(a));

        for (int c = 0; c < input.size() - 2; c++) {
            if (input.get(c) < input.get(c + 1) + input.get(c + 2)) {
                return input.get(c) + input.get(c + 1) + input.get(c + 2);
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> input = readList(reader);

            int result = max(input);
            System.out.println(result);
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
}
