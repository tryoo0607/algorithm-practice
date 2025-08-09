package lecture.section08;

import common.CodingTest;

import java.util.Stack;

/*
    해당 내용은 스스로 작성해본 버전
*/
// 05. 이진트리순회(DFS : Depth-First Search)
public class Section08Test05 extends CodingTest {

    // 이진트리이기 때문에 lt / rt로 구현한 것
    // 만일 하위 노드가 더 많으면 ArrayList 등의 자료구조 이용
    private static class Node implements Cloneable {
        int data;
        boolean visited = false;

        private Node lt;
        private Node rt;

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

    // 전위 순회 출력법
    private void preorderRecursion(Node node) {

        if(node.visited) return;

        Node lt = node.lt;
        Node rt = node.rt;

        System.out.print(node.data + " ");

        if(lt != null && !lt.visited) {
            preorderRecursion(lt);
        }

        if(rt != null && !rt.visited) {
            preorderRecursion(rt);
        }

        node.visited = true;
    }

    // 중위 순회 출력법
    private void inorderRecursion(Node node) {

        if(node.visited) return;

        Node lt = node.lt;
        Node rt = node.rt;

        if(lt != null && !lt.visited) {
            inorderRecursion(lt);
        }

        System.out.print(node.data + " ");

        if(rt != null && !rt.visited) {
            inorderRecursion(rt);
        }


        node.visited = true;
    }

    // 후위 순회 출력법
    private void postorderRecursion(Node node) {

        if(node.visited) return;

        Node lt = node.lt;
        Node rt = node.rt;

        if(lt != null && !lt.visited) {
            postorderRecursion(lt);
        }

        if(rt != null && !rt.visited) {
            postorderRecursion(rt);
        }

        System.out.print(node.data + " ");
        node.visited = true;
    }


    // 전위 순회 출력법 (루트 -> 좌쪽 -> 우측)
    private void preorderLoop(Node tree) {

        Stack<Node> stack = new Stack<>();
        stack.push(tree);

        while(!stack.isEmpty()) {
            Node root = stack.peek();

            Node lt = root.lt;
            Node rt = root.rt;

            if(!root.visited) {
                root.visited = true;
                System.out.print(root.data + " ");
            }

            if(lt != null && !lt.visited) {
                stack.push(lt);
            } else if(rt != null && !rt.visited) {
                stack.push(rt);
            } else {
                stack.pop();
            }
        }
    }

    // 중위 순회 출력법 (좌측 -> 루트 -> 우측)
    private void inorderLoop(Node tree) {

        Stack<Node> stack = new Stack<>();
        stack.push(tree);

        while(!stack.isEmpty()) {
            Node root = stack.peek();

            Node lt = root.lt;
            Node rt = root.rt;

            if(lt != null && !lt.visited) {
                stack.push(lt);
            } else if(!root.visited) {
                root.visited = true;
                System.out.print(root.data + " ");
            } else if(rt != null && !rt.visited) {
                stack.push(rt);
            } else {
                root.visited = true;
                stack.pop();
            }
        }
    }

    // 후위 순회 출력법 (좌쪽 -> 우측 -> 루트)
    private void postorderLoop(Node tree) {

        Stack<Node> stack = new Stack<>();
        stack.push(tree);

        while(!stack.isEmpty()) {
            Node root = stack.peek();

            Node lt = root.lt;
            Node rt = root.rt;

            if(lt != null && !lt.visited) {
                stack.push(lt);
            } else if(rt != null && !rt.visited) {
                stack.push(rt);
            } else {
                root.visited = true;
                Node pop = stack.pop();
                System.out.print(pop.data + " ");
            }
        }
    }
}
