import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void testRotations() {
        AVL tree = new AVL();

        // Test Left Rotation
        tree.insert(1);
        tree.insert(2);
        tree.insert(3); // Causes left rotation
        List<Integer> expectedLeft = List.of(1, 2, 3);
        assertEquals(expectedLeft, tree.inOrderTraversal(), "Left Rotation Failed");

        // Reset tree
        tree = new AVL();

        // Test Right Rotation
        tree.insert(3);
        tree.insert(2);
        tree.insert(1); // Causes right rotation
        List<Integer> expectedRight = List.of(1, 2, 3);
        assertEquals(expectedRight, tree.inOrderTraversal(), "Right Rotation Failed");

        // Reset tree
        tree = new AVL();

        // Test Left-Right Rotation
        tree.insert(3);
        tree.insert(1);
        tree.insert(2); // Causes left-right rotation
        List<Integer> expectedLeftRight = List.of(1, 2, 3);
        assertEquals(expectedLeftRight, tree.inOrderTraversal(), "LR Rotation Failed");

        // Reset tree
        tree = new AVL();

        // Test Right-Left Rotation
        tree.insert(1);
        tree.insert(3);
        tree.insert(2); // Causes right-left rotation
        List<Integer> expectedRightLeft = List.of(1, 2, 3);
        assertEquals(expectedRightLeft, tree.inOrderTraversal(), "RL Rotation Failed");
    }

    @Test
    void testDeletion() {
        AVL tree = new AVL();

        // Insert elements
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        List<Integer> expectedInitial = List.of(2, 3, 4, 5, 6, 7, 8);
        assertEquals(expectedInitial, tree.inOrderTraversal(), "Initial tree structure incorrect");

        // Delete a leaf node: 2
        tree.delete(2);
        List<Integer> expectedAfterLeafDeletion = List.of(3, 4, 5, 6, 7, 8);
        assertEquals(expectedAfterLeafDeletion, tree.inOrderTraversal(), "Leaf deletion failed");

        // Deleting a node with one child: 3
        tree.delete(3);
        List<Integer> expectedAfterOneChildDeletion = List.of(4, 5, 6, 7, 8);
        assertEquals(expectedAfterOneChildDeletion, tree.inOrderTraversal(), "Single-child deletion failed");

        // Delete a node with two children: 7
        tree.delete(7);
        List<Integer> expectedAfterTwoChildrenDeletion = List.of(4, 5, 6, 8);
        assertEquals(expectedAfterTwoChildrenDeletion, tree.inOrderTraversal(), "Two-children deletion failed");

        // Deleting a non-existent key: 10
        tree.delete(10); // No changes expected
        List<Integer> expectedAfterNonExistentDeletion = List.of(4, 5, 6, 8);
        assertEquals(expectedAfterNonExistentDeletion, tree.inOrderTraversal(), "Non-existent key deletion failed");

        // Test rebalancing after deletion
        tree.delete(5); // Causes rebalance
        List<Integer> expectedAfterRebalance = List.of(4, 6, 8);
        assertEquals(expectedAfterRebalance, tree.inOrderTraversal(), "Rebalancing after deletion failed");
    }

    @Test
    void testInsertingDuplicateKeys() {
        AVL tree = new AVL();
        tree.insert(10);
        tree.insert(10);

        List<Integer> expected = List.of(10);
        assertEquals(expected, tree.inOrderTraversal());
    }


    @Test
    void testSearch() {
        AVL tree = new AVL();
        tree.insert(10);
        tree.insert(20);

        assertTrue(tree.search(10));
        assertTrue(tree.search(20));
        assertFalse(tree.search(30));
    }

    @Test
    void testLargeTree() {
        AVL tree = new AVL();
        int[] keys = {50, 25, 75, 10, 30, 60, 80, 5, 15, 28, 55, 65};

        for (int key : keys) {
            tree.insert(key);
        }

        assertTrue(tree.search(25));
        assertTrue(tree.search(80));
        assertFalse(tree.search(100));

        List<Integer> expected = List.of(5, 10, 15, 25, 28, 30, 50, 55, 60, 65, 75, 80);
        assertEquals(expected, tree.inOrderTraversal());
    }
}
