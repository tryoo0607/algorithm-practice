package lecture.section02;

import common.CodingTest;

// 9. 숫자만 추출
public class Section02Test09 extends CodingTest {

    private final String STR = "g0en2T0s8eSoft";

    @Override
    public void addCases() {

//        testCases.add(() -> createCase(STR));
        testCases.add(() -> createCase2(STR));
    }

    private void createCase(String str) {

        StringBuilder builder = new StringBuilder();

        for(Character c : str.toCharArray()) {

            if(Character.isDigit(c)) {
                builder.append(c);
            }
        }

        if(builder.isEmpty()) {
            builder.append(0);
        }

        int result = Integer.parseInt(builder.toString());

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }

    // 정규식 활용
    private void createCase2(String str) {

        String filteredStr = str.replaceAll("[^0-9]", "");

        filteredStr = !filteredStr.isEmpty() ? filteredStr : "0";
        int result = Integer.parseInt(filteredStr);

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
