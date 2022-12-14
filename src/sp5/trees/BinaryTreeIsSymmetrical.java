package sp5.trees;

import java.util.Optional;

/**
 * Гоша и Алла играют в игру «Удивительные деревья». Помогите ребятам определить, является ли дерево,
 * которое им встретилось, деревом-анаграммой?
 * Дерево называется анаграммой, если оно симметрично относительно своего центра.
 */
public class BinaryTreeIsSymmetrical {

    public static boolean isSymmetrical(Node root) {
        return isSymmetrical(root.left, root.right);
    }

    private static boolean isSymmetrical(Node leftSubtree, Node rightSubtree) {
        if (leftSubtree == null && rightSubtree == null) {
            return true;
        }

        if (leftSubtree == null || rightSubtree == null) {
            return false;
        }

        if (leftSubtree.value != rightSubtree.value
                || value(leftSubtree.left) != value(rightSubtree.right)
                || value(leftSubtree.right) != value(rightSubtree.left)) {
            return false;
        }

        return isSymmetrical(leftSubtree.left, rightSubtree.right)
                && isSymmetrical(leftSubtree.right, rightSubtree.left);
    }

    private static int value(Node node) {
        return Optional.ofNullable(node).map(n -> n.value).orElse(-1);
    }


    public static void main(String[] args) {
//        Node node1 = new Node(3, null, null);
//        Node node2 = new Node(4, null, null);
//        Node node3 = new Node(4, null, null);
//        Node node4 = new Node(3, null, null);
//        Node node5 = new Node(2, node1, node2);
//        Node node6 = new Node(2, node3, node4);
//        Node node7 = new Node(1, node5, node6);

        Node node1 = new Node(3,  null,  null);
        Node node4 = new Node(3,  null,  null);
        Node node5 = new Node(2, node1, null);
        Node node6 = new Node(2, null, node4);
        Node node7 = new Node(0, node5, node6);

        System.out.println(isSymmetrical(node7)); // true
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
