package sp1.intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Вася реализовал функцию, которая переводит целое число из десятичной системы в двоичную.
 * Но, кажется, она получилась не очень оптимальной. Попробуйте написать более эффективную программу.
 * Не используйте встроенные средства языка по переводу чисел в бинарное представление.
 *
 * На вход подаётся целое число в диапазоне от 0 до 10000.
 *
 * Выведите двоичное представление этого числа.
 */
public class BinaryNumber {

    private static String getBinaryNumber(int n) {
        if (n == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int remainder = n % 2;
            n = n / 2;
            sb.append(remainder);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            System.out.println(getBinaryNumber(n));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}