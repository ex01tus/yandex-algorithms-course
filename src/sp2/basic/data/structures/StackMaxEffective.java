package sp2.basic.data.structures;

import java.io.*;
import java.util.*;

/**
 * Реализуйте класс StackMaxEffective, поддерживающий операцию определения максимума среди элементов в стеке.
 * Сложность операции должна быть O(1). Для пустого стека операция должна возвращать None.
 * При этом push(x) и pop() также должны выполняться за константное время.
 *
 * В первой строке записано одно число — количество команд, оно не превосходит 100000.
 * Далее идут команды по одной в строке. Команды могут быть следующих видов:
 * push(x) — добавить число x в стек;
 * pop() — удалить число с вершины стека;
 * get_max() — напечатать максимальное число в стеке;
 * Если стек пуст, при вызове команды get_max нужно напечатать «None», для команды pop — «error».
 *
 * Для каждой команды get_max() напечатайте результат её выполнения.
 * Если стек пустой, для команды get_max() напечатайте «None».
 * Если происходит удаление из пустого стека — напечатайте «error».
 */
public class StackMaxEffective {

    private static final List<Integer> LIST = new ArrayList<>();
    private static final LinkedList<Integer> MAX_VALUE_STACK = new LinkedList<>();
    private static int max = Integer.MIN_VALUE;

    public static void push(int value) {
        LIST.add(value);
        if (value >= max) {
            max = value;
            MAX_VALUE_STACK.push(max);
        }
    }

    public static Optional<Integer> pop() {
        if (LIST.isEmpty()) {
            return Optional.empty();
        }

        int value = LIST.remove(LIST.size() - 1);
        if (value == max) {
            findMax();
        }

        return Optional.of(value);
    }

    public static Optional<Integer> getMax() {
        return max == Integer.MIN_VALUE ? Optional.empty() : Optional.of(max);
    }

    private static void findMax() {
        MAX_VALUE_STACK.pop();
        if (MAX_VALUE_STACK.isEmpty()) {
            max = Integer.MIN_VALUE;
            return;
        }

        max = Optional.ofNullable(MAX_VALUE_STACK.peek())
                .orElse(Integer.MIN_VALUE);
    }

    private static String execute(String command) {
        if (command.equals("get_max")) {
            Optional<Integer> max = getMax();
            return max.isEmpty() ? "None" : String.valueOf(max.get());
        }

        if (command.startsWith("push")) {
            push(Integer.parseInt(command.split(" ")[1]));
            return "";
        }

        if (command.equals("pop")) {
            Optional<Integer> pop = pop();
            return pop.isEmpty() ? "error" : "";
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
