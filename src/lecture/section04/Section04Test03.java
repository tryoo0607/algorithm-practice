package lecture.section04;

import common.CodingTest;

import java.util.Arrays;

// 03. 최대 매출
public class Section04Test03 extends CodingTest {
    private final int N = 10;
    private final int K = 3;
    private final int[] SALES = {12, 15, 11, 20, 25, 10, 20, 19, 13, 15};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, K, SALES));

        // 보다 효율적인 방법
        testCases.add(() -> createCase2(N, K, SALES));
    }

    /*
        시간복잡도 : O(NK)
        공간복잡도 : O(1)
    */
    private void createCase(int n, int k, int[] sales) {

        int result = 0;
        for(int i=0; i <= n - k; i++){
            int sum = 0;

            for(int j=i; j < i + k; j++){
                sum += sales[j];
            }

            result = Math.max(sum, result);
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[k] : " + k);
        System.out.println("[sales] : " + Arrays.toString(sales));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(1)
    */
    // 슬라이딩 윈도우 기법
    private void createCase2(int n, int k, int[] sales) {

        int sum = 0;
        // 처음 sum 값 구하기
        // O(N)
        for (int i = 0; i < k; i++) {
            sum += sales[i];
        }

        int max = sum;

        // 슬라이딩 윈도우
        // 처음 sum에서 앞 요소를 뺴고 뒷 요소 추가하는 식으로 sliding
        // O(N - K)
        for (int i = k; i < n; i++) {
            sum = sum + sales[i] - sales[i - k];
            max = Math.max(max, sum);
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[k] : " + k);
        System.out.println("[sales] : " + Arrays.toString(sales));
        System.out.println("[작업 결과] : " + max);
    }
}
