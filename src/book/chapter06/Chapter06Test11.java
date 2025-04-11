package book.chapter06;

import common.CodingTest;

import java.util.ArrayDeque;
import java.util.Stack;

// 문제 11. 짝지어 제거하기 *
public class Chapter06Test11 extends CodingTest {

    private final String S1 = "baabaa";
    private final String S2 = "cdcd";
    private final String S3 = "baaabbaa";

    @Override
    public void addCases() {
        testCases.add(() -> createCase(S1));
        testCases.add(() -> createCase(S2));
        testCases.add(() -> createCase(S3));
    }

    /*
        시간 복잡도 : O(N)
    */
    private void createCaseBefore(String str) {

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for(Character c : str.toCharArray()) {                              // O(N)
            stack.addLast(c);                                               // O(1)
        }

        ArrayDeque<Character> taskStack = new ArrayDeque<>();
        while(!stack.isEmpty()) {                                           // O(N)
            Character c = stack.pollFirst();                                // O(1)
            if(!taskStack.isEmpty() && taskStack.getLast().equals(c)) {     // O(1)
                taskStack.pollLast();                                       // O(1)
            } else {
                taskStack.add(c);                                           // O(1)
            }
        }


        int result = taskStack.isEmpty() ? 1 : 0;

        System.out.println("[작업 이전] : " + str);
        System.out.println("[작업 결과] : " + result);
    }

    // 개선된 코드
    /*
        시간 복잡도 : O(N)
    */
    private void createCase(String str) {

        Stack<Character> stack = new Stack<>();
        for(Character c : str.toCharArray()) {                  // O(N)
            if(!stack.isEmpty() && stack.peek().equals(c)) {    // O(1)
                stack.pop();                                    // O(1)
            } else {
                stack.push(c);                                  // O(1)
            }
        }

        int result = stack.isEmpty() ? 1 : 0;

        System.out.println("[작업 이전] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
