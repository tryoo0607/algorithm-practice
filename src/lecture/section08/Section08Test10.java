package lecture.section08;

import common.CodingTest;

import java.util.LinkedList;
import java.util.Queue;

// 10. Tree 말단 노드까지의 가장 짧은 경로(BFS)
public class Section08Test10 extends CodingTest {

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

        Node nodeLt = root.lt;
        nodeLt.lt = new Node(4);
        nodeLt.rt = new Node(5);

        return root;
    }

    @Override
    public void addCases() {

        // 잘못된 풀이
        testCases.add(() -> BFS(getTree()));

        // 올바른 풀이
        testCases.add(() -> BFS2(getTree()));
    }

    // ❌ 잘못된 풀이
    // null을 큐에 넣고, poll() 결과가 null이면 return해서
    // 아직 남아있는 노드들이 있어도 탐색이 중단됨
    private void BFS(Node tree) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(tree);

        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i=0; i< size; i++) {
                Node node = queue.poll();
                if(node == null) return;

                if(node.lt == null && node.rt == null) {
                    System.out.println(level);
                    return;
                }

                queue.add(node.lt);
                queue.add(node.rt);
            }

            level++;
        }

    }

    // ✅ 올바른 풀이
    // null을 큐에 넣지 않고, 처음 만나는 리프의 level을 바로 반환하므로
    // 항상 최단 거리를 보장
    private void BFS2(Node tree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(tree);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for(int i=0; i < size; i++) {
                Node node = queue.poll();
                if (node.lt == null && node.rt == null) {
                    System.out.println(level);
                    return;
                }

                if (node.lt != null) { queue.add(node.lt); }
                if (node.rt != null) { queue.add(node.rt); }
            }

            level++;
        }

        System.out.println(level);
    }
}
