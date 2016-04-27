package dataStructureAndAlgotithems.binaryTree;
import java.util.List;

public class BinaryTree {
    public TreeNode root;

    private BinaryTree() {}

    public static BinaryTree generateBinaryTreeWith(List<Integer> values) {
        BinaryTree tree = new BinaryTree();
        TreeNode current = tree.root;
        values.forEach(e -> tree.append(e));
        return tree;
    }

    private void append(Integer value) {
        TreeNode node = new TreeNode(value);
        TreeNode current = root;
        if (current.leftChild == null)
            current.leftChild = node;
        else if (current.rightChild == null)
            current.rightChild = node;
    }
}
