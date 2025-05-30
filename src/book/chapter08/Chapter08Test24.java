package book.chapter08;

import common.CodingTest;

// 문제 24. 메뉴 리뉴얼 ***
public class Chapter08Test24 extends CodingTest {

    private final String[] ORDER1 = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
    private final int[] COURSE1 = {2, 3, 4};

    private final String[] ORDER2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};

    private final int[] COURSE2 = {2, 3, 5};

    private final String[] ORDER3 = {"XYZ", "XWY", "WXA"};
    private final int[] COURSE3 = {2, 3, 4};

    @Override
    public void addCases() {
        testCases.add(() -> createCase(ORDER1, COURSE1));
        testCases.add(() -> createCase(ORDER2, COURSE2));
        testCases.add(() -> createCase(ORDER3, COURSE3));
    }

    private void createCase(String[] order, int[] course) {


        for(String str : order) {
            
        }
    }
}
