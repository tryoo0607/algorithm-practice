package lecture.section08;

import common.CodingTest;

import java.util.*;

// 08. 송아지 찾기 1(BFS : 상태 트리 탐색)
// 아래와 같이 방문한 곳을 체크하는 로직 반드시 필요
public class Section08Test08_Solution extends CodingTest {

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

    private void setSubNode(Node node, boolean[] visited) {
        List<Integer> jumpList = Arrays.asList(1, -1, 5);

        for (Integer jump : jumpList) {

            // 다음 위치
            int nextPos = node.sum + jump;

            // 다음 갈 위치가 좌표 범위에서 이내이고, 방문한 곳이 아니라면
            if (nextPos >= 1 && nextPos <= 10000 && !visited[nextPos]) {
                visited[nextPos] = true;
                Node subNode = new Node(node.level + 1, nextPos);
                node.subNodes.add(subNode);
            }
        }
    }

    @Override
    public void addCases() {

        testCases.add(() -> BFS(S, E));
    }

    private void BFS(int s, int e) {

        // 방문한 좌표를 체크하기 위한 배열(필수)
        boolean[] visited = new boolean[10001];
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(0, s);
        queue.add(root);
        visited[s] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.sum == e) {
                System.out.println(node.level);
                return;
            }

            setSubNode(node, visited);
            queue.addAll(node.subNodes);
        }
    }
}
