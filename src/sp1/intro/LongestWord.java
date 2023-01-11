package sp1.intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Чтобы подготовиться к семинару, Гоше надо прочитать статью по эффективному менеджменту.
 * Так как Гоша хочет спланировать день заранее, ему необходимо оценить сложность статьи.
 * Он придумал такой метод оценки: берётся случайное предложение из текста и в нём ищется самое длинное слово.
 * Его длина и будет условной сложностью статьи. Помогите Гоше справиться с этой задачей.
 *
 * В первой строке дана длина текста L (1 ≤ L ≤ 10^5).
 * В следующей строке записан текст, состоящий из строчных латинских букв и пробелов.
 * Слово —– последовательность букв, не разделённых пробелами. Пробелы могут стоять в самом начале строки и в самом её конце.
 * Текст заканчивается переносом строки, этот символ не включается в число остальных L символов.
 *
 * В первой строке выведите самое длинное слово. Во второй строке выведите его длину.
 * Если подходящих слов несколько, выведите то, которое встречается раньше.
 */
public class LongestWord {

    private static String getLongestWord(String text) {
        int left = 0;
        int right = 0;

        int maxLength = 0;
        int savedLeft = left;
        int savedRight = right;

        while (right < text.length()) {
            if (text.charAt(left) == ' ') {
                left++;
                right++;
                continue;
            }

            if (text.charAt(right) != ' ') {
                right++;
            } else {
                int length = right - left;
                if (length > maxLength) {
                    maxLength = length;
                    savedLeft = left;
                    savedRight = right;
                }

                left = right + 1;
                right = left + 1;
            }
        }

        int length = right - left;
        if (length > maxLength) {
            savedLeft = left;
            savedRight = right;
        }

        return text.substring(savedLeft, savedRight);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int textLength = readInt(reader);
            String text = reader.readLine();
            String longestWord = getLongestWord(text);
            System.out.println(longestWord);
            System.out.println(longestWord.length());
        }

    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

}