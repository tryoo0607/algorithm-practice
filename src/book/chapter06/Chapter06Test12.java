package book.chapter06;

import common.CodingTest;

import java.util.Arrays;
import java.util.Stack;

// 문제 12. 주식 가격 **
public class Chapter06Test12 extends CodingTest {

    private final int[] PRICES1 = {1, 2, 3, 2, 3};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(PRICES1));
    }

    /*
        # 단조 / 단조성 (Monotonic / Monotonicity)
        - 어떤 수열이나 자료가 "한 방향으로만 변하는 성질"
            - 단조 증가 : 계속 커지거나 같아지는 형태
            - 단조 감소 : 계속 작아지거나 같아지는 형태

        # 단조 스택 (Monotonic Stack)
        - 스택에 쌓이는 값이 항상 단조 증가 혹은 단조 감소하도록 유지하는 구조
          -> 예: 항상 오름차순(증가)으로 쌓이게 하거나, 내림차순(감소)으로 쌓이게 함
          -> 즉, 스택에 계속 커지거나 계속 작아지는 값들이 쌓이는 형태이고, 방향이 바뀌는 값은 들어오지 않는 스택의 형태

        - 단조성 유지를 위해 현재 값이 조건을 만족하지 않으면 스택에서 값을 pop하여 제거함
        - 이런 방식은 다음과 같은 문제에 유용함:
            - '뒤에 있는 더 작은 수', '앞에서부터 가격이 떨어지는 시점', '다음 큰 수 찾기' 등

        - 단조 스택은 특정 조건을 만족하는 다음 위치(인덱스)를 빠르게 찾기 위한 스택 기반 기법이며,
          그 자체로 암시적 단일 순회를 유도함 (O(N) 보장)
    */

    /*
        시간 복잡도 : O(N)
    */
    // 단조 스택 패턴의 대표적 예시
    private void createCase(int[] prices) {

        int n = prices.length;
        int[] results = new int[n];

        Stack<Integer> stack = new Stack<>();


        /*
            # 암시적 단일 순회
            - 겉보기 이중 반복이지만 실제 반복은 한 번뿐인 패턴
            - 코드 구조는 for + while 이중 루프처럼 보이지만
                -> 실제로는 전체 원소를 한 번씩만 처리
                -> 이유:
                    - push()는 각 원소마다 1회 실행됨
                    - pop()도 각 원소마다 최대 1회 실행됨

            - 내부의 while문은 전체 prices길이 N에 대해서 push랑 pop이 한번씩 밖에 일어나지 않음
            - 즉, 이 for문의 최대 반복 횟수는 2N
            - 따라서 O(N)

            - 이 경우엔 O(N) * O(N)이 아닌 O(N) + O(N)

            => 아래에서는 단조 스택 구조를 이용하여 이 암시적 단일 순회를 구현한 것
        */
        for(int i = 0; i < n; i++) {                                        // O(N)

            // stack 에 쌓여있는 마지막 index에 해당되는 price보다 현재 값이 떨어질 경우 results에 기록
            while(!stack.isEmpty() && prices[i] < prices[stack.peek()]) {   // 총합 O(N)

                int j = stack.pop();                                        // O(1)
                results[j] = i - j;                                         // O(1)
            }

            stack.push(i);                                                  // O(1)
        }

        // 아직까지 stack에 쌓여있다는 것은 끝까지 가격이 안떨어 졌다는 것
        // 전체 길이 - 1 - 자기 자신의 인덱스
        while(!stack.isEmpty()) {                                           // O(?)
            int i = stack.pop();                                            // O(1)
            results[i] = n - 1 - i;                                         // O(1)
        }

        System.out.println("[작업 이전] : " + Arrays.toString(prices));
        System.out.println("[작업 결과] : " + Arrays.toString(results));
    }
}
