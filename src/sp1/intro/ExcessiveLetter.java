package sp1.intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Васе очень нравятся задачи про строки, поэтому он придумал свою.
 * Есть 2 строки s и t, состоящие только из строчных букв.
 * Строка t получена перемешиванием букв строки s и добавлением 1 буквы в случайную позицию.
 * Нужно найти добавленную букву.
 *
 * На вход подаются строки s и t, разделённые переносом строки. Длины строк не превосходят 1000 символов. Строки не бывают пустыми.
 *
 * Выведите лишнюю букву.
 */
public class ExcessiveLetter {

    private static char getExcessiveLetter(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.merge(c, 1, Integer::sum);
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = map.merge(c, -1, Integer::sum);
            if (count < 0) return c;
        }

        throw new RuntimeException();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            String t = reader.readLine();
            System.out.println(getExcessiveLetter(s, t));
        }
    }
}
