package lecture.section08;

import common.CodingTest;

import java.util.LinkedList;
import java.util.Queue;

// 07. 이진트리 레벨탐색(BFS: Breadth-First Search)
public class Section08Test07 extends CodingTest {

    private class Node {
        int data;
        Node lt;
        Node rt;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node getTree() {

        Node root = new Node(1);

        root.lt = new Node(2);
        root.rt = new Node(3);

        Node nodeNum2 = root.lt;
        Node nodeNum3 = root.rt;

        nodeNum2.lt = new Node(4);
        nodeNum2.rt = new Node(5);

        nodeNum3.lt = new Node(6);
        nodeNum3.rt = new Node(7);

        return root;
    }

    @Override
    public void addCases() {

        testCases.add(() -> BFS(getTree()));
    }

    private void BFS(Node root) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.data + " ");

            if(node.lt != null) {
                queue.add(node.lt);
            }

            if(node.rt != null) {
                queue.add(node.rt);
            }
        }
    }
}
