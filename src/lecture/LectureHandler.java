package lecture;

import common.CodingTest;
import common.CodingTestHandler;
import lecture.section01.*;

/*
    인프런 강의 : 자바(Java) 알고리즘 문제풀이 입문: 코딩테스트 대비
    문제 사이트 : https://cote.inflearn.com/contest/10/problems
*/
public class LectureHandler extends CodingTestHandler {

    @Override
    public CodingTest getTest() {

        /* Section 01 */
//        return new Section01Test01();       // 01. 문자 찾기
//        return new Section01Test02();       // 02. 대소문자 변환
//        return new Section01Test03();       // 03. 문장 속 단어
//        return new Section01Test04();       // 04. 단어 뒤집기
//        return new Section01Test05();       // 05. 특정 문자 뒤집기
//        return new Section01Test06();       // 06. 중복문자제거
//        return new Section01Test07();       // 07. 회문 문자열
//        return new Section01Test08();       // 08. 유효한 팰린드롬
//        return new Section01Test09();       // 9. 숫자만 추출
//        return new Section01Test10();       // 10. 가장 짧은 문자거리
//        return new Section01Test11();       // 11. 문자열 압축
        return new Section01Test12();       // 12. 암호
    }
}
