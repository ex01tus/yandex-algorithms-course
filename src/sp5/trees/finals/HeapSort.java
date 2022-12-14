package sp5.trees.finals;

import java.io.*;
import java.util.Comparator;

/**
 * https://contest.yandex.ru/contest/24810/run-report/68969336/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Для сортировки используется куча на максимум – в каждый момент в времени первым элементом массива "под капотом" будет
 * самый большой элемент. Для этого применяется просеивание вверх при каждом добавлении элемента и просеивание вниз
 * при каждом извлечении элемента.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Для проведения сортировки мы сначала добавляем в кучу все сортируемые элементы. Таким образом получаем на вершине
 * максимальный из элементов – из принципа работы кучи. После добавления начинаем извлекать элементы из кучи по одному.
 * После извлечения первого элемента (и последующего просеивания вниз), на вершине кучи окажется максимальный
 * из оставшихся элементов. Т.о. извлеченные элементы окажутся отсортированными в порядке убывания.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * O(NlogN) – после каждой вставки и извлечения элемента требуется просеивание сложностью O(logN). Для N элементов
 * получим O(NlogN) на вставки и O(NlogN) на извлечения. O(2 * NlogN) -> O(NlogN)
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(N) – на поддержание массива внутри кучи
 */
public class HeapSort {

    private static class Heap {

        private final Competitor[] arr;
        private int size = 0;

        public Heap(int size) {
            this.arr = new Competitor[size + 1];
        }

        public void insert(Competitor value) {
            if (size >= arr.length - 1) {
                throw new IllegalStateException("Heap is full");
            }

            size++;
            arr[size] = value;

            siftUp(size);
        }

        public Competitor extract() {
            if (size == 0) {
                throw new IllegalStateException("Heap is empty");
            }

            Competitor value = arr[1];
            arr[1] = arr[size];
            arr[size] = null;
            size--;

            siftDown(1);
            return value;
        }

        private int siftUp(int idx) {
            if (idx <= 1) {
                // already on top
                return idx;
            }

            int parent = idx / 2;
            if (arr[parent].compareTo(arr[idx]) > 0) {
                // no swap needed
                return idx;
            }

            swap(idx, parent);
            return siftUp(parent);
        }

        private int siftDown(int idx) {
            int left = 2 * idx;
            int right = 2 * idx + 1;

            if (left > size) {
                // no children
                return idx;
            }

            int newIdx;
            if (right <= size && arr[right].compareTo(arr[left]) > 0) {
                // both children
                newIdx = right;
            } else {
                // only left child
                newIdx = left;
            }

            if (arr[idx].compareTo(arr[newIdx]) > 0) {
                // no swap needed
                return idx;
            }

            swap(idx, newIdx);
            return siftDown(newIdx);
        }

        private void swap(int firstIdx, int secondIdx) {
            Competitor tmp = arr[firstIdx];
            arr[firstIdx] = arr[secondIdx];
            arr[secondIdx] = tmp;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int size = Integer.parseInt(reader.readLine());
            Heap heap = new Heap(size);

            for (int i = 0; i < size; i++) {
                Competitor competitor = new Competitor(reader.readLine());
                heap.insert(competitor);
            }

            for (int i = 0; i < size; i++) {
                Competitor competitor = heap.extract();
                writer.write(competitor.getLogin());
                writer.newLine();
            }
        }
    }

    private static class Competitor implements Comparable<Competitor> {

        private static final Comparator<Competitor> COMPARATOR = Comparator
                .comparing(Competitor::getSolvedTasksNumber)
                .thenComparing(Competitor::getPenalty, Comparator.reverseOrder())
                .thenComparing(Competitor::getLogin, Comparator.reverseOrder());

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
