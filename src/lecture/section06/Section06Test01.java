package lecture.section06;

import common.CodingTest;

import java.util.Stack;

// 01. 올바른 괄호
public class Section06Test01 extends CodingTest {

    private final String STR = "(()(()))(()";

    @Override
    public void addCases() {

        // 잘못된 풀이
        testCases.add(() -> createCase(STR));

        // 옳게된 풀이
        testCases.add(() -> createCase2(STR));
    }

    // 잘못된 풀이
    private void createCase(String str) {

        Stack<Character> stack = new Stack<>();

        for(Character c : str.toCharArray()) {
            stack.push(c);
        }


        // 이렇게 비교할 필요가 없음 어차피 (만 쌓고 ) 들어오면 pop 해버리면 됨
        while(!stack.isEmpty()) {
            Character right = stack.pop();
            Character left = stack.peek();

            if(left.equals('(') && right.equals(')')) {
                stack.pop();
            } else {
                break;
            }
        }

        String result = stack.isEmpty() ? "YES" : "NO";

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }

    private void createCase2(String str) {

        Stack<Character> stack = new Stack<>();

        String result = "YES";
        for(Character c : str.toCharArray()) {

            if(c.equals('(')) {
                stack.push(c);
            } else {
                if(stack.isEmpty()) {
                    result = "NO";
                    break;
                }
                stack.pop();
            }
        }

        if(!stack.isEmpty()) result = "NO";

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
