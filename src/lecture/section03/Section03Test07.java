package lecture.section03;

import common.CodingTest;

import java.util.Arrays;

// 07. 점수 계산
public class Section03Test07 extends CodingTest {

    private final int N = 10;
    private final int[] NUM_ARR = {1, 0, 1, 1, 1, 0, 0, 1, 1, 0};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N,  NUM_ARR));
    }

    private void createCase(int n, int[] numArr){

        int total = 0;
        int score = 1;

        for(int i=0; i < n; i++){

            boolean isCorrect = numArr[i] == 1;

            if(isCorrect) {
                total+=score;
                score++;
            } else {
                score = 1;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[numArr] : " + Arrays.toString(numArr));
        System.out.println("[작업 결과] : " + total);
    }
}
