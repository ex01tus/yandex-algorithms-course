package sp2.basic.data.structures.finals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Гоша реализовал структуру данных Дек, максимальный размер которого определяется заданным числом.
 * Методы push_back(x), push_front(x), pop_back(), pop_front() работали корректно.
 * Но, если в деке было много элементов, программа работала очень долго. Дело в том, что не все операции выполнялись за O(1).
 * Помогите Гоше! Напишите эффективную реализацию.
 * Внимание: при реализации используйте кольцевой буфер.
 *
 * В первой строке записано количество команд n — целое число, не превосходящее 100000.
 * Во второй строке записано число m — максимальный размер дека. Он не превосходит 50000.
 * В следующих n строках записана одна из команд:
 * push_back(value) – добавить элемент в конец дека. Если в деке уже находится максимальное число элементов, вывести «error».
 * push_front(value) – добавить элемент в начало дека. Если в деке уже находится максимальное число элементов, вывести «error».
 * pop_front() – вывести первый элемент дека и удалить его. Если дек был пуст, то вывести «error».
 * pop_back() – вывести последний элемент дека и удалить его. Если дек был пуст, то вывести «error».
 * Value — целое число, по модулю не превосходящее 1000.
 *
 * Выведите результат выполнения каждой команды на отдельной строке.
 * Для успешных запросов push_back(x) и push_front(x) ничего выводить не надо.
 *
 * https://contest.yandex.ru/contest/22781/run-report/68184433/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Используем два указателя – tail и head. tail всегда указывает на первый элемент дека. head всегда указывает на следующую
 * свободную для записи ячейку в голове дека. Каждая из четырех операций перемещает указатели, чтобы описанные выше условия
 * выполнялись. В случае превышения максимального размера для push-операций или обращения к пустой деке для pop-операций
 * бросается исключение.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * В каждый момент времени указатели позволяют получить доступ как к голове, так и к хвосту дека. Использующийся "под капотом"
 * массив "закольцован" (см. вычисление tail и head для каждой операции), что позволяет деку свободно расти
 * в обоих направлениях, не вызывая ошибок с индексами.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * O(1) для каждой операции – все операции реализованы через обращение к массиву по индексу, выполняющееся за константное время
 * O(N) для всей программы – по условию последовательно выполняется N операций
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(N) – размер массива, использующегося для хранения элементов, определяется максимальным размером дека
 */
public class RingBufferDequeTask {

    private static RingBufferDeque deque;

    public static class RingBufferDeque {

        private final int maxSize;
        private final Integer[] arr;

        private int tail = 0;
        private int head = 0;
        private int size = 0;

        public RingBufferDeque(int maxSize) {
            this.maxSize = maxSize;
            this.arr = new Integer[maxSize];
        }

        public void pushBack(int value) {
            if (size == maxSize) throw new IllegalStateException();

            tail = tail == 0 ? maxSize - 1 : tail - 1;
            arr[tail] = value;
            size++;
        }

        public void pushFront(int value) {
            if (size == maxSize) throw new IllegalStateException();

            arr[head] = value;
            head = (head + 1) % maxSize;
            size++;
        }

        public int popBack() {
            if (size == 0) throw new IllegalStateException();

            int value = arr[tail];
            arr[tail] = null;
            tail = (tail + 1) % maxSize;
            size--;

            return value;
        }

        public int popFront() {
            if (size == 0) throw new IllegalStateException();

            head = head == 0 ? maxSize - 1 : head - 1;
            int value = arr[head];
            arr[head] = null;
            size--;

            return value;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int commandsNumber = Integer.parseInt(reader.readLine());
            int maxSize = Integer.parseInt(reader.readLine());
            deque = new RingBufferDeque(maxSize);

            for (int i = 0; i < commandsNumber; i++) {
                String command = reader.readLine();
                try {
                    executeAndPrint(command);
                } catch (IllegalStateException e) {
                    System.out.println("error");
                }
            }
        }
    }

    private static void executeAndPrint(String command) {
        if (command.startsWith("push_back")) {
            deque.pushBack(Integer.parseInt(command.split(" ")[1]));
        } else if (command.startsWith("push_front")) {
            deque.pushFront(Integer.parseInt(command.split(" ")[1]));
        } else if (command.equals("pop_back")) {
            System.out.println(deque.popBack());
        } else if (command.equals("pop_front")) {
            System.out.println(deque.popFront());
        } else {
            throw new IllegalArgumentException("Unknown command: " + command);
        }
    }
}
