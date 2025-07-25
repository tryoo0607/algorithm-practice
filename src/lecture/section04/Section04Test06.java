package lecture.section04;

import common.CodingTest;

import java.util.Arrays;

// 06. 최대 길이 연속부분수열
public class Section04Test06 extends CodingTest {

    private final int N = 14;
    private final int K = 2;
    private final int[] ARR = {1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1};

    @Override
    public void addCases() {

        // 잘못된 풀이
        testCases.add(() -> createCase(N, K, ARR));

        // 권장 풀이
        testCases.add(() -> createCase2(N, K, ARR));
    }

    // 주의 : 가장 긴 슬라이딩 윈도우를 찾아야 함
    private void createCase(int n, int k, int[] arr) {

        int lt = 0;
        int count = 0;
        int rt;
        for(rt=0; rt<n; rt++) {
            if(arr[rt] == 1) continue;

            if(count < k) {

                count++;

            } else {
                while(count == k) {
                    if(arr[lt] == 0) {
                        count--;
                    }

                    lt++;
                }

                count++;
            }

        }

        int result = rt - lt + 1;

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[k] : " + k);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(1)
    */
    // 권장 풀이
    private void createCase2(int n, int k, int[] arr) {

        int lt = 0;
        int count = 0;

        int max = 0;

        // for문 : N + while : N
        // N * N이 아닌 N + N :
        for(int rt=0; rt<n; rt++) {
            if(arr[rt] == 0) {

                count++;
            }

            while(count > k) {
                if(arr[lt] == 0) {
                    count--;
                }

                lt++;
            }

            int length = rt - lt + 1;

            max = Math.max(length, max);
        }



        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[k] : " + k);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + max);
    }
}
