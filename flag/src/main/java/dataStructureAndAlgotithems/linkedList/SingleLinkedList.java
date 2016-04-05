package dataStructureAndAlgotithems.linkedList;

public class SingleLinkedList {
    private SingleNode root;

    private SingleLinkedList() {}

    public static SingleLinkedList LinkNodesWith(SingleNode rootNode) {
        if (rootNode == null)
            throw new IllegalArgumentException("root node mustn't be null");
        SingleLinkedList linkedTable = new SingleLinkedList();
        linkedTable.root = rootNode;
        return linkedTable;
    }

    public void add(SingleNode newNode) {
        SingleNode last = findLastNode();
        last.next = newNode;
    }

    private SingleNode findLastNode() {
        SingleNode node = root;
        while (node.hasNext()) {
            node = node.next;
        }
        return node;
    }

    public void walkThrough() {
        walkThroughFrom(root);
    }

    private void walkThroughFrom(SingleNode current) {
        if (current != null) {
            System.out.printf(current.itsValue + ", ");
            current = current.next;
            walkThroughFrom(current);
        }
    }

    public void invert() {
        invertFrom(root);
    }

    public void invertFrom(SingleNode startNode) {
        invert(startNode);
        startNode.next = null;
    }

    private void invert(SingleNode node) {
        SingleNode nextNode;
        if (node.hasNext()) {
            nextNode = node.next;
            if (nextNode.hasNext()) {
                invert(nextNode);
                // time sequence counts
                nextNode.next = node;
            }
            else {
                nextNode.next = node;
                root = nextNode;
            }
        }
    }

}
