package book;

import book.chapter05.*;
import book.chapter06.*;
import common.CodingTest;
import common.CodingTestHandler;

public class BookHandler extends CodingTestHandler {

    @Override
    public CodingTest getTest() {
        /*
            Chapter 05
        */
//        return new Chapter05Test01();       // 문제 01. 배열 정렬하기 *
//        return new Chapter05Test02();       // 문제 02. 배열 제어하기 **
//        return new Chapter05Test03();       // 문제 03. 두 개 뽑아서 더하기 *
//        return new Chapter05Test04();       // 문제 04. 모의고사 *
//        return new Chapter05Test05();       // 문제 05. 행렬의 곱셈 *
//        return new Chapter05Test06();       // 문제 06. 실패율 **
//        return new Chapter05Test07();       // 문제 07. 방문 길이 **


        /*
            Chapter 06
        */
//        return new Chapter06Test08();       // 문제 08. 올바른 괄호 **
//        return new Chapter06Test09();       // 문제 09. 10진수를 2진수로 변환하기 *
        return new Chapter06Test10();       // 문제 10. 괄호 회전하기 *     => 복습 필수 (다시 풀어볼 것)
    }
}
