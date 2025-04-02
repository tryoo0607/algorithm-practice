package chapter05;

import model.CodingTest;

import java.util.Arrays;

// 문제 01. 배열 정렬하기
public class Chapter05Test01 extends CodingTest {

    private static final int[] arr1 = {1, -5, 2, 4, 3};
    private static final int[] arr2 = {2, 1, 1, 3, 2, 5, 4};
    private static final int[] arr3 = {6, 1, 7};

    @Override
    public void addCases() {
        testCases.add(() -> sortArr(arr1));
        testCases.add(() -> sortArr(arr2));
        testCases.add(() -> sortArr(arr3));
    }

    private void sortArr(int[] arr) {
        int[] clone = arr.clone();
        Arrays.sort(clone);

        System.out.println("[정렬 결과] : " + Arrays.toString(clone));
    }
}
