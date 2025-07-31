package lecture.section06;

import common.CodingTest;

import java.util.Stack;

// 05. 쇠막대기
// 힌트 : 스택의 개수가 중요
public class Section06Test05 extends CodingTest {

    private final String STR1 = "()(((()())(())()))(())";
    private final String STR2 = "(((()(()()))(())()))(()())";

    @Override
    public void addCases() {

        // 잘못된 풀이 -> 쇠막대기 끝 일때는 전체 + stack 크기가 아니라 끝 부분인 쇠막대기 1개만 추가하면 됨
        testCases.add(() -> createCase(STR1));
        testCases.add(() -> createCase(STR2));

        // 올바른 풀이
        testCases.add(() -> createCase2(STR1));
        testCases.add(() -> createCase2(STR2));
    }


    // 잘못된 풀이
    private void createCase(String str) {

        Stack<Character> stack = new Stack();

        char[] arr = str.toCharArray();

        int count = 0;
        Character last = null;
        for(Character c : str.toCharArray()) {

            last = c;

            if(c.equals('(')) {
                stack.push(c);
                continue;
            }

            if(last.equals(')')) {
                stack.pop();
                count += stack.size();
            } else {

                count += stack.size();
                stack.pop();
            }
        }


        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + count);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    private void createCase2(String str) {

        Stack<Character> stack = new Stack();


        int count = 0;
        Character last = null;
        for(Character c : str.toCharArray()) {

            if(c.equals('(')) {
                stack.push(c);
            } else {
                stack.pop();

                if(last != null && last.equals('(')) {
                    // 절단되서 생긴 막대기
                    count += stack.size();
                } else {
                    // 쇠막대기 끝
                    count ++;
                }
            }

            last = c;
        }


        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + count);
    }
}
