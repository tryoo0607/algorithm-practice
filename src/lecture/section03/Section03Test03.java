package lecture.section03;

import common.CodingTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 03. 가위 바위 보
public class Section03Test03 extends CodingTest {

    private final int N = 5;
    private final int[] INPUT_A = {2, 3, 3, 1, 3};
    private final int[] INPUT_B = {1, 1, 2, 2, 3};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, INPUT_A, INPUT_B));
        testCases.add(() -> createCase2(N, INPUT_A, INPUT_B));
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    private void createCase(int n, int[] inputA, int[] inputB) {

        String[] result = new String[n];

        for(int i=0; i < n; i++) {

            if(inputA[i] == inputB[i]) {
                result[i] = "D";
            } else if(inputA[i] == 1) {
                if(inputB[i] == 2) {
                    result[i] = "B";
                } else {
                    result[i] = "A";
                }
            } else if(inputA[i] == 2) {
                if(inputB[i] == 3) {
                    result[i] = "B";
                } else {
                    result[i] = "A";
                }
            } else if(inputA[i] == 3) {
                if(inputB[i] == 1) {
                    result[i] = "B";
                } else {
                    result[i] = "A";
                }
            }
        }


        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[inputA] : " + Arrays.toString(inputA));
        System.out.println("[inputB] : " + Arrays.toString(inputB));
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    // Map 이용한 방법
    private void createCase2(int n, int[] inputA, int[] inputB) {

        Map<Integer, Integer> winMap = new HashMap<>(){{
            put(1,2);
            put(2,3);
            put(3,1);
        }};

        String[] result = new String[n];

        for(int i=0; i < n; i++) {
            if(inputA[i] == inputB[i]) {
                result[i] = "D";
            } else {
                if(winMap.get(inputA[i]) == inputB[i]) {
                    result[i] = "B";
                } else {
                    result[i] = "A";
                }
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[inputA] : " + Arrays.toString(inputA));
        System.out.println("[inputB] : " + Arrays.toString(inputB));
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }
}
