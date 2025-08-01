package lecture.section06;

import common.CodingTest;

import java.util.LinkedList;
import java.util.Queue;

// 07. 교육과정 설계
public class Section06Test07 extends CodingTest {

    private final String REQUIRED = "CBA";
    private final String SUBJECTS = "CBDAGE";

    @Override
    public void addCases() {

        testCases.add(() -> createCase(REQUIRED, SUBJECTS));

        // 사소한 개선 방식
        testCases.add(() -> createCase2(REQUIRED, SUBJECTS));
    }

    /*
        시간복잡도 : O(N) // 전체 과목 수
        공간복잡도 : O(M) // 필수과목 수
    */
    private void createCase(String required, String subjects) {

        Queue<Character> requiredQueue = new LinkedList<>();
        Queue<Character> subjectQueue = new LinkedList<>();

        for(Character c : required.toCharArray()) {
            requiredQueue.add(c);
        }

        for(Character c : subjects.toCharArray()) {
            subjectQueue.add(c);
        }

        while(!subjectQueue.isEmpty()) {
            if(requiredQueue.isEmpty()) break;
            Character target = requiredQueue.peek();
            Character subject = subjectQueue.poll();

            if(target.equals(subject)) {
                requiredQueue.poll();
            }
        }

        String result = requiredQueue.isEmpty() ? "YES" : "NO";

        System.out.println("[작업 이전] : ");
        System.out.println("[required] : " + required);
        System.out.println("[subjects] : " + subjects);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N) // 전체 과목 수
        공간복잡도 : O(M) // 필수과목 수
    */
    private void createCase2(String required, String subjects) {

        Queue<Character> requiredQueue = new LinkedList<>();

        for(Character c : required.toCharArray()) {
            requiredQueue.add(c);
        }

        for(Character subject : subjects.toCharArray()) {
            if(requiredQueue.isEmpty()) break;
            Character target = requiredQueue.peek();

            if(target.equals(subject)) {
                requiredQueue.poll();
            }
        }

        String result = requiredQueue.isEmpty() ? "YES" : "NO";

        System.out.println("[작업 이전] : ");
        System.out.println("[required] : " + required);
        System.out.println("[subjects] : " + subjects);
        System.out.println("[작업 결과] : " + result);
    }
}
