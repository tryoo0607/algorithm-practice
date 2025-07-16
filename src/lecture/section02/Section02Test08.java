package lecture.section02;

import common.CodingTest;

// 08. 유효한 팰린드롬
public class Section02Test08 extends CodingTest {

    private final String STR = "found7, time: study; Yduts; emit, 7Dnuof";
    @Override
    public void addCases() {

//        testCases.add(() -> createCase(STR));
        testCases.add(() -> createCase2(STR));
    }

    private void createCase(String str) {

        int lt = 0;
        int rt = str.length() - 1;

        String beforeStr = str;
        str = str.toLowerCase();

        boolean isTrue = true;
        while(lt < rt) {
            char strLt = str.charAt(lt);
            char strRt = str.charAt(rt);

            if(!Character.isAlphabetic(str.charAt(lt))) {
                lt++;
            } else if(!Character.isAlphabetic(str.charAt(rt))) {
                rt--;
            } else {
                if(strLt != strRt) {
                    isTrue = false;
                    break;
                }

                lt++;
                rt--;
            }
        }


        String result = isTrue ? "YES" : "NO";
        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + beforeStr);
        System.out.println("[작업 결과] : " + result);
    }

    // 정규식 활용
    private void createCase2(String str) {

        String filteredStr = str.toUpperCase().replaceAll("[^A-Z]", "");
        String reversedStr = new StringBuilder(filteredStr).reverse().toString();

        String result = filteredStr.equals(reversedStr) ? "YES" : "NO";

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
