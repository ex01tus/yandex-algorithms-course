package sp7.greedy.and.dynamic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Гуляя по одному из островов Алгосского архипелага, Гоша набрёл на пещеру, в которой лежат кучи золотого песка.
 * К счастью, у Гоши есть с собой рюкзак грузоподъёмностью до M килограмм, поэтому он может унести с собой
 * какое-то ограниченное количество золота.
 * Всего золотых куч n штук, и все они разные. В куче под номером i содержится mi килограммов золотого песка,
 * а стоимость одного килограмма — ci алгосских франков.
 * Помогите Гоше наполнить рюкзак так, чтобы общая стоимость золотого песка в пересчёте на алгосские франки была максимальной.
 *
 * В первой строке задано целое число M — грузоподъёмность рюкзака Гоши (0 ≤ M ≤ 10^8).
 * Во второй строке дано количество куч с золотым песком — целое число n (1 ≤ n ≤ 10^5).
 * В каждой из следующих n строк описаны кучи: i-ая куча задаётся двумя целыми числами ci и mi,
 * записанными через пробел (1 ≤ ci ≤ 10^7, 1 ≤ mi ≤ 10^8).
 *
 * Выведите единственное число —– максимальную сумму (в алгосских франках),
 * которую Гоша сможет вынести из пещеры в своём рюкзаке.
 */
public class GoldenFever {

    private static long maxGold(int backpackSize, List<int[]> gold) {
        gold.sort((g1, g2) -> Integer.compare(g2[0], g1[0]));

        int spaceLeft = backpackSize;
        long price = 0;

        for (int[] g : gold) {
            if (spaceLeft == 0) break;

            long kilos = Math.min(spaceLeft, g[1]);

            spaceLeft -= kilos;
            price += kilos * g[0];
        }

        return price;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int backpackSize = readInt(reader);
            int n = readInt(reader);
            List<int[]> gold = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] s = reader.readLine().split(" ");
                gold.add(new int[] {Integer.parseInt(s[0]), Integer.parseInt(s[1])});
            }

            long price = maxGold(backpackSize, gold);
            System.out.println(price);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
