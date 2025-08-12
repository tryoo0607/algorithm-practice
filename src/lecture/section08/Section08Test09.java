package lecture.section08;

import common.CodingTest;

import java.util.LinkedList;
import java.util.Queue;

// 09. Tree 말단 노드까지의 가장 짧은 경로(DFS)
public class Section08Test09 extends CodingTest {

    private static int result = 999999;

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
        testCases.add(this::createCase);

        // 올바른 풀이
        testCases.add(this::createCase2);
    }

    private void createCase() {
        // 초기화
        result = 99999999;

        DFS(getTree(), 0);

        System.out.println(result);
    }

    private void createCase2() {
        // 초기화
        result = 99999999;

        DFS2(getTree(), 0);

        System.out.println(result);
    }

    private void  DFS(Node node, int level) {
        if(node == null) return;

        if(level != 0) result = Math.min(level, result);
        if(node.lt != null) DFS(node.lt, level + 1);
        if(node.rt != null) DFS(node.rt, level + 1);
    }

    private void  DFS2(Node node, int level) {
        if(node == null) return;

        // 즉, 더 깊이 들어가면 굳이 실행 안해도 됨
        if(level >= result) return;

        // 더 이상 내려갈 곳이 없음 => 여기가 최단
        if (node.lt == null && node.rt == null) {
            result = Math.min(result, level);
            return;
        }

        DFS2(node.lt, level + 1);
        DFS2(node.rt, level + 1);
    }
}
