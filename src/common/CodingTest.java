package common;

import java.util.ArrayList;
import java.util.List;

public abstract class CodingTest {

    protected List<Runnable> testCases = new ArrayList<>(); // 테스트 케이스
    private Long limitMillis; // 제한시간
    private int errorCases = 0; // 위배 케이스 수


    /* 생성자 */
    public CodingTest() {}

    public CodingTest(long limitMillis) {
        this.limitMillis = limitMillis;
    }

    /* 실행 */
    public void run() {
        addCases();
        executeCases();

        System.out.println("==========================[결과]==========================");
        if (errorCases > 0) {
            System.out.println("위배된 Case 수 : " + errorCases);
        } else {
            System.out.println("Success");
        }
        System.out.println("=========================================================");
    }


    /* Case */
    public abstract void addCases();

    private void executeCases() {
        for (int i = 0; i < testCases.size(); i++) {
            solve(testCases.get(i), i + 1);  // 케이스 번호는 1부터 시작
        }
    }

    private void solve(Runnable testCase, int index) {
        System.out.printf("------------------[Test Case %d]------------------%n", index);

        long start = System.currentTimeMillis();

        testCase.run();

        long end = System.currentTimeMillis();

        long executionTime = end - start;
        printedExecuteTime(executionTime);

        if (limitMillis != null && executionTime > limitMillis) {
            errorCases++;
            System.out.printf("[Error] 제한 시간 초과 : %dms (제한: %dms)%n", executionTime, limitMillis);
        }

        System.out.println("-------------------------------------------------");
    }

    private void printedExecuteTime(long millis) {
        long minutes = millis / 1000 / 60;
        long seconds = (millis / 1000) % 60;
        long remainMillis = millis % 1000;

        System.out.printf("[실행 시간] : %d분 %d초 %d밀리초%n", minutes, seconds, remainMillis);
    }
}
