package sp5.trees;

import java.io.IOException;

public class BinarySearchTreeFirstGreaterThan {

    public static int findFirstGreaterThan(Node current, int value, Node leftSubtreeRoot) {
        if (current == null) return leftSubtreeRoot.value >= value ? leftSubtreeRoot.value : -1;

        if (value <= current.value) {
            return findFirstGreaterThan(current.left, value, current);
        } else {
            return findFirstGreaterThan(current.right, value, leftSubtreeRoot);
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

        int a = findFirstGreaterThan(node7, 3, node7);
        System.out.println(a); // 5
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