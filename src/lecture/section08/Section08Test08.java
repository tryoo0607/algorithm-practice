package lecture.section08;

import common.CodingTest;

import java.util.*;

// 08. 송아지 찾기 1(BFS : 상태 트리 탐색)
// 이 풀이의 경우 무한루프 날 가능성이 매우 높음(ex. 1, -1 반복)
public class Section08Test08 extends CodingTest {

    private final int S = 5;
    private final int E = 14;

    private class Node {
        int sum;
        int level;
        List<Node> subNodes = new ArrayList<>();

        public Node(int level, int sum) {
            this.level = level;
            this.sum = sum;
        }
    }

    private void setSubNode(Node node) {
        List<Integer> jumpList = new ArrayList<>(Arrays.asList(1, -1, 5));

        for (Integer jump : jumpList) {
            Node subNode = new Node(node.level + 1, node.sum + jump);
            node.subNodes.add(subNode);
        }
    }

    @Override
    public void addCases() {

        testCases.add(() -> BFS(S, E));
    }

    private void BFS(int s, int e) {
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(0, s);
        queue.add(root);
        int result = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(node.sum == e) {
                result = node.level;
                break;
            }

            setSubNode(node);
            queue.addAll(node.subNodes);
        }

        System.out.println(result);
    }
}
