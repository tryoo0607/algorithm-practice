package lecture.section06;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

// 02. 괄호 문자 제거
public class Section06Test02 extends CodingTest {

    private final String STR = "(A(BC)D)EF(G(H)(IJ)K)LM(N)";

    @Override
    public void addCases() {

        // depth 활용
        // 스택이 의미 없는  풀이법 -> 개선 필요
        testCases.add(() -> createCase(STR));

        // 개선한 풀이법 (스택 활용 X)
        testCases.add(() -> createCase2(STR));

        /* 스택 활용 */
        // 스택을 활용한 풀이법
        testCases.add(() -> createCase3(STR));

        // 스택 기능 최대한 활용한 풀이
        testCases.add(() -> createCase4(STR));
    }

    // 스택이 의미 없는 풀이법
    private void createCase(String str){

        Stack<Character> stack = new Stack<>();

        int depth = 0;
        for(Character c : str.toCharArray()) {

            if(c.equals('(')) {
                depth++;
                continue;
            } else if (c.equals(')')) {
                depth--;
                continue;
            }

            if(depth != 0) continue;

            stack.push(c);
        }

        String result = stack.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }


    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N) // O(K) -> k는 괄호 밖 문자 수 => c최대 O(N)
    */
    // 개선한 풀이법 (스택 활용 X)
    private void createCase2(String str){

        StringBuilder builder = new StringBuilder();

        int depth = 0;
        for(Character c : str.toCharArray()) {

            if(c.equals('(')) {
                depth++;
            } else if (c.equals(')')) {
                depth--;
            } else if (depth == 0) {
                builder.append(c);
            }
        }

        String result = builder.toString();

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    // 스택을 활용한 풀이법
    private void createCase3(String str){

        Stack<Character> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();

        for(Character c : str.toCharArray()) {

            if(c.equals('(')) {
                stack.push(c);
            } else if (c.equals(')')) {
                stack.pop();
            } else if(stack.isEmpty()) {
                builder.append(c);
            }
        }

        String result = builder.toString();

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }

    // 스택 기능 최대한 활용한 풀이
    private void createCase4(String str){

        Stack<Character> stack = new Stack<>();

        for(Character c : str.toCharArray()) {

            if (c.equals(')')) {
                while(stack.pop() != '(');
            } else {

                stack.push(c);
            }
        }

        String result = stack.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining());

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }

}
