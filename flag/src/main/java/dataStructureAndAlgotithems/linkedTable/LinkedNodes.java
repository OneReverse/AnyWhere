package dataStructureAndAlgotithems.linkedTable;

public class LinkedNodes {
    private SingleNode root;

    private LinkedNodes() {}

    public static LinkedNodes LinkNodesWith(SingleNode rootNode) {
        if (rootNode == null)
            throw new IllegalArgumentException("root node mustn't be null");
        LinkedNodes linkedTable = new LinkedNodes();
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
        SingleNode copyOfStart = startNode;
        invert(startNode);
        copyOfStart.next = null;
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
