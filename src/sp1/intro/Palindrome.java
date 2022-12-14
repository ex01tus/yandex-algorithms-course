package sp1.intro;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Помогите Васе понять, будет ли фраза палиндромом. Учитываются только буквы и цифры,
 * заглавные и строчные буквы считаются одинаковыми.
 * Решение должно работать за O(N), где N — длина строки на входе.
 *
 * В единственной строке записана фраза или слово. Буквы могут быть только латинские.
 * Длина текста не превосходит 20000 символов.
 * Фраза может состоять из строчных и прописных латинских букв, цифр, знаков препинания.
 *
 * Выведите «True», если фраза является палиндромом, и «False», если не является.
 */
public class Palindrome {

    private static boolean isPalindrome(String text) {
        if (text.length() == 1) return true;

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            char leftChar = text.charAt(left);
            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
                continue;
            }

            char rightChar = text.charAt(right);
            if (!Character.isLetterOrDigit(rightChar)) {
                right--;
                continue;
            }

            if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                return false;
            } else {
                left++;
                right--;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();
            if (isPalindrome(text)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
