package sp2.basic.data.structures;

/**
 * Мама Васи хочет знать, что сын планирует делать и когда. Помогите ей: напишите функцию solution, определяющую индекс
 * первого вхождения передаваемого ей на вход значения в связном списке, если значение присутствует.
 */
public class LinkedListSearch {

    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static int search(Node<String> head, String elem) {
        int idx = 0;

        while (head != null) {
            if (head.value.equals(elem)) return idx;
            head = head.next;
            idx++;
        }

        return -1;
    }

    public static void main(String[] args) {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);

        int idx = search(node0, "node3");
        System.out.println(idx);
    }
}
