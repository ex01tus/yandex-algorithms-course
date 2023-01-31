package sp8.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * У Риты была строка s, Гоша подарил ей на 8 марта ещё n других строк ti, 1≤ i≤ n.
 * Теперь Рита думает, куда их лучше поставить. Один из вариантов —– расположить подаренные строки внутри имеющейся строки s,
 * поставив строку ti сразу после символа строки s с номером ki (в частности, если ki=0, то строка вставляется в самое начало s).
 * Помогите Рите и определите, какая строка получится после вставки в s всех подаренных Гошей строк.
 *
 * В первой строке дана строка s. Строка состоит из строчных букв английского алфавита,
 * не бывает пустой и её длина не превышает 10^5 символов.
 * Во второй строке записано количество подаренных строк — натуральное число n, 1 ≤ n ≤ 10^5.
 * В каждой из следующих n строк через пробел записаны пары ti и ki. Строка ti состоит из маленьких латинских букв
 * и не бывает пустой. ki — целое число, лежащее в диапазоне от 0 до |s|.
 * Все числа ki уникальны. Гарантируется, что суммарная длина всех строк ti не превосходит 10^5.
 *
 * Выведите получившуюся в результате вставок строку.
 */
public class WordsInsertion {

    private static String insert(String line, List<Insertion> insertions) {
        insertions.sort((i1, i2) -> Integer.compare(i1.position, i2.position));

        StringBuilder sb = new StringBuilder();
        int current = 0;
        for (Insertion insertion : insertions) {
            sb.append(line, current, insertion.position);
            sb.append(insertion.word);

            current = insertion.position;
        }

        sb.append(line.substring(current));

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            int n = Integer.parseInt(reader.readLine());

            List<Insertion> insertions = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] insert = reader.readLine().split(" ");
                String word = insert[0];
                int position = Integer.parseInt(insert[1]);
                insertions.add(new Insertion(position, word));
            }

            String inserted = insert(line, insertions);
            System.out.println(inserted);
        }
    }

    private static class Insertion {

        private final int position;
        private final String word;

        public Insertion(int position, String word) {
            this.position = position;
            this.word = word;
        }
    }
}
