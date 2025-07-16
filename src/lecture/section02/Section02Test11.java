package lecture.section02;

import common.CodingTest;

// 11. 문자열 압축
public class Section02Test11 extends CodingTest {

    private final String STR1 = "KKHSSSSSSSE";
    private final String STR2 = "KSTTTSEEKFKKKDJJGG";

    @Override
    public void addCases() {

        // case1
        testCases.add(() -> createCase(STR1));
        testCases.add(() -> createCase(STR2));

        // case2
        testCases.add(() -> createCase2(STR1));
        testCases.add(() -> createCase2(STR2));
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    private void createCase(String str){

        char target = str.charAt(0);
        int count = 0;
        StringBuilder builder = new StringBuilder();

        for(int i=0; i < str.length(); i++) {

            char c = str.charAt(i);

            if(c != target) {
                if(count == 1) {
                    builder.append(target);
                } else {
                    builder.append(target);
                    builder.append(count);
                }

                target = c;
                count = 0;
                count++;
            } else {
                count++;
            }
        }

        if(count == 1) {
            builder.append(target);
        } else {
            builder.append(target);
            builder.append(count);
        }

        String result = builder.toString();
        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    // 조금 더 세련된 방법
    private void createCase2(String str) {

        int count = 1;
        StringBuilder builder = new StringBuilder();

        // 마지막 문자열 카운트도 포함시키기 위한 로직
        str += " ";

        for(int i=0; i < str.length() -1; i++) {

            if(str.charAt(i) == str.charAt(i+1)) {
                count++;
            } else {
                builder.append(str.charAt(i));

                if(count > 1) {
                    builder.append(count);
                }

                count = 1;
            }
        }

        String result = builder.toString();
        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
