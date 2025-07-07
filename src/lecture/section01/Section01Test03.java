package lecture.section01;

import common.CodingTest;

// 03. 문장 속 단어
public class Section01Test03 extends CodingTest {

    private String STR = "it is time to study";

    @Override
    public void addCases() {

        testCases.add(() -> createCase(STR));
    }

    private void createCase(String str) {

        String[] strArr = str.split(" ");

        String result = strArr[0];

        for(String s : strArr) {

            if(result.length() < s.length()) {
                result = s;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
