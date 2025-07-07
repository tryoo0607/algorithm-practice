import baekjoon.BaekjoonHandler;
import book.BookHandler;
import common.*;
import lecture.LectureHandler;
import leetcode.LeetCodeHandler;

public class Main {
    public static void main(String[] args) {

//        CodingTest test = getCodingTestWithType(TestType.BOOK);
//        CodingTest test = getCodingTestWithType(TestType.BAEKJOON);
//        CodingTest test = getCodingTestWithType(TestType.LEET_CODE);
        CodingTest test = getCodingTestWithType(TestType.LECTURE);

        test.run();
    }

    private static CodingTest getCodingTestWithType(TestType type) {

        CodingTestHandler handler;

        switch(type) {
            case BOOK -> handler = new BookHandler();
            case BAEKJOON -> handler = new BaekjoonHandler();
            case LEET_CODE -> handler = new LeetCodeHandler();
            case LECTURE -> handler = new LectureHandler();
            default -> handler = new DefaultHandler();
        }

        return handler.getTest();
    }
}