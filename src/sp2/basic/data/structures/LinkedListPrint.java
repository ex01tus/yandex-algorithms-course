package sp2.basic.data.structures;

public class LinkedListPrint {

    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void print(Node<String> head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    private static void main(String[] args) {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);

        print(node0);
    }
}


