package book.chapter08;

import common.CodingTest;

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

    private void createCase(int[] arr, int target) {

    }
}
