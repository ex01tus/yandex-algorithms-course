package sp1.intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Тимофей спросил у Гоши, умеет ли тот работать с числами в двоичной системе счисления.
 * Он ответил, что проходил это на одной из первых лекций по информатике. Тимофей предложил Гоше решить задачку.
 * Два числа записаны в двоичной системе счисления. Нужно вывести их сумму, также в двоичной системе.
 * Встроенную в язык программирования возможность сложения двоичных чисел применять нельзя.
 * Решение должно работать за O(N), где N –— количество разрядов максимального числа на входе.
 *
 * Два числа в двоичной системе счисления, каждое на отдельной строке. Длина каждого числа не превосходит 10 000 символов.
 *
 * Одно число в двоичной системе счисления.
 */
public class SumOfBinaries {

    private static boolean remains = false;

    private static String sumOfBinaries(String a, String b) {
        StringBuilder sb = new StringBuilder();

        String longy = a.length() > b.length() ? a : b;
        String shorty = a.length() > b.length() ? b : a;
        int diff = longy.length() - shorty.length();

        for (int i = shorty.length() - 1; i >= 0; i--) {
            char c = compute(shorty.charAt(i), longy.charAt(i + diff));
            sb.append(c);
        }

        for (int i = diff - 1; i >= 0; i--) {
            char c = compute(longy.charAt(i), '0');
            sb.append(c);
        }

        if (remains) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }

    private static char compute(char first, char second) {
        if (first == '0' && second == '0') {
            if (remains) {
                remains = false;
                return '1';
            } else {
                return '0';
            }
        } else if (first == '1' && second == '1') {
            if (remains) {
                return '1';
            } else {
                remains = true;
                return '0';
            }
        } else {
            if (remains) {
                return '0';
            } else {
                return '1';
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();
            System.out.println(sumOfBinaries(a, b));
        }
    }
}
