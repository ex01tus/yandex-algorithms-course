package sp2.basic.data.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Любимый вариант очереди Тимофея — очередь, написанная с использованием связного списка.
 * Помогите ему с реализацией. Очередь должна поддерживать выполнение трёх команд:
 * get() — вывести элемент, находящийся в голове очереди, и удалить его. Если очередь пуста, то вывести «error».
 * put(x) — добавить число x в очередь
 * size() — вывести текущий размер очереди
 *
 * В первой строке записано количество команд n — целое число, не превосходящее 1000.
 * В каждой из следующих n строк записаны команды по одной строке.
 *
 * Выведите ответ на каждый запрос по одному в строке.
 */
public class LinkedListQueue {

    private static final List<Integer> LIST = new LinkedList<>();
    private static int size = 0;

    private static Optional<Integer> get() {
        if (size == 0) return Optional.empty();
        size--;

        return Optional.of(LIST.remove(0));
    }

    private static void put(int value) {
        LIST.add(value);
        size++;
    }

    private static int size() {
        return size;
    }

    private static String execute(String command) {
        if (command.equals("get")) {
            Optional<Integer> value = get();
            return value.isEmpty() ? "error" : String.valueOf(value.get());
        }

        if (command.startsWith("put")) {
            put(Integer.parseInt(command.split(" ")[1]));
            return "";
        }

        if (command.equals("size")) {
            return String.valueOf(size());
        }

        throw new RuntimeException();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                String command = reader.readLine();
                String result = execute(command);
                if (!result.isEmpty()) {
                    System.out.println(result);
                }
            }
        }
    }
}
