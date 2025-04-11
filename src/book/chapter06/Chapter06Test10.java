package book.chapter06;

import common.CodingTest;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Stack;

// 문제 10. 괄호 회전하기 *
public class Chapter06Test10 extends CodingTest {

    private final String S1 = "[](){}";
    private final String S2 = "}]()[{";
    private final String S3 = "[)(]";
    private final String S4 = "}}}";

    @Override
    public void addCases() {
        testCases.add(() -> createCase(S1));
        testCases.add(() -> createCase(S2));
        testCases.add(() -> createCase(S3));
        testCases.add(() -> createCase(S4));

//        testCases.add(() -> createCaseReview(S1));
//        testCases.add(() -> createCaseReview(S2));
//        testCases.add(() -> createCaseReview(S3));
//        testCases.add(() -> createCaseReview(S4));
    }

    /*
        - Stack에서는 위 / 아래로 표현
        - Deque에서는 앞 / 뒤로 표현
    */

    /*
        - deque => LIFO / FIFO 방식으로 모두 사용 가능 (양방향 큐 / Stack + Queue)

        - push(), pop()은 stack처럼 동작
            - push 시 [a], [b, a], [c, b, a] → pop 시 c부터 제거됨 (FILO / LIFO)

        - add(), remove()는 queue처럼 동작
            - add 시 [a], [a, b], [a, b, c] → remove 시 a부터 제거됨 (FIFO)

        - 맨 마지막 요소를 반환하거나 제거하려면 다음 메서드를 사용해야 함
            - getLast() : 맨 끝 값 반환
            - removeLast() : 맨 끝 값 제거
            - pollLast() : 맨 끝 값 제거 (비어있으면 null 반환)

        - top()이라는 메서드는 없지만, stack에서 top처럼 쓰려면 peek() 또는 peekFirst() 사용
            - 가장 마지막에 push된 값이 top 위치

        현재 deque가 [c, b, a]일 때
            - top 위치의 값은 c (peek())
            - getLast() 위치의 값은 a

        이때 add("d")를 하면 [c, b, a, d]가 되고
            - top 위치의 값은 여전히 c
            - getLast()의 값은 d가 됨
    */

    /*
        시간 복잡도 : O(N^2)
    */
    private void createCase(String str) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        int result = 0;
        int n = str.length();       // O(1)
        String s =  str + str;      // O(N)

        // 전체 문자열 체크하는 루프
        A: for(int i=0; i < n; i++) {                                                // O(N) // 회전 횟수: n번 반복

            // 여는 괄호를 담기 위한 Deque (유효한, 한쌍이 될만한 괄호를 찾기 위한 저장소)
            ArrayDeque<Character> deque = new ArrayDeque<>();

            // 한 쌍이 되는 문자열, 즉, 문자열이 유효한지를 확인하는 루프
            for(int j = i; j < i + n; j++) {                                        // O(N) // 회전된 문자열을 검사
                char c =  s.charAt(j);                                              // O(1)

                if(!map.containsKey(c)) {                                           // O(1)
                    // 여는 괄호의 경우
                    deque.push(c);                                                  // O(1)
                } else {
                    // 닫는 괄호의 경우 -> 바로 앞에 한 쌍이 될만한 여는 괄호가 있는지 체크해야함 (유효한지)

                    // stack이 비어있지 않다면 들어있던 여는 괄호 꺼내와서 서로 한 쌍이 되는지 체크 (equals)
                    // 만약 stack이 비어있거나 짝이 안 맞으면 continue
                    // -> 즉, 현재 문자열과 한 쌍이 아니면 => 더 이상 체크가 무의미함 (유의미한 문자열이 아니기 때문) => 따라서 다음 루프로 이동
                    if (deque.isEmpty() || !deque.pop().equals(map.get(c))) {       // O(1)
                        continue A;
                    }
                }
            }

            // 한쌍이 될만한 문자열을 모두 체크했는데도 현재 deque가 비워져 있다는건 현재 문자열은 유효한 한 쌍이 있다는 의미
            if (deque.isEmpty()) {                                                  // O(1)
                result++;
            }
        }

        System.out.println("[작업 이전] : " + str);
        System.out.println("[작업 결과] : " + result);
    }

    // 이해한 코드 바탕으로 학습용으로 재작성
    private void createCaseReview(String str) {

        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        int n = str.length();
        String s = str + str;

        int result = 0;
        char[] arr = s.toCharArray();

        A : for(int i = 0; i < n; i++) {
            Stack<Character> stack = new Stack<>();

            for(int j = i; j < n + i; j++) {
                Character c = arr[j];
                if(!map.containsKey(c)) {
                    stack.push(c);
                } else {
                    if(stack.isEmpty() || !stack.pop().equals(map.get(c))) {
                        continue A;
                    }
                }
            }

            if(stack.isEmpty()) {
                result++;
            }
        }
        System.out.println("[작업 이전] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
