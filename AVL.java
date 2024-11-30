import java.util.ArrayList;

public class AVL {
    private class Node {
        int key;
        int height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1; // Initial height
        }
    }

    private Node root;

    // Insert a key
    public void insert(int key) {
        root = insertHelper(root, key);
    }

    // Private helper method for insertion
    private Node insertHelper(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insertHelper(node.left, key);
        else if (key > node.key)
            node.right = insertHelper(node.right, key);
        else
            return node;

        setHeight(node);
        return balance(node);
    }

    // Delete a key
    public void delete(int key) {
        root = deleteHelper(root, key);
    }

    // Private helper method for deletion
    private Node deleteHelper(Node node, int key) {
        if (node == null)
            return null;

        if (key < node.key)
            node.left = deleteHelper(node.left, key);
        else if (key > node.key)
            node.right = deleteHelper(node.right, key);
        else {
            // Node to be deleted found
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                //Replace the key to be deleted with its successor
                Node successor = findMin(node.right);
                node.key = successor.key;
                node.right = deleteHelper(node.right, successor.key);
            }
        }

        if (node == null)
            return null;

        setHeight(node);
        return balance(node);
    }

    // Return a boolean for searching a key
    public boolean search(int key) {
        return searchHelper(root, key);
    }

    // Private helper method for searching
    private boolean searchHelper(Node node, int key) {
        if (node == null)
            return false;
        if (key == node.key)
            return true;
        else if (key < node.key)
            return searchHelper(node.left, key);
        else
            return searchHelper(node.right, key);
    }

    // Return an integer array with in-order traversal
    public ArrayList<Integer> inOrderTraversal() {
        ArrayList<Integer> result = new ArrayList<>();
        inOrderHelper(root, result);
        return result;
    }

    // Private helper method for in-order traversal
    private void inOrderHelper(Node node, ArrayList<Integer> result) {
        if (node != null) {
            inOrderHelper(node.left, result);
            result.add(node.key);
            inOrderHelper(node.right, result);
        }
    }

    // Right rotation
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node temp = x.right;

        x.right = y;
        y.left = temp;

        setHeight(y);
        setHeight(x);

        return x;
    }

    // Left rotation
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node temp = y.left;

        y.left = x;
        x.right = temp;

        setHeight(x);
        setHeight(y);

        return y;
    }

    // Get the height of a node
    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // Set the height of a node
    private void setHeight(Node node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    // Calculate the balanceHelper factor
    private int getBalanceFactor(Node node) {
        return (node == null) ?
                0 : getHeight(node.left) - getHeight(node.right);
    }

    // Balance a node
    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        // Left-Left Case
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rotateRight(node);

        // Left-Right Case
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right-Right Case
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return rotateLeft(node);

        // Right-Left Case
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Find the minimum node in a subtree
    private Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

}

