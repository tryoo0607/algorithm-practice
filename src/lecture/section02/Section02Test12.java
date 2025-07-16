package lecture.section02;

import common.CodingTest;

// 12.암호
public class Section02Test12 extends CodingTest {

    private final int NUM = 4;
    private final String STR = "#****###**#####**#####**##**";

    @Override
    public void addCases() {

        testCases.add(() -> createCase(STR));
        testCases.add(() -> createCase2(NUM, STR));
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    private void createCase(String str) {

        StringBuilder result = new StringBuilder();

        for(int i=0; i < str.length() - 6; i+=7) {
            StringBuilder builder = new StringBuilder();

            for(int j=i; j < i+7; j++) {
                char c = str.charAt(j);
                if(c == '#') {
                    builder.append(1);
                } else {
                    builder.append(0);
                }
            }

            int decimal = Integer.parseInt(builder.toString(), 2);
            char letter = (char) decimal;
            result.append(letter);
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result.toString());
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    // 정석 풀이
    private void createCase2(int num, String str) {

        StringBuilder result = new StringBuilder();

        for(int i=0; i < num; i++) {

            String subString = str.substring(i*7, (i+1)*7)
                    .replace('#', '1')
                    .replace('*', '0');

            int decimal = Integer.parseInt(subString, 2);
            char letter = (char) decimal;
            result.append(letter);
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result.toString());
    }
}
