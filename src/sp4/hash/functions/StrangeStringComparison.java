package sp4.hash.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Жители Алгосского архипелага придумали новый способ сравнения строк. Две строки считаются равными,
 * если символы одной из них можно заменить на символы другой так, что первая строка станет точной копией второй строки.
 * При этом необходимо соблюдение двух условий:
 * Порядок вхождения символов должен быть сохранён.
 * Одинаковым символам первой строки должны соответствовать одинаковые символы второй строки. Разным символам —– разные.
 *
 * В первой строке записана строка s, во второй –— строка t. Длины обеих строк не превосходят 10^6.
 * Обе строки содержат хотя бы по одному символу и состоят только из маленьких латинских букв.
 * Строки могут быть разной длины.
 *
 * Выведите «YES», если строки равны (согласно вышеописанным правилам), и «NO» в ином случае.
 */
public class StrangeStringComparison {

    private static boolean isEqual(String a, String b) {
        if (a.length() != b.length()) return false;

        Map<Character, Character> aToB = new HashMap<>();
        Map<Character, Character> bToA = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            char aChar = a.charAt(i);
            char bChar = b.charAt(i);
            aToB.put(aChar, bChar);
            bToA.put(bChar, aChar);
        }

        for (int i = 0; i < a.length(); i++) {
            char aChar = a.charAt(i);
            char bChar = b.charAt(i);

            if (bChar != aToB.get(aChar) || aChar != bToA.get(bChar)) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();

            boolean isEqual = isEqual(a, b);
            System.out.println(isEqual ? "YES" : "NO");
        }
    }
}
