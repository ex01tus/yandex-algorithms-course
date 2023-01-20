package sp7.greedy.and.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Рита хочет попробовать поиграть на бирже. Но для начала она решила потренироваться на исторических данных.
 * Даны стоимости акций в каждый из n дней. В течение дня цена акции не меняется. Акции можно покупать и продавать,
 * но только по одной штуке в день. В один день нельзя совершать более одной операции (покупки или продажи).
 * Также на руках не может быть более одной акции в каждый момент времени.
 * Помогите Рите выяснить, какую максимальную прибыль она могла бы получить.
 *
 * В первой строке записано количество дней n —– целое число в диапазоне от 0 до 10 000.
 * Во второй строке через пробел записано n целых чисел в диапазоне от 0 до 1000 –— цены акций.
 *
 * Выведите число, равное максимально возможной прибыли за эти дни.
 */
public class StockExchange {

    private static int maxProfit(List<Integer> stocks) {
        if (stocks.size() < 2) return 0;

        int maxProfit = 0;

        for (int i = 1; i < stocks.size(); i++) {
            int profit = stocks.get(i) - stocks.get(i - 1);
            maxProfit += Math.max(0, profit);
        }

        return maxProfit;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> stocks = readList(reader);

            int maxProfit = maxProfit(stocks);
            System.out.println(maxProfit);
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
