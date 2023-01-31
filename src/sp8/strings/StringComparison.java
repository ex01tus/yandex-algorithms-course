package sp8.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Алла придумала новый способ сравнивать две строки: чтобы сравнить строки a и b, в них надо оставить только те буквы,
 * которые в английском алфавите стоят на четных позициях. Затем полученные строки сравниваются по обычным правилам.
 * Помогите Алле реализовать новое сравнение строк.
 *
 * На вход подаются строки a и b по одной в строке. Обе строки состоят из маленьких латинских букв,
 * не бывают пустыми и не превосходят 10^5 символов в длину.
 *
 * Выведите -1, если a < b, 0, если a = b, и 1, если a > b.
 */
public class StringComparison {

    private static int compare(String first, String second) {
        int i = 0;
        int j = 0;

        while (i < first.length() || j < second.length()) {
            if (i < first.length() && (int) first.charAt(i) % 2 != 0) {
                i++;
            } else if (j < second.length() && (int) second.charAt(j) % 2 != 0) {
                j++;
            } else {
                char firstChar = i < first.length() ? first.charAt(i) : Character.MIN_VALUE;
                char secondChar = j < second.length() ? second.charAt(j) : Character.MIN_VALUE;

                int result = Character.compare(firstChar, secondChar);
                if (result != 0) return result > 0 ? 1 : -1;

                i++;
                j++;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String first = reader.readLine();
            String second = reader.readLine();

            int result = compare(first, second);
            System.out.println(result);
        }
    }
}
