package sp2.basic.data.structures.finals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Задание связано с обратной польской нотацией. Она используется для парсинга арифметических выражений.
 * Еще её иногда называют постфиксной нотацией. В постфиксной нотации операнды расположены перед знаками операций.
 * Пример 1:
 * 3 4 +
 * означает 3 + 4 и равно 7
 * Пример 2:
 * 12 5 /
 * Так как деление целочисленное, то в результате получим 2.
 * Пример 3:
 * 10 2 4 * -
 * означает 10 - 2 * 4 и равно 2
 * Замечание про отрицательные числа и деление: в этой задаче под делением понимается математическое целочисленное деление.
 * Это значит, что округление всегда происходит вниз. А именно: если a / b = c, то b ⋅ c — это наибольшее число,
 * которое не превосходит a и одновременно делится без остатка на b.
 * Например, -1 / 3 = -1. Будьте осторожны: в C++, Java и Go, например, деление чисел работает иначе.
 * В текущей задаче гарантируется, что деления на отрицательное число нет.
 *
 * В единственной строке дано выражение, записанное в обратной польской нотации. Числа и арифметические операции записаны через пробел.
 * На вход могут подаваться операции: +, -, *, / и числа, по модулю не превосходящие 10000.
 * Гарантируется, что значение промежуточных выражений в тестовых данных по модулю не больше 50000.
 *
 * Выведите единственное число — значение выражения.
 *
 * https://contest.yandex.ru/contest/22781/run-report/68183120/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * В цикле обходим выражение слева направо, по одному элементу за раз. Если элемент – число, добавляем его в стек.
 * Если элемент – оператор, извлекаем из стека два предыдущих значения – операнды. Вычисляем результат операции. Кладем результат
 * операции в стек. После завершения цикла извлекаем из вершины стека результат всего выражения.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Для каждого оператора два предыдущих значения в стеке – операнды. В простых случаях вида `1 2 +` это гарантировано самой
 * нотацией и последовательным добавлением элементов в стек. Кейсы вида `10 2 4 * -` покрываются добавлением результата операции
 * обратно в стек. Т.о. `2 4 *` сводится к простому кейсу, описанному выше, равно как и `10 8 -`.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * O(N) – время выполнения линейно зависит от длины выражения, т.к. выражение обходится в цикле по одному элементу за раз
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(N) – размер стека линейно зависит от длины выражения, т.к. каждый элемент выражения будет помещен в стек
 */
public class ReversePolishNotation {

    private static final Pattern OPERATORS_PATTERN = Pattern.compile("[+\\-/*]");

    private static int calc(List<String> expression) {
        Deque<String> stack = new LinkedList<>();

        for (String element : expression) {
            if (isOperator(element)) {
                int secondOperand = Integer.parseInt(stack.pop());
                int firstOperand = Integer.parseInt(stack.pop());
                int operationResult = execute(firstOperand, secondOperand, element);

                stack.push(String.valueOf(operationResult));
            } else {
                stack.push(element);
            }
        }

        return Integer.parseInt(stack.pop());
    }

    private static boolean isOperator(String element) {
        return OPERATORS_PATTERN.matcher(element).matches();
    }

    private static int execute(int firstOperand, int secondOperand, String operator) {
        switch (operator) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "/":
                if (secondOperand == 0) {
                    throw new ArithmeticException("/ by zero");
                }

                return (int) Math.floor((float) firstOperand / secondOperand);
            case "*":
                return firstOperand * secondOperand;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<String> expression = List.of(reader.readLine().split(" "));
            System.out.println(calc(expression));
        }
    }
}
