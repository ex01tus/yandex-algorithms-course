package sp3.recursion.and.sorting.finals;

import java.io.*;
import java.util.Comparator;

/**
 * Тимофей решил организовать соревнование по спортивному программированию, чтобы найти талантливых стажёров.
 * Задачи подобраны, участники зарегистрированы, тесты написаны. Осталось придумать, как в конце соревнования будет
 * определяться победитель.
 * Каждый участник имеет уникальный логин. Когда соревнование закончится, к нему будут привязаны два показателя:
 * количество решённых задач Pi и размер штрафа Fi. Штраф начисляется за неудачные попытки и время, затраченное на задачу.
 * Тимофей решил сортировать таблицу результатов следующим образом: при сравнении двух участников выше будет идти тот,
 * у которого решено больше задач. При равенстве числа решённых задач первым идёт участник с меньшим штрафом.
 * Если же и штрафы совпадают, то первым будет тот, у которого логин идёт раньше в алфавитном (лексикографическом) порядке.
 * Тимофей заказал толстовки для победителей и накануне поехал за ними в магазин. В своё отсутствие он поручил вам
 * реализовать алгоритм быстрой сортировки (англ. quick sort) для таблицы результатов. Так как Тимофей любит спортивное
 * программирование и не любит зря расходовать оперативную память, то ваша реализация сортировки не может потреблять O(n)
 * дополнительной памяти для промежуточных данных (такая модификация быстрой сортировки называется "in-place").
 *
 * Как и в случае обычной быстрой сортировки, которая использует дополнительную память, необходимо выбрать опорный элемент (англ. pivot),
 * а затем переупорядочить массив. Сделаем так, чтобы сначала шли элементы, не превосходящие опорного, а затем —– большие опорного.
 * Затем сортировка вызывается рекурсивно для двух полученных частей. Именно на этапе разделения элементов на группы в обычном
 * алгоритме используется дополнительная память. Теперь разберёмся, как реализовать этот шаг in-place.
 * Пусть мы как-то выбрали опорный элемент. Заведём два указателя left и right, которые изначально будут указывать
 * на левый и правый концы отрезка соответственно. Затем будем двигать левый указатель вправо до тех пор, пока он указывает
 * на элемент, меньший опорного. Аналогично двигаем правый указатель влево, пока он стоит на элементе, превосходящем опорный.
 * В итоге окажется, что левее от left все элементы точно принадлежат первой группе, а правее от right — второй.
 * Элементы, на которых стоят указатели, нарушают порядок. Поменяем их местами и продвинем указатели на следующие элементы.
 * Будем повторять это действие до тех пор, пока left и right не столкнутся.
 *
 * В первой строке задано число участников n, 1 ≤ n ≤ 100 000.
 * В каждой из следующих n строк задана информация про одного из участников.
 * i-й участник описывается тремя параметрами:
 * - уникальным логином (строкой из маленьких латинских букв длиной не более 20)
 * - числом решённых задач Pi
 * - штрафом Fi
 * Fi и Pi — целые числа, лежащие в диапазоне от 0 до 10^9.
 *
 * Для отсортированного списка участников выведите по порядку их логины по одному в строке.
 *
 * https://contest.yandex.ru/contest/23815/run-report/68444934/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Выбирается элемент `pivot` в середине интервала. Два указателя – `l` и `r` – движутся навстречу друг другу, начиная
 * с границ интервала. `l` движется вправо до тех пор, пока элемент, на который он указывает, меньше `pivot`.
 * `r` движется влево пока элемент, на который он указывает, больше `pivot`. Когда оба указателя останавливаются, они
 * указывают на элементы, находящиеся не на своих местах. Элементы переставляются. Таким образом получаем, что все элементы,
 * левее `r` меньше `pivot`, а все элементы правее `l` – больше. Рекурсивно повторяем вышеописанные действия для левой и правой
 * частей интервала.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * На каждом уровне рекурсии слева оказываются элементы, меньшие некоторого числа из интервала, а справа – большие.
 * Рекурсивные вызовы продолжаются, пока не будет получен пустой массив или массив из одного элемента,
 * которые по определению являются отсортированными. Поскольку все перестановки совершаются in-place в исходном массиве,
 * то весь массив – отсортированный.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * O(N^2) – возможно подобрать такую последовательность данных, для которой массив каждый раз будет не делиться примерно
 * пополам, а уменьшаться до n - 1. Например, когда на каждой итерации `pivot` – максимальный элемент в интервале. Получим
 * рекурсию глубиной N, с функцией, выполняющей O(N) операций. Итого, в худшем случае сложность – O(N^2).
 * Реалистично средний случай – O(NlogN).
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(logN) – дополнительная память расходуется на стек рекурсивных вызовов, имеющий грубину logN.
 */
public class InPlaceQuickSort {

    private static void quickSort(Competitor[] arr, int left, int right) {
        if (left >= right) return;

        int l = left;
        int r = right;
        Competitor pivot = arr[(l + r) / 2];

        while (l <= r) {
            if (arr[l].compareTo(pivot) < 0) {
                l++;
            } else if (arr[r].compareTo(pivot) > 0) {
                r--;
            } else {
                swap(arr, l, r);
                l++;
                r--;
            }
        }

        quickSort(arr, left, r);
        quickSort(arr, l, right);
    }

    private static void swap(Competitor[] arr, int i, int j) {
        Competitor tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int competitorsNumber = Integer.parseInt(reader.readLine());
            Competitor[] competitors = readArray(reader, competitorsNumber);
            quickSort(competitors, 0, competitors.length - 1);
            printArray(writer, competitors);
        }
    }

    private static Competitor[] readArray(BufferedReader reader, int competitorsNumber) throws IOException {
        Competitor[] competitors = new Competitor[competitorsNumber];
        for (int i = 0; i < competitorsNumber; i++) {
            Competitor competitor = new Competitor(reader.readLine());
            competitors[i] = competitor;
        }

        return competitors;
    }

    private static void printArray(BufferedWriter writer, Competitor[] competitors) throws IOException {
        for (Competitor competitor : competitors) {
            writer.write(competitor.getLogin());
            writer.newLine();
        }
    }

    private static class Competitor implements Comparable<Competitor> {

        private static final Comparator<Competitor> COMPARATOR = Comparator
                .comparing(Competitor::getSolvedTasksNumber, Comparator.reverseOrder())
                .thenComparingInt(Competitor::getPenalty)
                .thenComparing(Competitor::getLogin);

        private final String login;
        private final int solvedTasksNumber;
        private final int penalty;

        public Competitor(String input) {
            String[] tokens = input.split(" ");
            this.login = tokens[0];
            this.solvedTasksNumber = Integer.parseInt(tokens[1]);
            this.penalty = Integer.parseInt(tokens[2]);
        }

        @Override
        public int compareTo(Competitor other) {
            return COMPARATOR.compare(this, other);
        }

        public String getLogin() {
            return login;
        }

        public int getSolvedTasksNumber() {
            return solvedTasksNumber;
        }

        public int getPenalty() {
            return penalty;
        }
    }
}
