import dataStructureAndAlgotithems.linkedTable.LinkedNodes;
import dataStructureAndAlgotithems.linkedTable.SingleNode;
import org.junit.Test;

public class LinkedElements {

    @Test(expected = IllegalArgumentException.class)
    public void initializeLinkedNodes() {
        SingleNode node1 = null;
        LinkedNodes table = LinkedNodes.LinkNodesWith(node1);
        table.walkThrough();
    }

    @Test
    public void workThroughLinkedTable() {
        SingleNode node1 = new SingleNode(1);
        SingleNode node2 = new SingleNode(2);
        LinkedNodes table = LinkedNodes.LinkNodesWith(node1);
        table.add(node2);
        table.walkThrough();
    }

    @Test
    public void invertTable() {
        SingleNode node1 = new SingleNode(1);
        SingleNode node2 = new SingleNode(2);
        SingleNode node3 = new SingleNode(3);
        SingleNode node4 = new SingleNode(4);
        SingleNode node5 = new SingleNode(5);
        LinkedNodes table = LinkedNodes.LinkNodesWith(node1);
        table.add(node2);
        table.add(node3);
        table.add(node4);
        table.add(node5);
        table.walkThrough();
        table.invert();
        table.walkThrough();
    }

    @Test
    public void invertTableAtRandomNode() {
        SingleNode node1 = new SingleNode(1);
        SingleNode node2 = new SingleNode(2);
        SingleNode node3 = new SingleNode(3);
        SingleNode node4 = new SingleNode(4);
        SingleNode node5 = new SingleNode(5);
        LinkedNodes table = LinkedNodes.LinkNodesWith(node1);
        table.add(node2);
        table.add(node3);
        table.add(node4);
        table.add(node5);
        table.walkThrough();
        table.invertFrom(node2);
        table.walkThrough();
    }

}
