package lecture.section04;

import common.CodingTest;

import java.util.Arrays;

// 04.연속부분수열(복합적문제)
public class Section04Test04 extends CodingTest {

    private final int N = 8;
    private final int M = 6;
    private final int[] ARR = {1, 2, 1, 3, 1, 1, 1, 2};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, M, ARR));

        // 권장 방식 (투포인터)
        testCases.add(() -> createCase2(N, M, ARR));
    }

    /*
        시간복잡도 : O(N^2)
        공간복잡도 : O(1)
    */
    private void createCase(int n, int m, int[] arr) {

        int result = 0;

        for(int i=0; i < n; i++) {
            int sum = 0;
            for(int j=i; j < n; j++) {
                sum+=arr[j];

                if(sum == m) result++;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(1)
    */
    // 투포인터 알고리즘 활용
    private void createCase2(int n, int m, int[] arr) {
        int count = 0;
        int sum = 0;
        int lt = 0;

        // rt와 lt는 각각 최대 N번만 움직임
        /*
           - rt / lt => 부분 집합의 왼쪽 끝과 오른쪽 끝 => 즉, 최대 0 ~ n-1 => 최대 N개
           - for문 내의 모든 while의 합은 lt의 최대 이동거리인 N
           - 그리고 for문의 rt 역시 최대 N까지만 이동 가능함
           - 따라서 둘이 합친 2N이 최대 입력값이고
           - 시간복잡도는 O(N)이 됨
        */
        for (int rt = 0; rt < n; rt++) {
            sum += arr[rt];

            while (sum > m) {
                sum -= arr[lt++];
            }

            if (sum == m) count++;
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + count);
    }

}
