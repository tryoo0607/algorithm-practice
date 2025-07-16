package lecture.section02;

import common.CodingTest;

// 07. 회문 문자열
public class Section02Test07 extends CodingTest {

    private final String STR = "gooG";

    @Override
    public void addCases() {
        testCases.add(() -> createCase(STR));
    }

    private void createCase(String str) {

        int lt = 0;
        int rt = str.length() - 1;

        // 결과 출력용 -> 실전에선 불필요
        String beforeStr = str;

        // 전체 소문자 처리
        str = str.toLowerCase();

        // split의 경우 O(N)의 비용이 발생 -> split 보다는 charAt 활용 권장
        // String[] strArr = str.split("");

        boolean isTrue = true;

        while(lt < rt) {
            // String strLt = strArr[lt].toLowerCase();
            // String strRt = strArr[rt].toLowerCase();

            char strLt = str.charAt(lt);
            char strRt = str.charAt(rt);

            if (strLt != strRt) {
                isTrue = false;
                break;
            }

            lt++;
            rt--;
        }

        String result = isTrue ? "YES" : "NO";
        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + beforeStr);
        System.out.println("[작업 결과] : " + result);
    }
}
