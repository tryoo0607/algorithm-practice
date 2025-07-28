package lecture.section05;

import common.CodingTest;

import java.util.Arrays;
import java.util.HashMap;

// 03. 매출액의 종류
public class Section05Test03 extends CodingTest {

    private final int N = 7;
    private final int K = 4;
    private final int[] ARR = {20, 12, 20, 10, 23, 17, 10};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, K, ARR));
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N) // O(N - K + 1) == O(N)
    */
    private void createCase(int n, int k, int[] arr) {

        int lt = 0;

        int[] result = new int[n - k + 1];

        HashMap<Integer, Integer> countMap = new HashMap<>();
        for(int rt=0; rt < n; rt++) {
            Integer key = arr[rt];
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);

            int boundary  = k - 1;
            if(rt < boundary) continue;

            result[rt - boundary] = countMap.size();

            Integer popKey = arr[lt++];
            int count = countMap.get(popKey) - 1;

            if(count == 0) {
                countMap.remove(popKey);
            } else {
                countMap.put(popKey, count);
            }

        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[k] : " + k);
        System.out.println("[k] : " + k);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }

    // 로직은 같으나 깔끔하게 다듬은 버전
    private void createCase2(int n, int k, int[] arr) {

        int lt = 0;

        int[] result = new int[n - k + 1];

        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int rt = 0; rt < n; rt++) {
            countMap.put(arr[rt], countMap.getOrDefault(arr[rt], 0) + 1);

            if (rt - lt + 1 > k) {
                int popKey = arr[lt++];
                int count = countMap.get(popKey);
                if (count == 1) countMap.remove(popKey);
                else countMap.put(popKey, count - 1);
            }

            if (rt >= k - 1) {
                result[lt] = countMap.size();
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[k] : " + k);
        System.out.println("[k] : " + k);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }
}
