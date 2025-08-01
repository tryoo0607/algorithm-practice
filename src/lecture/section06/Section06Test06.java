package lecture.section06;

import common.CodingTest;

import java.util.LinkedList;
import java.util.Queue;

// 06. 공주구하기
public class Section06Test06 extends CodingTest {

    private final int N = 8;
    private final int K = 3;

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, K));
    }


    /*
        시간복잡도 : O(N) => O(N*K) 이지만 K가 의미있게 크진 않음
        공간복잡도 : O(N)
    */
    private void createCase(int n, int k) {

        Queue<Integer> queue = new LinkedList<>();

        for(int i=1; i <= n; i++) {
            queue.add(i);
        }

        int count = 0;
        while(queue.size() > 1) {
            count++;

            if(count == k) {
                count = 0;
                queue.poll();
                continue;
            }

            queue.add(queue.poll());
        }

        int result = !queue.isEmpty() ? queue.poll() : 0;

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[k] : " + k);
        System.out.println("[작업 결과] : " + result);
    }
}
