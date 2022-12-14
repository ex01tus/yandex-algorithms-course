package sp1.intro.finals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Гоша и Тимофей нашли необычный тренажёр для скоростной печати и хотят освоить его.
 * Тренажёр представляет собой поле из клавиш 4× 4, в котором на каждом раунде появляется конфигурация цифр и точек.
 * На клавише написана либо точка, либо цифра от 1 до 9. В момент времени t игрок должен одновременно нажать на все клавиши,
 * на которых написана цифра t. Гоша и Тимофей могут нажать в один момент времени на k клавиш каждый.
 * Если в момент времени t были нажаты все нужные клавиши, то игроки получают 1 балл.
 * Найдите число баллов, которое смогут заработать Гоша и Тимофей, если будут нажимать на клавиши вдвоём.
 *
 * В первой строке дано целое число k (1 ≤ k ≤ 5).
 * В четырёх следующих строках задан вид тренажёра –— по 4 символа в каждой строке.
 * Каждый символ —– либо точка, либо цифра от 1 до 9. Символы одной строки идут подряд и не разделены пробелами.
 *
 * Выведите единственное число –— максимальное количество баллов, которое смогут набрать Гоша и Тимофей.
 */
public class SleightOfHand {

    private static final Integer ROWS_NUMBER = 4;
    private static final Integer KEYS_NUMBER = 10;
    private static final Integer PLAYERS_NUMBER = 2;
    private static final Character EMPTY_SLOT = '.';

    private static int play(String trainer, int keystrokesPerPlayer) {
        return countPoints(
                countKeys(trainer),
                keystrokesPerPlayer);
    }

    private static List<Integer> countKeys(String trainer) {
        List<Integer> keysCount = new ArrayList<>(Collections.nCopies(KEYS_NUMBER, 0));
        for (char key : trainer.toCharArray()) {
            if (key != EMPTY_SLOT) {
                int idx = Character.getNumericValue(key);
                int count = keysCount.get(idx) + 1;
                keysCount.set(idx, count);
            }
        }

        return keysCount;
    }

    private static int countPoints(List<Integer> keysCount, int keystrokesPerPlayer) {
        int points = 0;
        for (int timeUnit = 1; timeUnit < KEYS_NUMBER; timeUnit++) {
            int count = keysCount.get(timeUnit);
            if (count > 0 && count <= keystrokesPerPlayer * PLAYERS_NUMBER) {
                points++;
            }
        }

        return points;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int keystrokesPerPlayer = Integer.parseInt(reader.readLine());

            StringBuilder trainer = new StringBuilder();
            for (int i = 0; i < ROWS_NUMBER; i++) {
                trainer.append(reader.readLine());
            }

            int points = play(trainer.toString(), keystrokesPerPlayer);
            System.out.println(points);
        }
    }
}
