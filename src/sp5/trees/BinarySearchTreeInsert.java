package sp5.trees;

/**
 * Дано BST. Надо вставить узел с заданным ключом. Ключи в дереве могут повторяться.
 * На вход функции подаётся корень корректного бинарного дерева поиска и ключ, который надо вставить в дерево.
 * Осуществите вставку этого ключа. Если ключ уже есть в дереве, то его дубликаты уходят в правого сына.
 * Таким образом вид дерева после вставки определяется однозначно.
 *
 * Функция должна вернуть корень дерева после вставки вершины.
 */
public class BinarySearchTreeInsert {

    public static Node insert(Node root, int key) {
        insertKey(root, key);
        return root;
    }

    public static void insertKey(Node current, int key) {
        if (current.value > key) {
            insertLeft(current, key);
        } else {
            insertRight(current, key);
        }
    }

    private static void insertLeft(Node current, int key) {
        if (current.left != null) {
            insertKey(current.left, key);
        } else {
            current.left = new Node( null, null, key);
        }
    }

    private static void insertRight(Node current, int key) {
        if (current.right != null) {
            insertKey(current.right, key);
        } else {
            current.right = new Node(null, null, key);
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(null, null, 7);
        Node node2 = new Node(node1, null, 8);
        Node node3 = new Node(null, node2, 7);
        Node newHead = insert(node3, 6);

        System.out.println(newHead.value); // 7
        System.out.println(newHead.getLeft().getValue()); // 6
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}