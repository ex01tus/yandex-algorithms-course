package sp5.trees;

/**
 * Напишите функцию, совершающую просеивание вверх в куче на максимум. Гарантируется, что порядок элементов в куче
 * может быть нарушен только элементом, от которого запускается просеивание.
 * Функция принимает в качестве аргументов массив, в котором хранятся элементы кучи, и индекс элемента,
 * от которого надо сделать просеивание вверх. Функция должна вернуть индекс, на котором элемент оказался после просеивания.
 * Также необходимо изменить порядок элементов в переданном в функцию массиве.
 * Обратите внимание, что нулевой элемент в передаваемом массиве фиктивный, вершина кучи соответствует 1-му элементу.
 */
public class BinaryHeapSiftUp {

    public static int siftUp(int[] heap, int idx) {
        if (idx == 1) {
            // already on top
            return idx;
        }

        int parent = idx / 2;
        if (heap[parent] > heap[idx]) {
            // no swap needed
            return idx;
        }

        int tmp = heap[parent];
        heap[parent] = heap[idx];
        heap[idx] = tmp;
        return siftUp(heap, parent);
    }

    public static void main(String[] args) {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        System.out.println(siftUp(sample, 5)); // 1
    }
}