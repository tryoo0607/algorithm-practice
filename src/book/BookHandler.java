package book;

import book.chapter05.*;
import book.chapter06.*;
import book.chapter07.*;
import book.chapter08.*;
import common.CodingTest;
import common.CodingTestHandler;

public class BookHandler extends CodingTestHandler {

    @Override
    public CodingTest getTest() {
        /*  Chapter 05  */
//        return new Chapter05Test01();       // 문제 01. 배열 정렬하기 *
//        return new Chapter05Test02();       // 문제 02. 배열 제어하기 **
//        return new Chapter05Test03();       // 문제 03. 두 개 뽑아서 더하기 *
//        return new Chapter05Test04();       // 문제 04. 모의고사 *
//        return new Chapter05Test05();       // 문제 05. 행렬의 곱셈 *
//        return new Chapter05Test06();       // 문제 06. 실패율 **
//        return new Chapter05Test07();       // 문제 07. 방문 길이 **


        /*  Chapter 06  */
//        return new Chapter06Test08();       // 문제 08. 올바른 괄호 **
//        return new Chapter06Test09();       // 문제 09. 10진수를 2진수로 변환하기 *
//        return new Chapter06Test10();       // 문제 10. 괄호 회전하기 *  // stack, deque 개념            => 복습 필수 (다시 풀어볼 것)
//        return new Chapter06Test11();       // 문제 11. 짝지어 제거하기 *
//        return new Chapter06Test12();       // 문제 12. 주식 가격 ** // 단조스택                         => 복습 필수 (다시 풀어볼 것)
//        return new Chapter06Test13();       // 문제 13. 크레..인 인형 뽑기 게임 **
//        return new Chapter06Test14();       // 문제 14. 표 편집 *****


        /*  Chapter 07  */
//        return new Chapter07Test15();       // 문제 15. 요세푸스 문제 **
//        return new Chapter07Test16();       // 문제 16. 기능 개발 **
//        return new Chapter07Test17();       // 문제 17. 카드 뭉치 **


        /*  Chapter 08  */
//        return new Chapter08Test18();       // 문제 18. 두 개의 수로 특정 값 만들기 *
//        return new Chapter08Test19();       // 문제 19. 완주하지 못한 선수 *
//        return new Chapter08Test20();       // 문제 20. 할인 행사 **
//        return new Chapter08Test21();       // 문제 21. 오픈 채팅방 **
        return new Chapter08Test22();       // 문제 22. 베스트 앨범 ** // Stream 활용 방식                 => 복습 필수 (다시 풀어볼 것)
    }
}
