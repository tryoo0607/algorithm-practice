package lecture.section08;

import common.CodingTest;

import java.util.ArrayList;

// 04. 피보나치 재귀(메모이제이션)
public class Section08Test04 extends CodingTest {

    private final int N = 10;

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N));
    }

    private void createCase(int n) {

        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;

        for(int i= 2; i < n; i++) {

            arr[i] = arr[i - 1] + arr[i - 2];
        }

        for(int num : arr) {
            System.out.print(num + " ");
        }
    }
}
