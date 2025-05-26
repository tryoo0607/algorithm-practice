package book.chapter08;

import common.CodingTest;

import java.util.Arrays;
import java.util.HashSet;

// 문제 18. 두 개의 수로 특정 값 만들기 *
public class Chapter08Test18 extends CodingTest {

    private final int[] ARR1 = {1, 2, 3, 4, 8};
    private final int TARGET1 = 6;

    private final int[] ARR2 = {2, 3, 5, 9};
    private final int TARGET2 = 10;

    @Override
    public void addCases() {

        testCases.add(() -> createCase(ARR1, TARGET1));
        testCases.add(() -> createCase(ARR2, TARGET2));
    }

    /*
        시간 복잡도 : O(N)
        공간 복잡도 : O(N)
    */
    private void createCase(int[] arr, int target) {

        int expected = arr.length;
        float loadFactor = 0.75f;
        int cap = (int)(expected / loadFactor) + 1;

        HashSet<Integer> hashSet = new HashSet<>(cap);  // 공간복잡도 : O(N)

        boolean answer = false;
        for(int num : arr) {                            //  O(N)

            if(hashSet.contains(target - num)) {        //  O(1)
                answer = true;
                break;
            }

            hashSet.add(num);                           // O(1)
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[target] : " + target);
        System.out.println("[작업 결과] : " + answer);
    }
}
