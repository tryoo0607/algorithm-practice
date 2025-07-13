package lecture.section02;

import common.CodingTest;

import java.util.Arrays;

// 02. 보이는 학생
public class Section02Test02 extends CodingTest {

    private final int N = 8;
    private final int[] NUM_ARR = {130, 135, 148, 140, 145, 150, 150, 153};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, NUM_ARR));
    }

    private void createCase(int n, int[] numArr) {

        int count = 1;
        int max = numArr[0];

        for(int i=0; i < n; i++){
            if(max < numArr[i]) {
                max = numArr[i];
                count++;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[numArr] : " + Arrays.toString(numArr));
        System.out.println("[작업 결과] : " + count);
    }
}
