package lecture.section08;

import common.CodingTest;

import java.util.ArrayList;
import java.util.List;

// 06. 부분집합 구하기(DFS)
public class Section08Test06 extends CodingTest {

    private final int N = 3;
    static List<List<Integer>> result = new ArrayList<>();

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N));
    }

    /*
        핵심 : 이진트리 => 포함할지 말지
    */
    private class Node {
        int num;
        boolean isInclude;
        Node lt;
        Node rt;

        public Node(int num, boolean isInclude) {
            this.num = num;
            this.isInclude = isInclude;
        }
    }

    private Node getTree(int level, boolean isInclude, int n) {
        if(level > n) return null;

        Node node = new Node(level, isInclude);

        node.lt = getTree(level+1, true, n);
        node.rt = getTree(level+1, false, n);

        return node;
    }

    private void DFS(Node node, List<Integer> path) {
        /*
            예) N = 3일 때, level이 4가 되면 node = null이 됨
            node가 null이라는 것은 더 이상 내려갈 노드가 없다는 의미 → 재귀 종료
        */
        if(node == null) return;

        // isInclude: 이 숫자를 부분집합에 포함시킬지 여부 (num이 0일 때는 루트 노드이므로 제외)
        // path: 현재 DFS 경로에서 포함하기로 선택된 숫자들의 리스트
        if (node.isInclude && node.num != 0) {
            path.add(node.num);
        }

        /*
            리프(leaf): 자식이 없는 노드(더 이상 내려갈 곳이 없음)
            이 시점에서 path에는 현재 경로를 따라 선택된(isInclude == true) 숫자들만 들어 있음
            이를 result에 저장하면 하나의 부분집합이 완성됨
        */
        if (node.lt == null && node.rt == null) {      // 리프

            // 공집합은 출력에서 제외
            if (!path.isEmpty()) result.add(new ArrayList<>(path));

        // 더 내려갈 곳이 있으면 재귀 탐색 (포함 → 미포함 순서)
        } else {
            DFS(node.lt, path);                // 포함 먼저
            DFS(node.rt, path);                // 미포함 다음
        }

        /*
            아래 로직은 백트래킹을 수행함

            백트래킹: 현재 노드에서 path에 추가한 숫자를 원복(pop)

            이유:
            - level이 2일 때 자식 노드들은 모두 level 3이 되고,
              lt는 '포함', rt는 '미포함'의 경우를 탐색
            - lt와 rt는 같은 path 객체(부모 경로)를 공유함
            - path는 참조형(얕은 복사)이므로, lt에서 추가한 값이 rt에도 그대로 영향을 미치게 됨
            - 이를 방지하려면, 현재 노드에서 추가한 값만 제거하여 상위 호출의 상태를 복구해야 함
        */
        if (node.isInclude && node.num != 0) path.remove(path.size() - 1);
    }

    private void createCase(int n) {
        result.clear();

        Node tree = getTree(0, false, n);

        List<Integer> path = new ArrayList<>();
        DFS(tree, path);

        System.out.println(result);
    }
}
