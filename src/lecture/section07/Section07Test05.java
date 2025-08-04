package lecture.section07;

import common.CodingTest;

import java.util.Arrays;
import java.util.LinkedHashSet;

// 05. 중복확인
public class Section07Test05 extends CodingTest {

    private final int N = 8;
    private final int[] ARR = {20, 25, 52, 30, 39, 33, 43, 33};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, ARR));

        // 아주 조금 개선된 코드
        testCases.add(() -> createCase2(N, ARR));
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    private void createCase(int n, int[] arr) {

        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        boolean isDuplicated = false;

        for(int target : arr) {
            if(set.contains(target)) {
                isDuplicated = true;
                break;
            }

            set.add(target);
        }

        String result = isDuplicated ? "D" : "U";

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    // 성능 조금 더 개선
    private void createCase2(int n, int[] arr) {

        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        boolean isDuplicated = false;

        // add 안되면 false 발생
        for(int target : arr) {
            if(!set.add(target)) {
                isDuplicated = true;
                break;
            }
        }

        String result = isDuplicated ? "D" : "U";

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }
}
