package sp2.basic.data.structures;

/**
 * Вася решил запутать маму —– делать дела в обратном порядке. Список его дел теперь хранится в двусвязном списке.
 * Напишите функцию, которая вернёт список в обратном порядке.
 */
public class LinkedListReverse {

    public static class Node<V> {
        public V value;
        public Node<V> next;
        public Node<V> prev;

        public Node(V value, Node<V> next, Node<V> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    public static Node<String> reverseDoublyLinked(Node<String> head) {
        if (head.next == null) {
            return head;
        }

        Node<String> tmp = null;
        while (head != null) {
            tmp = head.prev;
            head.prev = head.next;
            head.next = tmp;

            head = head.prev;
        }

        return tmp.prev;
    }

    public static Node<String> reverseSinglyLinked(Node<String> head) {
        Node<String> prev = null;
        while (head != null) {
            Node<String> tmp = head.next;
            head.next = prev;
            prev = head;

            head = tmp;
        }

        return prev;
    }

    public static void main(String[] args) {
        Node<String> node3 = new Node<>("node3", null, null);
        Node<String> node2 = new Node<>("node2", node3, null);
        Node<String> node1 = new Node<>("node1", node2, null);
        Node<String> node0 = new Node<>("node0", node1, null);
        node1.prev = node0;
        node2.prev = node1;
        node3.prev = node2;
        Node<String> newNode = reverseDoublyLinked(node0);

        while (newNode != null) {
            System.out.println(newNode.value);
            newNode = newNode.next;
        }
    }
}