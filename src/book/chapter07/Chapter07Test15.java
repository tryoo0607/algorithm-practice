package book.chapter07;

import common.CodingTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

// 문제 15. 요세푸스 문제 **
public class Chapter07Test15 extends CodingTest {

    private final int N1 = 5;
    private final int K1 = 2;

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N1, K1));

//        testCases.add(() -> createCaseOther(N1, K1));
    }

    /*
        시간 복잡도 : O(N^2)
        공간 복잡도 : O(N)
    */
    private void createCaseOther(int n, int k) {

        ArrayList<Integer> people = new ArrayList<>();      // 공간 복잡도 : O(N)

        for(int i=0; i < n; i++) {                          // O(N)
            people.add(i+1);
        }


        int lastIdx = 0;
        // O(N^2)
        for(int i = 0; i < n - 1; i++) {                        // O(N)
            int test = (lastIdx + k - 1) % people.size();       // O(1)

            people.remove(test);                                // O(N)
            lastIdx = test;
        }

        int result = people.getFirst();

        System.out.println("[작업 이전] : ");
        System.out.println("n : " + n);
        System.out.println("k : " + k);
        System.out.println("[작업 결과] : " + result);
    }

    // 잘못된 풀이
    private void createCaseBefore(int n, int k) {

        ArrayDeque<Integer> people = new ArrayDeque<>();

        for(int j = k; j < n + k; j++) {
            if(j > n) {
                people.add(j - k);
            } else {
                people.add(j);
            }
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < n -1; i++) {

            people.poll();

            for(int j =0; j < k; j++) {
                if (!people.isEmpty()) {
                    deque.add(people.poll());
                }
            }

            for(int j = 0; j < n - i - k; j++) {
                if(!people.isEmpty()) {
                    deque.push(people.pop());
                }
            }

            people = deque;
        }

        int result = people.getFirst();

        System.out.println("[작업 이전] : ");
        System.out.println("n : " + n);
        System.out.println("k : " + k);
        System.out.println("[작업 결과] : " + result);
    }

    // 개선된 풀이 => 원리는 위와 거의 유사함
    /*
        시간 복잡도 : O(N * k)
        공간 복잡도 : O(N)
    */
    private void createCase(int n, int k) {

        ArrayDeque<Integer> people = new ArrayDeque<>();        // 공간 복잡도 O(N)

        for(int i = 1; i <= n; i++) {
            people.add(i);
        }


        // O(N * K)
        while(people.size() > 1) {                      // O(N)
            for(int j = 0; j < k - 1; j++) {
                if(!people.isEmpty()) {                 // O(K)
                    people.addLast(people.poll());      // O(1)
                }
            }

            people.poll();
        }

        int result = people.pop();

        System.out.println("[작업 이전] : ");
        System.out.println("n : " + n);
        System.out.println("k : " + k);
        System.out.println("[작업 결과] : " + result);
    }
}
