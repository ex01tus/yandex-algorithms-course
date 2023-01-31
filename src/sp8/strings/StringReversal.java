package sp8.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * В некоторых языках предложения пишутся и читаются не слева направо, а справа налево.
 * Вам под руку попался странный текст –— в нём обычный (слева направо) порядок букв в словах.
 * А вот сами слова идут в противоположном направлении. Вам надо преобразовать текст так,
 * чтобы слова в нём были написаны слева направо.
 *
 * На ввод подаётся строка, состоящая из слов, разделённых пробелами (один пробел между соседними словами).
 * Всего слов не более 1000, длина каждого из них —– от 1 до 100 символов.
 * Слова состоят из строчных букв английского алфавита.
 *
 * Выведите строку с обратным порядком слов в ней.
 */
public class StringReversal {

    private static String reverse(String line) {
        String[] tokens = line.split(" ");
        int left = 0;
        int right = tokens.length - 1;

        while (left < right) {
            swap(tokens, left, right);
            left++;
            right--;
        }

        return String.join(" ", tokens);
    }

    private static void swap(String[] tokens, int i, int j) {
        String tmp = tokens[i];
        tokens[i] = tokens[j];
        tokens[j] = tmp;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();

            String reversed = reverse(line);
            System.out.println(reversed);
        }
    }
}
