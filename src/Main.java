import baekjoon.BaekjoonHandler;
import book.BookHandler;
import common.*;

public class Main {
    public static void main(String[] args) {

        CodingTest test = getCodingTestWithType(TestType.BOOK);
//        CodingTest test = getCodingTestWithType(TestType.BAEKJOON);

        test.run();
    }

    private static CodingTest getCodingTestWithType(TestType type) {

        CodingTestHandler handler;

        switch(type) {
            case BOOK -> handler = new BookHandler();
            case BAEKJOON -> handler = new BaekjoonHandler();
            default -> handler = new DefaultHandler();
        }

        return handler.getTest();
    }
}