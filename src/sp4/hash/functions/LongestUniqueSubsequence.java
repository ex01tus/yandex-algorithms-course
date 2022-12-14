package sp4.hash.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * На вход подается строка. Нужно определить длину наибольшей подстроки, которая не содержит повторяющиеся символы.
 *
 * Одна строка, состоящая из строчных латинских букв. Длина строки не превосходит 10 000.
 *
 * Выведите натуральное число —– ответ на задачу.
 */
public class LongestUniqueSubsequence {

    private static int longestUniqueSubsequence(String input) {
        if (input.length() <= 1) return input.length();

        int left = 0;
        int right = 1;
        int max = 1;

        Map<Character, Integer> previous = new HashMap<>();
        previous.put(input.charAt(left), 0);

        while (right < input.length()) {
            char current = input.charAt(right);
            if (previous.containsKey(current)) {
                int idx = previous.remove(current);
                left = Math.max(left, idx + 1);
            }

            max = Math.max(max, right - left + 1);

            previous.put(current, right);
            right++;
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            int max = longestUniqueSubsequence(input);
            System.out.println(max);
        }
    }
}
