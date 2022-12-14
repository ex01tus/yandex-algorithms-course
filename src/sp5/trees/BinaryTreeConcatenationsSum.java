package sp5.trees;

/**
 * Вася и его друзья решили поиграть в игру. Дано дерево, в узлах которого записаны цифры от 0 до 9.
 * Таким образом, каждый путь от корня до листа содержит число, получившееся конкатенацией цифр
 * пути (склеиванием цифр пути в одно число). Нужно найти сумму всех таких чисел в дереве.
 * Гарантируется, что ответ не превосходит 20 000.
 */
public class BinaryTreeConcatenationsSum {

    private static int sum(Node current, String prefix) {
        if (current == null) {
            // dead end
            return 0;
        }

        prefix += current.value;

        if (current.left == null && current.right == null) {
            // leaf
            return Integer.parseInt(prefix);
        }

        return sum(current.left, prefix)
                + sum(current.right, prefix);
    }

    public static void main(String[] args) {
        Node node0 = new Node(4, null, null);
        Node node1 = new Node(2, node0, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(1, node4, node3);

        System.out.println(sum(node5, "")); // 275
    }

    private static class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}

