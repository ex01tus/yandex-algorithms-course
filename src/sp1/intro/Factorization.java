package sp1.intro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Тимофей готовит доклад ко дню открытых дверей кафедры Теории чисел. Он собирается рассказать про Основную теорему арифметики.
 * В соответствии с этой теоремой, любое число раскладывается на произведение простых множителей
 * единственным образом –— с точностью до их перестановки.
 * Например, число 8 можно представить как 2 × 2 × 2.
 * Число 50 –— как 2 × 5 × 5 (или 5 × 5 × 2, или 5 × 2 × 5). Три варианта отличаются лишь порядком следования множителей.
 * Разложение числа на простые множители называется факторизацией числа.
 * Факторизацию в уме делать сложно, поэтому помогите Тимофею написать для этого программу.
 *
 * В единственной строке дано число n (2 ≤ n ≤ 10^9), которое нужно факторизовать.
 *
 * Выведите в порядке неубывания простые множители, на которые раскладывается число n.
 */
public class Factorization {

    private static List<Integer> factorize(int n) {
        List<Integer> result = new ArrayList<>();
        int i = 2;

        while (n >= i * i) {
            if (n % i == 0) {
                n = n / i;
                result.add(i);
            } else {
                i++;
            }
        }

        result.add(n);

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> factorization = factorize(n);
            for (int elem : factorization) {
                writer.write(elem + " ");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
