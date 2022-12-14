package sp3.recursion.and.sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * На клавиатуре старых мобильных телефонов каждой цифре соответствовало несколько букв. Примерно так:
 * 2:'abc',
 * 3:'def',
 * 4:'ghi',
 * 5:'jkl',
 * 6:'mno',
 * 7:'pqrs',
 * 8:'tuv',
 * 9:'wxyz'
 * Вам известно в каком порядке были нажаты кнопки телефона, без учета повторов.
 * Напечатайте все комбинации букв, которые можно набрать такой последовательностью нажатий.
 *
 * На вход подается строка, состоящая из цифр 2-9 включительно. Длина строки не превосходит 10 символов.
 *
 * Выведите все возможные комбинации букв через пробел.
 */
public class IterativeLetterCombinations {

    private static final Map<Character, List<Character>> MAP = Map.of(
            '2', List.of('a', 'b', 'c'),
            '3', List.of('d', 'e', 'f'),
            '4', List.of('g', 'h', 'i'),
            '5', List.of('j', 'k', 'l'),
            '6', List.of('m', 'n', 'o'),
            '7', List.of('p', 'q', 'r', 's'),
            '8', List.of('t', 'u', 'v'),
            '9', List.of('w', 'x', 'y', 'z'));

    private static void generate(String input, List<String> output, String prefix, int idx) {
        if (idx == input.length()) {
            output.add(prefix);
            return;
        }

        char key = input.charAt(idx);
        List<Character> letters = MAP.get(key);
        for (char letter : letters) {
            generate(input, output, prefix + letter, idx + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String input = reader.readLine();
            List<String> output = new ArrayList<>();
            generate(input, output, "", 0);
            for (String s : output) {
                writer.write(s + " ");
            }
        }
    }
}
