package sp2.basic.data.structures;

/**
 * Вася размышляет, что ему можно не делать из того списка дел, который он составил.
 * Но, кажется, все пункты очень важные! Вася решает загадать число и удалить дело, которое идёт под этим номером.
 * Список дел представлен в виде односвязного списка. Напишите функцию solution, которая принимает на вход голову списка
 * и номер удаляемого дела и возвращает голову обновлённого списка.
 */
public class LinkedListRemove {

    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static Node<String> remove(Node<String> head, int idx) {
        if (idx == 0) {
            return head.next;
        }

        Node<String> prev = findByIndex(head, idx - 1);
        prev.next = prev.next.next;

        return head;
    }

    private static Node<String> findByIndex(Node<String> head, int idx) {
        for (int i = 0; i < idx; i++) {
            head = head.next;
        }

        return head;
    }

    public static void main(String[] args) {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);

        Node<String> newHead = remove(node0, 1);
        while (newHead != null) {
            System.out.println(newHead.value);
            newHead = newHead.next;
        }
    }
}

