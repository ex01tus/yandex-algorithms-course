package sp3.recursion.and.sorting.finals;

/**
 * Алла ошиблась при копировании из одной структуры данных в другую. Она хранила массив чисел в кольцевом буфере.
 * Массив был отсортирован по возрастанию, и в нём можно было найти элемент за логарифмическое время.
 * Алла скопировала данные из кольцевого буфера в обычный массив, но сдвинула данные исходной отсортированной последовательности.
 * Теперь массив не является отсортированным. Тем не менее, нужно обеспечить возможность находить в нем элемент за O(logN).
 * Можно предполагать, что в массиве только уникальные элементы.
 *
 * Функция принимает массив натуральных чисел и искомое число k. Длина массива не превосходит 10000. Элементы массива и число
 * k не превосходят по значению 10000.
 *
 * Функция должна вернуть индекс элемента, равного k, если такой есть в массиве (нумерация с нуля).
 * Если элемент не найден, функция должна вернуть -1.
 * Изменять массив нельзя.
 *
 * https://contest.yandex.ru/contest/23815/run-report/68368503/
 *
 * Решил задачу без использования рекурсии, а потом вспомнил, что у нас посвященный рекурсии спринт
 * и решил с использованием рекурсии. Прикладываю оба варианта.
 *
 * -- ПРИНЦИП РАБОТЫ --
 * С помощью слегка модифицированного бинарного поиска ищем позицию "переломного" элемента `pivot`.
 * Более формально: такой `pivot`, для которого оба массива [0; pivot) и [pivot; arr.length) – отсортированные.
 * Получаем два отсортированных массива, для каждого из которых можно применить классический бинарный поиск.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Для функции `findPivot()`:
 *  а) если arr[mid] > arr[left], то массив [left; mid] – отсортированный. Ищем "перелом" справа от него.
 *  б) иначе массив [left; mid] – неотсортированный. Ищем "перелом" внутри.
 * Для функции `findElement()`:
 *  а) если arr[mid] > k, то для отсортированного массива `k` лежит в интервале [left; mid)
 *  б) иначе (mid; right]
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * O(logN) – в худшем случае последовательно будут выполнены два бинарных поиска: первый – для поиска `pivot`,
 * второй – для поиска индекса искомого элемента `k`. Каждый работает за O(logN) – на каждой итерации массив
 * делится пополам – получаем O(2logN) -> O(logN).
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(1) – дополнительные структуры данных не задействуются, память выделяется только на вспомогательные переменные,
 * количество которых не привязано к размеру входных данных.
 */
public class BrokenArrayBinarySearch {

    public static int brokenSearch(int[] arr, int k) {
        int pivot = findPivot(arr);
        if (arr[pivot] == k) return pivot;

        return (k > arr[pivot] && k <= arr[arr.length - 1])
                ? findElement(arr, pivot, arr.length - 1, k)
                : findElement(arr, 0, pivot - 1, k);
    }

    private static int findPivot(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left != right) {
            int mid = (left + right) / 2;

            if (arr[mid] >= arr[left]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static int findElement(int[] arr, int left, int right, int key) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == key) {
                return mid;
            }

            if (arr[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
