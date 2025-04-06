package chapter05;

import model.CodingTest;

import java.util.Arrays;

// 문제 04. 모의고사
public class Chapter05Test04 extends CodingTest {
    private final int[] answers1 = {1, 2, 3, 4, 5};
    private final int[] answers2 = {1, 3, 2, 4, 2};

    @Override
    public void addCases() {
        testCases.add(() -> createCase(answers1));
        testCases.add(() -> createCase(answers2));
    }

    private void createCase(int[] answers) {
        int[][] pattern = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };



        System.out.println("[작업 이전] : " + Arrays.toString(answers));
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }
}
