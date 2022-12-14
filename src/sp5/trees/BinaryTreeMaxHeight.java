package sp5.trees;

/**
 * Алла хочет побывать на разных островах архипелага Алгосы. Она составила карту. Карта представлена в виде дерева:
 * корень обозначает центр архипелага, узлы –— другие острова. А листья —– это дальние острова, на которые Алла хочет попасть.
 * Помогите Алле определить максимальное число островов, через которые ей нужно пройти для совершения одной поездки
 * от стартового острова до места назначения, включая начальный и конечный пункты.
 */
public class BinaryTreeMaxHeight {

    public static int maxHeight(Node head) {
        if (head == null) return 0;

        return Math.max(
                height(head.left, 1),
                height(head.right, 1));
    }

    private static int height(Node subtree, int height) {
        if (subtree == null) {
            return height;
        }

        height += 1;

        return Math.max(
                height(subtree.left, height),
                height(subtree.right, height));
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);

        System.out.println(maxHeight(node5)); // 3
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
