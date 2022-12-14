package sp3.recursion.and.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Рита по поручению Тимофея наводит порядок в правильных скобочных последовательностях (ПСП),
 * состоящих только из круглых скобок (). Для этого ей надо сгенерировать все ПСП длины 2n в алфавитном порядке
 * —– алфавит состоит из ( и ) и открывающая скобка идёт раньше закрывающей.
 * Помогите Рите —– напишите программу, которая по заданному n выведет все ПСП в нужном порядке.
 *
 * На вход функция принимает n — целое число от 0 до 10.
 *
 * Функция должна напечатать все возможные скобочные последовательности заданной длины в алфавитном (лексикографическом) порядке.
 */
public class ParenthesesGenerator {

    private static void generate(int max, String prefix, int open, int close) {
        if (prefix.length() == max * 2) {
            System.out.println(prefix);
            return;
        }

        if (open < max) {
            generate(max, prefix + "(", open + 1, close);
        }

        if (open > close) {
            generate(max, prefix + ")", open, close + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int max = Integer.parseInt(reader.readLine());
            generate(max, "", 0, 0);
        }
    }
}
