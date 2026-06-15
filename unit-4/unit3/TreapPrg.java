
class Treap {

    static class Node {
        int key, priority;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.priority = (int)(Math.random() * 100000);
            this.left = this.right = null;
        }
    }

    Node root;

    // 🔁 Right Rotation
    Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        return x;
    }

    // 🔁 Left Rotation
    Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        return y;
    }

    // 🌱 Insert
    Node insert(Node root, int key) {
        if (root == null) return new Node(key);
        if (key < root.key) {
            root.left = insert(root.left, key);
            if (root.left.priority > root.priority)
                root = rotateRight(root);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
            if (root.right.priority > root.priority)
                root = rotateLeft(root);
        }

        return root;
    }

    void insert(int key) {
        root = insert(root, key);
    }

    // 🔍 Search
    boolean search(Node root, int key) {
        if (root == null) return false;

        if (key == root.key) return true;
        else if (key < root.key) return search(root.left, key);
        else return search(root.right, key);
    }

    boolean search(int key) {
        return search(root, key);
    }

    // ❌ Delete
    Node delete(Node root, int key) {
        if (root == null) return null;

        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            // Node found

            // Case 1: One or no child
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            // Case 2: Two children → rotate down
            if (root.left.priority > root.right.priority) {
                root = rotateRight(root);
                root.right = delete(root.right, key);
            } else {
                root = rotateLeft(root);
                root.left = delete(root.left, key);
            }
        }

        return root;
    }

    void delete(int key) {
        root = delete(root, key);
    }

    // 🌿 Inorder Traversal (sorted order)
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    void printInorder() {
        inorder(root);
        System.out.println();
    }
}

public class TreapPrg {
    public static void main(String[] args) {
        Treap treap = new Treap();

        // Insert
        treap.insert(50);
        treap.insert(30);
        treap.insert(20);
        treap.insert(40);
        treap.insert(70);
        treap.insert(60);
        treap.insert(80);

        System.out.print("Inorder after insertion: ");
        treap.printInorder();

        // Search
        System.out.println("Search 40: " + treap.search(40));
        System.out.println("Search 100: " + treap.search(100));

        // Delete
        treap.delete(20);
        System.out.print("After deleting 20: ");
        treap.printInorder();

        treap.delete(50);
        System.out.print("After deleting 50: ");
        treap.printInorder();
    }
}