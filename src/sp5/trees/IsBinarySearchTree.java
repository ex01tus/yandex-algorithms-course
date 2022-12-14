package sp5.trees;

/**
 * Гоша понял, что такое дерево поиска, и захотел написать функцию, которая определяет, является ли заданное дерево деревом поиска.
 * Значения в левом поддереве должны быть строго меньше, в правом —- строго больше значения в узле.
 * Помогите Гоше с реализацией этого алгоритма.
 */
public class IsBinarySearchTree {

    public static boolean isBst(Node current, Node min, Node max) {
        if (current == null) return true;
        if (min != null && current.value <= min.value) return false;
        if (max != null && current.value >= max.value) return false;

        return isBst(current.left, min, current)
                && isBst(current.right, current, max);
    }

    public static boolean isBst(Node head) {
        return isBst(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBst(Node current, int min, int max) {
        if (current == null) return true;
        if (current.value < min || current.value > max) return false;

        return isBst(current.left, min, current.value - 1)
                && isBst(current.right, current.value + 1, max);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);

        System.out.println(isBst(node5)); // true
        System.out.println(isBst(node5, null, null)); // true
        node2.value = 5;
        System.out.println(isBst(node5)); // false
        System.out.println(isBst(node5, null, null)); // false
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
