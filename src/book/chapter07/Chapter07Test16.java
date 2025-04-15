package book.chapter07;

import common.CodingTest;

import java.util.*;

// 문제 16. 기능 개발 **
public class Chapter07Test16 extends CodingTest {

    private final String PROGRESSES = "progresses";
    private final String SPEEDS = "speeds";

    private final HashMap<String, int[]> MAP1 = new HashMap<>(){{
        put(PROGRESSES, new int[]{93, 30, 55});
        put(SPEEDS, new int[]{1, 30, 5});
    }};

    private final HashMap<String, int[]> MAP2 = new HashMap<>(){{
        put(PROGRESSES, new int[]{95, 90, 99, 99, 80, 99});
        put(SPEEDS, new int[]{1, 1, 1, 1, 1, 1});
    }};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(MAP1));
        testCases.add(() -> createCase(MAP2));
    }

    private void createCaseBefore(HashMap<String, int[]> map) {
        int[] progresses = map.get(PROGRESSES);
        int[] speeds = map.get(SPEEDS);

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for(int progress : progresses) {
            deque.add(progress);
        }

        int n = progresses.length;

        ArrayDeque<Integer> result = new ArrayDeque<>();

        int beforeTimes = (int) Math.ceil((double) (100 - deque.getFirst()) / speeds[0]);
        int count = 0;
        for(int i = 0; i < n; i++) {

            double target = (double) (100 - deque.pop()) / speeds[i];
            int times = (int) Math.ceil(target);

            if(times <= beforeTimes) {
                count++;
            } else {
                result.add(count);
                count = 1;
            }

            if(deque.isEmpty()) {
                result.add(count);
            }

            beforeTimes = Math.max(times, beforeTimes);
        }


        System.out.println("[작업 이전] : ");
        System.out.println("processes : " + Arrays.toString(progresses));
        System.out.println("speeds : " + Arrays.toString(speeds));
        System.out.println("[작업 결과] : " + Arrays.toString(result.toArray()));
    }

    // 사실 상 위의 로직과 동일 => 조금 더 깔끔하게 다듬어짐
    /*
        시간 복잡도 : O(N)
        공간 복잡도 : O(N)
    */
    private void createCase(HashMap<String, int[]> map) {
        int[] progresses = map.get(PROGRESSES);
        int[] speeds = map.get(SPEEDS);

        ArrayDeque<Integer> result = new ArrayDeque<>();        // 공간 복잡도 : O(N)

        int n = progresses.length;

        int[] daysLeft = new int[n];

        for(int i = 0; i < n; i++) {        // O(N)
            daysLeft[i] = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]); // O(1)
        }

        int count = 0;
        int maxDay = daysLeft[0];
        for(int i = 0; i < n; i++) {        // O(N)
            if(daysLeft[i] <= maxDay) {
                count++;                    // O(1)
            } else {
                result.add(count);          // O(1)
                count = 1;
            }
        }

        // 마지막 요소 추가 위해
        result.add(count);


        System.out.println("[작업 이전] : ");
        System.out.println("processes : " + Arrays.toString(progresses));
        System.out.println("speeds : " + Arrays.toString(speeds));
        System.out.println("[작업 결과] : " + Arrays.toString(result.toArray()));
    }
}
