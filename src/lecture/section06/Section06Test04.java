package lecture.section06;

import common.CodingTest;

import java.util.Stack;

// 04. 후위식 연산(postfix)
public class Section06Test04 extends CodingTest {

    private final String STR = "352+*9-";

    @Override
    public void addCases() {

        // 로직은 맞음
        testCases.add(() -> createCase(STR));

        // 보다 깔끔한 코드
        testCases.add(() -> createCase2(STR));
    }

    /*
        시간 복잡도 : O(N)
        공간 복잡도 : O(N)
    */
    private void createCase(String str) {

        Stack<Integer> stack = new Stack();

        for(Character c : str.toCharArray()) {

            if(c.equals('+')) {
                Integer right = stack.pop();
                Integer left = stack.pop();
                stack.push(left+right);
            } else if (c.equals('-')) {
                Integer right = stack.pop();
                Integer left = stack.pop();
                stack.push(left-right);
            } else if (c.equals('*')) {
                Integer right = stack.pop();
                Integer left = stack.pop();
                stack.push(left*right);
            } else if(c.equals('/')) {
                Integer right = stack.pop();
                Integer left = stack.pop();
                stack.push(left/right);
            } else {
                String intStr = String.valueOf(c);
                Integer trans = Integer.parseInt(intStr);
                stack.push(trans);

            }

        }

        Integer result = stack.pop();

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간 복잡도 : O(N)
        공간 복잡도 : O(N)
    */
    private void createCase2(String str) {

        Stack<Integer> stack = new Stack();

        for(Character c : str.toCharArray()) {

            if(Character.isDigit(c)) {
                String intStr = String.valueOf(c);
                Integer trans = Integer.parseInt(intStr);
                stack.push(trans);
                continue;
            }

            Integer right = stack.pop();
            Integer left = stack.pop();

            if(c.equals('+')) {
                stack.push(left+right);
            } else if (c.equals('-')) {
                stack.push(left-right);
            } else if (c.equals('*')) {
                stack.push(left*right);
            } else if(c.equals('/')) {
                stack.push(left/right);
            }
        }

        Integer result = stack.pop();

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
