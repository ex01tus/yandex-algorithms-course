package sp5.trees;

/**
 * Напишите функцию, совершающую просеивание вниз в куче на максимум. Гарантируется, что порядок элементов в куче
 * может быть нарушен только элементом, от которого запускается просеивание.
 * Функция принимает в качестве аргументов массив, в котором хранятся элементы кучи, и индекс элемента,
 * от которого надо сделать просеивание вниз. Функция должна вернуть индекс, на котором элемент оказался после просеивания.
 * Также необходимо изменить порядок элементов в переданном в функцию массиве.
 * Обратите внимание, что нулевой элемент в передаваемом массиве фиктивный, вершина кучи соответствует 1-му элементу.
 */
public class BinaryHeapSiftDown {

    public static int siftDown(int[] heap, int idx) {
        int left = 2 * idx;
        int right = 2 * idx + 1;

        if (left >= heap.length) {
            // no children
            return idx;
        }

        int newIdx;
        if (right < heap.length && heap[right] > heap[left]) {
            // both children
            newIdx = right;
        } else {
            // only left child
            newIdx = left;
        }

        if (heap[idx] > heap[newIdx]) {
            // no swap needed
            return idx;
        }

        int tmp = heap[newIdx];
        heap[newIdx] = heap[idx];
        heap[idx] = tmp;
        return siftDown(heap, newIdx);
    }

    public static void main(String[] args) {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        System.out.println(siftDown(sample, 2)); // 5
    }
}