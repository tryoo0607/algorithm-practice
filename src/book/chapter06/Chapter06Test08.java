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
      공간 복잡도 : O(N)
    */
    private void createCase(String str) {

        Stack<Character> stack = new Stack<>();             // 공간 복잡도: O(N)

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

    /*
        시간 복잡도 : O(N)
        공간 복잡도 : O(N)
    */
    // Stack을 사용하지 않는 방법
    private void createCaseOther(String str) {

        int counter = 0;                     // 공간 복잡도: O(1)
        boolean result = true;

        for (char c : str.toCharArray()) {   // O(N)
            if (c == '(') {
                counter++;                   // 여는 괄호 → 증가
            } else if (c == ')') {
                counter--;                   // 닫는 괄호 → 감소

                if (counter < 0) {           // 여는 괄호 없이 닫는 괄호만 나온 경우
                    result = false;
                    break;
                }
            }
        }

        if (counter != 0) result = false;    // 끝났는데 남은 괄호가 있음

        System.out.println("[작업 이전] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
