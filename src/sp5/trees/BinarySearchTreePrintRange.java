package sp5.trees;

import java.io.IOException;

/**
 * Напишите функцию, которая будет выводить по неубыванию все ключи от min to max включительно в заданном бинарном дереве поиска.
 * Ключи в дереве могут повторяться. Решение должно иметь сложность
 * В данной задаче если в узле содержится ключ x, то другие ключи, равные x, могут быть как в правом, так и в левом поддереве данного узла.
 *
 * Time complexity: O(H + k) -> O(logN + k), где
 * H – высота дерева, N – количество элементов в дереве, k – количество элементов в диапазоне
 */
public class BinarySearchTreePrintRange {

    // LMR – inorder traversal
    private static void printRange(Node current, int min, int max) {
        if (current.left != null && current.value >= min) {
            printRange(current.left, min, max);
        }

        if (current.value >= min && current.value <= max) {
            System.out.println(current.value);
        }

        if (current.right != null && current.value <= max) {
            printRange(current.right, min, max);
        }
    }

    // RML – reverse order traversal
    public static void printRangeReversed(Node current, int min, int max) {
        if (current.right != null && current.value < max) {
            printRangeReversed(current.right, min, max);
        }

        if (current.value >= min && current.value <= max) {
            System.out.println(current.value);
        }

        if (current.left != null && current.value > min) {
            printRangeReversed(current.left, min, max);
        }
    }

    public static void main(String[] args) throws IOException {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(null, node1, 1);
        Node node3 = new Node(null, null, 8);
        Node node4 = new Node(null, node3, 8);
        Node node5 = new Node(node4, null, 9);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node2, node6, 5);

        printRange(node7, 2, 8); // 2 5 8 8
        printRangeReversed(node7, 2, 8); // 8 8 5 2
    }

    private static class Node {

        public int value;
        public Node left;
        public Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }
}