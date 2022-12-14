package sp5.trees;

/**
 * Гоше очень понравилось слушать рассказ Тимофея про деревья. Особенно часть про сбалансированные деревья.
 * Он решил написать функцию, которая определяет, сбалансировано ли дерево.
 * Дерево считается сбалансированным, если левое и правое поддеревья каждой вершины отличаются по высоте не больше, чем на единицу.
 */
public class BinaryTreeIsBalanced {

    public static boolean isBalanced(Node subtree) {
        if (subtree == null) return true;

        if (Math.abs(height(subtree.left, 0) - height(subtree.right, 0)) > 1) {
            return false;
        }

        return isBalanced(subtree.right) && isBalanced(subtree.left);
    }

    private static int height(Node subtree, int height) {
        if (subtree == null) {
            return height;
        }

        height += 1;

        return Math.max(height(subtree.left, height), height(subtree.right, height));
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;

        System.out.println(isBalanced(node5)); // true
    }

    private static class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
