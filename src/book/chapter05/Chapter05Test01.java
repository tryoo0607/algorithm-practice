package book.chapter05;

import common.CodingTest;

import java.util.Arrays;

// 문제 01. 배열 정렬하기
public class Chapter05Test01 extends CodingTest {

    private final int[] ARR1 = {1, -5, 2, 4, 3};
    private final int[] ARR2 = {2, 1, 1, 3, 2, 5, 4};
    private final int[] ARR3 = {6, 1, 7};

    @Override
    public void addCases() {
        testCases.add(() -> createCase(ARR1));
        testCases.add(() -> createCase(ARR2));
        testCases.add(() -> createCase(ARR3));
    }

    private void createCase(int[] arr) {
        int[] clone = arr.clone();
        Arrays.sort(clone);

        System.out.println("[작업 이전] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + Arrays.toString(clone));
    }
}
