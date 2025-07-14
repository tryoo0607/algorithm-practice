package lecture.section02;

import common.CodingTest;

import java.util.Arrays;
import java.util.stream.Collectors;

// 04. 피보나치 수열
public class Section02Test04 extends CodingTest {

    private final int N = 10;

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N));
    }

    private void createCase(int n) {

        int[] resultArr = new int[n];
        resultArr[0] = 1;
        resultArr[1] = 1;

        for(int i=2; i < n; i++) {
            resultArr[i] = resultArr[i - 2] + resultArr[i - 1];
        }

        String result = Arrays.stream(resultArr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));


        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[작업 결과] : " + result);
    }
}
