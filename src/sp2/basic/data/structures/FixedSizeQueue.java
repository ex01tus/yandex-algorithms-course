package sp2.basic.data.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Астрологи объявили день очередей ограниченного размера. Тимофею нужно написать класс MyQueueSized,
 * который принимает параметр max_size, означающий максимально допустимое количество элементов в очереди.
 * Помогите ему —– реализуйте программу, которая будет эмулировать работу такой очереди.
 * Функции, которые надо поддержать, описаны в формате ввода.
 *
 * В первой строке записано одно число — количество команд, оно не превосходит 5000.
 * Во второй строке задан максимально допустимый размер очереди, он не превосходит 5000.
 * Далее идут команды по одной на строке. Команды могут быть следующих видов:
 * push(x) — добавить число x в очередь;
 * pop() — удалить число из очереди и вывести на печать;
 * peek() — напечатать первое число в очереди;
 * size() — вернуть размер очереди;
 * При превышении допустимого размера очереди нужно вывести «error».
 * При вызове операций pop() или peek() для пустой очереди нужно вывести «None».
 * Формат вывода
 *
 * Напечатайте результаты выполнения нужных команд, по одному на строке.
 */
public class FixedSizeQueue {

    private static MyQueue queue;

    public static class MyQueue {

        private final int maxSize;
        private final Integer[] arr;

        public MyQueue(int maxSize) {
            this.maxSize = maxSize;
            this.arr = new Integer[maxSize];
        }

        private int size = 0;
        private int head = 0;
        private int tail = 0;

        private Optional<Integer> push(int value) {
            if (size == maxSize) return Optional.empty();

            arr[head] = value;
            head = (head + 1) % maxSize;
            size++;

            return Optional.of(value);
        }

        private Optional<Integer> peek() {
            if (size == 0) return Optional.empty();

            return Optional.of(arr[tail]);
        }

        private Optional<Integer> pop() {
            if (size == 0) return Optional.empty();

            int value = arr[tail];
            arr[tail] = null;
            tail = (tail + 1) % maxSize;
            size--;

            return Optional.of(value);
        }

        private int size() {
            return size;
        }
    }

    private static String execute(String command) {
        if (command.startsWith("push")) {
            Optional<Integer> push = queue.push(Integer.parseInt(command.split(" ")[1]));
            return push.isEmpty() ? "error" : "";
        }

        if (command.equals("pop")) {
            Optional<Integer> pop = queue.pop();
            return pop.isEmpty() ? "None" : String.valueOf(pop.get());
        }

        if (command.equals("peek")) {
            Optional<Integer> peek = queue.peek();
            return peek.isEmpty() ? "None" : String.valueOf(peek.get());
        }

        if (command.equals("size")) {
            return String.valueOf(queue.size());
        }

        throw new RuntimeException();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            int maxSize = Integer.parseInt(reader.readLine());
            queue = new MyQueue(maxSize);

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
