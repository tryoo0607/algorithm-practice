package lecture.section04;

import common.CodingTest;

import java.util.Arrays;

public class Section04Test05 extends CodingTest {

    private final int N = 15;

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N));
    }

    private void createCase(int n) {

        int count = 0;

        int lt = 1;

        int sum = 0;
        for(int rt=1; rt < n; rt++) {
            sum+=rt;

            while(sum > n) {
                sum-=lt++;
            }

            if(sum == n) count++;
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[작업 결과] : " + count);
    }
}
