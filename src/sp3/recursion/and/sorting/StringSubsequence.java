package sp3.recursion.and.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Гоша любит играть в игру «Подпоследовательность»: даны 2 строки, и нужно понять,
 * является ли первая из них подпоследовательностью второй. Когда строки достаточно длинные,
 * очень трудно получить ответ на этот вопрос, просто посмотрев на них.
 * Помогите Гоше написать функцию, которая решает эту задачу.
 *
 * В первой строке записана строка s.
 * Во второй —- строка t.
 * Обе строки состоят из маленьких латинских букв, длины строк не превосходят 150000. Строки могут быть пустыми.
 *
 * Выведите True, если s является подпоследовательностью t, иначе —– False.
 */
public class StringSubsequence {

    private static boolean isSubsequence(String first, String second) {
        if (first.isEmpty()) return true;

        int counter = 0;
        int j = 0;

        for (int i = 0; i < second.length(); i++) {
            char current = second.charAt(i);
            if (current == first.charAt(j)) {
                counter++;
                j++;
            }

            if (j >= first.length()) break;
        }

        return counter == first.length();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String first = reader.readLine();
            String second = reader.readLine();

            System.out.println(isSubsequence(first, second) ? "True" : "False");
        }
    }
}
