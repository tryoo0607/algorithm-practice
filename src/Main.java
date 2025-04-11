import baekjoon.BaekjoonHandler;
import book.BookHandler;
import common.*;

public class Main {
    public static void main(String[] args) {

        CodingTest test = getCodingTest(TestType.BOOK);

        // 고정
        test.run();
    }

    private static CodingTest getCodingTest(TestType testType) {

        CodingTestHandler handler;

        switch(testType) {
            case BOOK -> handler = new BookHandler();
            case BAEKJOON -> handler = new BaekjoonHandler();
            default -> handler = new DefaultHandler();
        }

        return handler.getTest();
    }
}