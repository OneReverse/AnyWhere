package dataStructureAndAlgotithems.linkedTable;

import java.util.LinkedList;

public class Node {
    public Node leftChild;
    public Node rightChild;
    public int value;


    public void append(Node childNode) {
        if (leftChild == null)
            leftChild = childNode;
        else if (rightChild == null)
            rightChild = childNode;
        else
            ;
    }


}
