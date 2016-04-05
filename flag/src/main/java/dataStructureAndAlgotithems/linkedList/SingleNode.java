package dataStructureAndAlgotithems.linkedList;

public class SingleNode {
    public int itsValue;

    public SingleNode next;

    public SingleNode(int value) {
        itsValue = value;
    }

    public boolean hasNext() {
        return (next != null);
    }
}
