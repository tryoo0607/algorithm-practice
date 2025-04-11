package book.chapter06;

import common.CodingTest;

import java.util.Arrays;
import java.util.Stack;

// 문제 09. 10진수를 2진수로 변환하기 *
public class Chapter06Test09 extends CodingTest {

    private final int DECIMAL1 = 10;
    private final int DECIMAL2 = 27;
    private final int DECIMAL3 = 12345;

    @Override
    public void addCases() {

        testCases.add(() -> createCase(DECIMAL1));
        testCases.add(() -> createCase(DECIMAL2));
        testCases.add(() -> createCase(DECIMAL3));
    }

    // Integer의 내장 메소드를 활용한 간단한 방식
    private void createCaseOther(int decimal) {

        String result = Integer.toBinaryString(decimal);        // O(log N)

        System.out.println("[작업 이전] : " + decimal);
        System.out.println("[작업 결과] : " + result);
    }

    // Stack 활용한 모범 답안
    /*
        시간복잡도 : O(log N)
    */
    private void createCase(int decimal) {

        Stack<Integer> stack = new Stack<>();

        int div = decimal;

        /*
          시간 복잡도: O(log N)

          - 여기서 log N은 log₂(N), log₁₀(N), ln(N) 등과 모두 상수배 차이만 있음
          - 즉, 밑 값이 다르더라도 모두 시간복잡도는 동일함
            → 즉, log₂(N) = log₁₀(N) / log₁₀(2) ≈ log₁₀(N) × 3.3219
            => 밑이 달라진다 == log N의 상수 배가 달라진다 => 즉, 시간 복잡도 O(log N)은 변하지 않음

          - 알고리즘 시간 복잡도에서는 로그의 '밑(base)'은 무시됨
            → log₂(N), log₁₀(N), logₑ(N) 모두 O(log N)으로 표현 가능

          - 따라서, 정확한 연산 횟수가 log₂(decimal)이어도
            빅오 표기에서는 O(log N)으로 표기하는 것이 일반적임
        */
        while(div > 0) {                            // log₂(decimal) => O(N)
            int remain = div % 2;                   // O(1)
            stack.push(remain);                     // O(1)

            div /= 2;                               // O(1)
        }

        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()) {                   // O(log N)
            sb.append(stack.pop());                 // O(1)
        }


        /*
            - sb는 decimal이란 10진수를 2진수로 바꾼 것의 String
            - 따라서 sb는 2진수의 length를 따르게 됨
            - 2진수의 length는 log₂(decimal) == log₂N이 됨
            - 따라서 시간 복잡도는 O(log N)
        */
        String result = sb.toString();              // O(log N)

        System.out.println("[작업 이전] : " + decimal);
        System.out.println("[작업 결과] : " + result);
    }
}
