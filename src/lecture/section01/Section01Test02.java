package lecture.section01;

import common.CodingTest;

// 02. 대소문자 변환
public class Section01Test02 extends CodingTest {

    private final String STR = "StuDY";

    @Override
    public void addCases() {
        testCases.add(() -> createCase(STR));
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(1)
    */
    private void createCase(String str) {

        // StringBuilder -> 비동기 / StringBuffer -> 동기 (스레드 안전)
        StringBuilder builder = new StringBuilder(str.length());    //  공간복잡도 : O(1)

        for(Character c : str.toCharArray()) {              // O(N)
            if(Character.isUpperCase(c)) {
                builder.append(Character.toLowerCase(c));
            } else {
                builder.append(Character.toUpperCase(c));
            }
        }

        String result = builder.toString();

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
