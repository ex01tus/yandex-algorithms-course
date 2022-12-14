package sp5.trees;

/**
 * Гоша повесил на стену гирлянду в виде бинарного дерева, в узлах которого находятся лампочки.
 * У каждой лампочки есть своя яркость. Уровень яркости лампочки соответствует числу, расположенному в узле дерева.
 * Помогите Гоше найти самую яркую лампочку в гирлянде, то есть такую, у которой яркость наибольшая.
 */
public class BinaryTreeMax {

    public static int max(Node current) {
        int max = current.value;

        if (current.left != null) {
            max = Math.max(max(current.left), max);
        }

        if (current.right != null) {
            max = Math.max(max(current.right), max);
        }

        return max;
    }

    public static int max(Node current, int max) {
        if (current == null) return max;

        max = Math.max(current.value, max);

        return Math.max(
                max(current.left, max),
                max(current.right, max));
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        System.out.println(max(node4)); // 3
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
