package lecture.section01;

import common.CodingTest;

import java.util.Arrays;

public class Section01Test04 extends CodingTest {

    private int NUM = 3;
    private String[] STR_ARR = {"good", "Time", "Big"};

    @Override
    public void addCases() {
        testCases.add(() -> createCase(NUM, STR_ARR));
//        testCases.add(() -> createCase2(NUM, STR_ARR));
    }

    private void createCase(int num, String[] strArr) {

        String[] result = new String[num];

        for(int i=0; i < strArr.length; i++) {
            String str = strArr[i];
            StringBuilder builder = new StringBuilder(str.length());

            for(int j = str.length() - 1; j >= 0; j--) {
                builder.append(str.charAt(j));
            }

            result[i] = builder.toString();
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[num] : " + num);
        System.out.println("[strArr] : " + Arrays.toString(strArr));
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }

    // StringBuilder의 reverse 이용한 방법
    private void createCase2(int num, String[] strArr) {

        String[] result = new String[num];

        for(int i=0; i < strArr.length; i++) {
            String str = strArr[i];
            StringBuilder builder = new StringBuilder(str.length());

            result[i] = builder.reverse().toString();
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[num] : " + num);
        System.out.println("[strArr] : " + Arrays.toString(strArr));
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }
}
