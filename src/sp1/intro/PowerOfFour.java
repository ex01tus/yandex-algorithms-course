package sp1.intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Вася на уроке математики изучил степени. Теперь он хочет написать программу, которая определяет,
 * будет ли положительное целое число степенью четвёрки.
 * Подсказка: степенью четвёрки будут все числа вида 4n, где n – целое неотрицательное число.
 *
 * На вход подаётся целое число в диапазоне от 1 до 10000.
 *
 * Выведите «True», если число является степенью четырёх, «False» –— в обратном случае.
 */
public class PowerOfFour {

    private static boolean isPowerOfFour(int n) {
        if (n == 1) return true;

        while (n != 1) {
            if (n % 4 != 0) return false;
            n = n / 4;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            if (isPowerOfFour(n)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

}