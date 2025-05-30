package book.chapter08;

import common.CodingTest;

// 문제 22. 베스트 앨범 **
public class Chapter08Test22 extends CodingTest {

    private final String[] GENRES = {"classic", "pop", "classic", "classic", "pop"};
    private final Integer[] PLAYS = {500, 600, 150, 800, 2500};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(GENRES, PLAYS));
    }

    private void createCase(String[] genres, Integer[] plays) {

    }
}
