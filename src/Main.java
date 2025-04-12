import baekjoon.BaekjoonHandler;
import book.BookHandler;
import common.*;
import leetcode.LeetCodeHandler;

public class Main {
    public static void main(String[] args) {

        CodingTest test = getCodingTestWithType(TestType.BOOK);
//        CodingTest test = getCodingTestWithType(TestType.BAEKJOON);
//        CodingTest test = getCodingTestWithType(TestType.LEET_CODE);

        test.run();
    }

    private static CodingTest getCodingTestWithType(TestType type) {

        CodingTestHandler handler;

        switch(type) {
            case BOOK -> handler = new BookHandler();
            case BAEKJOON -> handler = new BaekjoonHandler();
            case LEET_CODE -> handler = new LeetCodeHandler();
            default -> handler = new DefaultHandler();
        }

        return handler.getTest();
    }
}