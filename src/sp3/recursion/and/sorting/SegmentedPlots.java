package sp3.recursion.and.sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Алла захотела, чтобы у неё под окном были узкие клумбы с тюльпанами.
 * На схеме земельного участка клумбы обозначаются просто горизонтальными отрезками, лежащими на одной прямой.
 * Для ландшафтных работ было нанято n садовников. Каждый из них обрабатывал какой-то отрезок на схеме.
 * Процесс был организован не очень хорошо, иногда один и тот же отрезок или его часть могли быть обработаны сразу
 * несколькими садовниками. Таким образом, отрезки, обрабатываемые двумя разными садовниками, сливаются в один.
 * Непрерывный обработанный отрезок затем станет клумбой. Нужно определить границы будущих клумб.
 * Пример 1:
 * 7 8
 * 7 8
 * 2 3
 * 6 10
 * Два одинаковых отрезка [7, 8] и [7, 8] сливаются в один, но потом их накрывает отрезок [6, 10].
 * Таким образом, имеем две клумбы с координатами [2,3] и [6,10].
 * Пример 2:
 * 2 3
 * 5 6
 * 3 4
 * 3 4
 * Отрезки [2,3], [3, 4] и [3,4] сольются в один отрезок [2,4]. Отрезок [5,6] ни с кем не объединяется, добавляем его в ответ.
 *
 * В первой строке задано количество садовников n. Число садовников не превосходит 100 000.
 * В следующих n строках через пробел записаны координаты клумб в формате: start end, где start —– координата начала,
 * end —– координата конца. Оба числа целые, неотрицательные и не превосходят 10^7. start строго меньше, чем end.
 *
 * Нужно вывести координаты каждой из получившихся клумб в отдельных строках.
 * Данные должны выводится в отсортированном порядке —– сначала клумбы с меньшими координатами, затем —– с бОльшими.
 */
public class SegmentedPlots {

    private static class Plot {
        public int start;
        public int end;

        public Plot(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static List<Plot> merge(List<Plot> input) {
        Collections.sort(input, (a, b) -> Integer.compare(a.start, b.start));
        List<Plot> result = new ArrayList<>();

        int start = input.get(0).start;
        int end = input.get(0).end;

        for (int i = 1; i < input.size(); i++) {
            Plot current = input.get(i);
            if (end < current.start) {
                result.add(new Plot(start, end));
                start = current.start;
                end = current.end;
            } else if (end < current.end) {
                end = current.end;
            }
        }

        result.add(new Plot(start, end));
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            List<Plot> input = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] s = reader.readLine().split(" ");
                input.add(new Plot(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
            }

            List<Plot> output = merge(input);
            for (Plot plot : output) {
                writer.write(plot.start + " " + plot.end + "\n");
            }
        }
    }
}
