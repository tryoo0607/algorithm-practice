package book.chapter06;

import common.CodingTest;

import java.util.Stack;

// 문제 08. 올바른 괄호 **
public class Chapter06Test08 extends CodingTest {

    private final String S1 = "()()";
    private final String S2 = "(())()";
    private final String S3 = ")()(";
    private final String S4 = "(()(";

    @Override
    public void addCases() {
        testCases.add(() -> createCase(S1));
        testCases.add(() -> createCase(S2));
        testCases.add(() -> createCase(S3));
        testCases.add(() -> createCase(S4));
    }

    /*
      시간 복잡도 : O(N)
    */
    private void createCase(String str) {

        Stack<Character> stack = new Stack<>();

        boolean result = true;

        for(Character s : str.toCharArray()) {              // O(N)
            if(s == '(') {
                stack.push(s);                              // O(1)
            } else if (s == ')') {
                if(stack.isEmpty()) result = false;         // O(1)
                else stack.pop();                           // O(1)
            }
        }

        if(!stack.isEmpty()) result = false;                // O(1)

        System.out.println("[작업 이전] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
