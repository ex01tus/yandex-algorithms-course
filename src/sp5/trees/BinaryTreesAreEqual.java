package sp5.trees;

import java.util.Optional;

/**
 * Гоше на день рождения подарили два дерева. Тимофей сказал, что они совершенно одинаковые.
 * Но, по мнению Гоши, они отличаются.
 * Помогите разрешить этот философский спор!
 */
public class BinaryTreesAreEqual {

    public static boolean areEqual(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return true;
        }

        if (head1 == null || head2 == null) {
            return false;
        }

        if (head1.value != head2.value
                || value(head1.left) != value(head2.left)
                || value(head1.right) != value(head2.right)) {
            return false;
        }

        return areEqual(head1.left, head2.left)
                && areEqual(head1.right, head2.right);
    }

    private static int value(Node node) {
        return Optional.ofNullable(node).map(n -> n.value).orElse(-1);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1,  null,  null);
        Node node2 = new Node(2,  null,  null);
        Node node3 = new Node(3,  node1,  node2);

        Node node4 = new Node(1,  null,  null);
        Node node5 = new Node(2,  null,  null);
        Node node6 = new Node(3,  node4,  node5);

        System.out.println(areEqual(node3, node6)); // true
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
