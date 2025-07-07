package lecture.section01;

import common.CodingTest;

// 01. 문자 찾기
public class Section01Test01 extends CodingTest {

    private final String STR = "Computercooler";
    private final Character CHAR = 'c';

    @Override
    public void addCases() {

        testCases.add(() -> createCase(STR, CHAR));
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(1)
    */
    private void createCase(String str, Character targetChar) {

        int count = 0;
        for(Character c : str.toCharArray()) {                      // O(N)
            Character testChar = Character.toLowerCase(c);
            Character target = Character.toLowerCase(targetChar);

            if(target.equals(testChar)) {
                count++;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[targetChar] : " + targetChar);
        System.out.println("[작업 결과] : " + count);
    }
}
