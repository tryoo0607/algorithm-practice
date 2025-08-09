package lecture.section08;

import common.CodingTest;

import java.util.Stack;

/*
    해당 내용은 ChatGPT 도움받아 개선받은 버전
    => 재귀함수의 경우 여기서 명시된 버전을 권장
    => but 반복문의 경우 직관적이지 못하여 추천하지 않음
*/
public class Section08Test05_Solution extends CodingTest {

    // 이진트리이기 때문에 lt / rt로 구현한 것
    // 만일 하위 노드가 더 많으면 ArrayList 등의 자료구조 이용
    private static class Node {
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

        Node layer1Node1 = root.lt;
        layer1Node1.lt = new Node(4);
        layer1Node1.rt = new Node(5);

        Node layer1Node2 = root.rt;
        layer1Node2.lt = new Node(6);
        layer1Node2.rt = new Node(7);

        return root;
    }

    @Override
    public void addCases() {

        // 재귀 호출
        testCases.add(() -> preorderRecursion(getTree()));
        testCases.add(() -> inorderRecursion(getTree()));
        testCases.add(() -> postorderRecursion(getTree()));

        // 반복문
        testCases.add(() -> preorderLoop(getTree()));
        testCases.add(() -> inorderLoop(getTree()));
        testCases.add(() -> postorderLoop(getTree()));
    }

    /*
        # 재귀 호출
        시간복잡도 :
        공간복잡도 :
    */
    // 전위 순회 출력법 (루트 -> 좌쪽 -> 우측)
    private void preorderRecursion(Node node) {

        if(node == null) return ;

        Node lt = node.lt;
        Node rt = node.rt;
        System.out.print(node.data + " ");

        preorderRecursion(lt);
        preorderRecursion(rt);
    }

    // 중위 순회 출력법 (좌측 -> 루트 -> 우측)
    private void inorderRecursion(Node node) {

        if(node == null) return ;

        Node lt = node.lt;
        Node rt = node.rt;

        inorderRecursion(lt);
        System.out.print(node.data + " ");
        inorderRecursion(rt);
    }

    // 후위 순회 출력법 (좌쪽 -> 우측 -> 루트)
    private void postorderRecursion(Node node) {

        if(node == null) return ;

        Node lt = node.lt;
        Node rt = node.rt;

        postorderRecursion(lt);
        postorderRecursion(rt);
        System.out.print(node.data + " ");
    }


    /*
        # 반복문
        시간복잡도 :
        공간복잡도 :
    */
    // 전위 순회 출력법 (루트 -> 좌쪽 -> 우측)
    private void preorderLoop(Node tree) {
        if (tree == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(tree);

        while(!stack.isEmpty()) {
            Node root = stack.pop();

            System.out.print(root.data + " ");

            Node lt = root.lt;
            Node rt = root.rt;

            // Stack에 나중에 탐색할 것을 먼저 쌓기 위해 rt를 먼저 검사
            if(rt != null) {
                stack.push(rt);
            }

            // rt 쌓은 뒤 lt 쌓아야하므로 else if아닌 if 사용
            if(lt != null) {
                stack.push(lt);
            }
        }
    }

    // 중위 순회 출력법 (좌측 -> 루트 -> 우측)
    private void inorderLoop(Node tree) {
        Stack<Node> stack = new Stack<>();
        Node current = tree;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.lt;
            }
            current = stack.pop();
            System.out.print(current.data + " ");
            current = current.rt;
        }
    }

    // 후위 순회 출력법 (좌쪽 -> 우측 -> 루트)
    private void postorderLoop(Node tree) {
        if (tree == null) return;
        Stack<Node> stack = new Stack<>();
        Node prev = null;
        stack.push(tree);

        while (!stack.isEmpty()) {
            Node current = stack.peek();
            // 내려가는 단계
            if (prev == null || prev.lt == current || prev.rt == current) {
                if (current.lt != null) stack.push(current.lt);
                else if (current.rt != null) stack.push(current.rt);
                else {
                    System.out.print(current.data + " ");
                    stack.pop();
                }
            }
            // 왼쪽에서 올라오는 단계
            else if (current.lt == prev) {
                if (current.rt != null) stack.push(current.rt);
                else {
                    System.out.print(current.data + " ");
                    stack.pop();
                }
            }
            // 오른쪽에서 올라오는 단계
            else {
                System.out.print(current.data + " ");
                stack.pop();
            }
            prev = current;
        }
    }
}
