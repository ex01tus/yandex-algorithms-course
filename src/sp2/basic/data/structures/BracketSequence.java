package sp2.basic.data.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Вот какую задачу Тимофей предложил на собеседовании одному из кандидатов.
 * Если вы с ней ещё не сталкивались, то наверняка столкнётесь –— она довольно популярная.
 * Дана скобочная последовательность. Нужно определить, правильная ли она.
 * Будем придерживаться такого определения:
 * пустая строка —– правильная скобочная последовательность;
 * правильная скобочная последовательность, взятая в скобки одного типа, –— правильная скобочная последовательность;
 * правильная скобочная последовательность с приписанной слева или справа правильной скобочной последовательностью —– тоже правильная.
 * На вход подаётся последовательность из скобок трёх видов: [], (), {}.
 * Напишите функцию is_correct_bracket_seq, которая принимает на вход скобочную последовательность и возвращает True,
 * если последовательность правильная, а иначе False.
 *
 * На вход подаётся одна строка, содержащая скобочную последовательность. Скобки записаны подряд, без пробелов.
 *
 * Выведите «True» или «False».
 */
public class BracketSequence {

    private static boolean isCorrectBracketSequence(String sequence) {
        if (sequence.isEmpty()) return true;

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : sequence.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                Character prev = stack.peek();
                if (prev == null) return false;
                stack.pop();

                if (c == ')' && prev == '(') continue;
                if (c == ']' && prev == '[') continue;
                if (c == '}' && prev == '{') continue;

                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence = reader.readLine();
            boolean isCorrect = isCorrectBracketSequence(sequence);
            System.out.println(isCorrect ? "True" : "False");
        }
    }
}
