package sp5.trees.finals;

/**
 * https://contest.yandex.ru/contest/24810/run-report/69026834/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Ищем удаляемую ноду, спускаясь вниз по дереву. Если у найденной ноды только один ребенок – заменяемый удаляемую
 * ноду на ребенка. Если ребенка два – заменим удаляемую ноду на самую левую ноду правого поддерева.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * O(h), где h – высота дерева. Для сбалансированного дерева эквивалентно O(logN). Для вырожденного – O(N)
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(h) – на поддержание стека рекурсии
 */
public class BinaryTreeDelete {

    public static Node remove(Node root, int key) {
        if (root == null) {
            // tree is empty
            return null;
        }

        if (root.value > key) {
            return deleteInLeftSubtree(root, key);
        } else if (root.value < key) {
            return deleteInRightSubtree(root, key);
        } else {
            return deleteRoot(root);
        }
    }

    private static Node deleteInLeftSubtree(Node root, int key) {
        Node newLeftSubtree = remove(root.left, key);
        root.left = newLeftSubtree;
        return root;
    }

    private static Node deleteInRightSubtree(Node root, int key) {
        Node newRightSubtree = remove(root.right, key);
        root.right = newRightSubtree;
        return root;
    }

    private static Node deleteRoot(Node root) {
        if (root.left == null) {
            return root.right;
        }

        if (root.right == null) {
            return root.left;
        }

        int leftmostValueOfRightSubtree = getLeftmostValue(root.right);
        root.value = leftmostValueOfRightSubtree;

        return deleteInRightSubtree(root, leftmostValueOfRightSubtree);
    }

    private static int getLeftmostValue(Node current) {
        if (current.left == null) {
            return current.value;
        }

        return getLeftmostValue(current.left);
    }

    private static class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }
}
