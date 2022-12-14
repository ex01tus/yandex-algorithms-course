package sp3.recursion.and.sorting;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * К Васе в гости пришли одноклассники. Его мама решила угостить ребят печеньем.
 * Но не всё так просто. Печенья могут быть разного размера.
 * А у каждого ребёнка есть фактор жадности —– минимальный размер печенья, которое он возьмёт.
 * Нужно выяснить, сколько ребят останутся довольными в лучшем случае, когда они действуют оптимально.
 * Каждый ребёнок может взять не больше одного печенья.
 *
 * В первой строке записано n —– количество детей.
 * Во второй —– n чисел, разделённых пробелом, каждое из которых –— фактор жадности ребёнка. Это натуральные числа, не превосходящие 1000.
 * В следующей строке записано число m –— количество печенек.
 * Далее —– m натуральных чисел, разделённых пробелом —– размеры печенек. Размеры печенек не превосходят 1000.
 * Оба числа n и m не превосходят 10000.
 *
 * Нужно вывести одно число –— количество детей, которые останутся довольными
 */
public class Cookies {

    private static int eat(List<Integer> kids, List<Integer> cookies) {
        Collections.sort(kids);
        Collections.sort(cookies);

        int kid = 0;
        int cookie = 0;
        int counter = 0;

        while (kid < kids.size() && cookie < cookies.size()) {
            if (kids.get(kid) <= cookies.get(cookie)) {
                counter++;
                kid++;
            }

            cookie++;
        }

        return counter;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> kids = readList(reader);
            int m = readInt(reader);
            List<Integer> cookies = readList(reader);

            int result = eat(kids, cookies);
            System.out.println(result);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
