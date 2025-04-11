package book.chapter05;

import common.CodingTest;

import java.util.*;

// 문제 06. 실패율 **
public class Chapter05Test06 extends CodingTest {

    private final int N1 = 5;
    private final int[] STAGES1 = {2, 1, 2, 6, 2, 4, 3, 3};

    private final int N2 = 4;
    private final int[] STAGES2 = {4, 4, 4, 4, 4};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N1, STAGES1));
        testCases.add(() -> createCase(N2, STAGES2));

//        testCases.add(() -> createCaseBefore(N1, STAGES1));
//        testCases.add(() -> createCaseBefore(N2, STAGES2));
    }


    // 개선 전
    // O(M * N + N log N)
    private void createCaseBefore(int totalStage, int[] stages) {

        HashMap<Integer, Double> rateMap = new HashMap<>();

        /*
         - 람다식 내부에서 외부 변수를 사용하려면 해당 변수는 final 또는 effectively final이어야 함
         - for문 내의 i는 변하기 때문에 람다 내부에서 직접 사용할 수 없음
         - 따라서 i 값을 final 변수로 한 번 더 감싸서 사용해야 함
        */

        // 전체 O(M * N)
        for(int i = 0; i < totalStage; i++) {               // O(M)
            final int stageNum = i + 1;
            long reachCount = Arrays.stream(stages)         // O(1)
                    .filter(stage -> stage >= stageNum)     // O(N)
                    .count();                               // O(1)
            long stayCount = Arrays.stream(stages)          // O(1)
                    .filter(stage -> stage == stageNum)     // O(N)
                    .count();                               // O(1)

            Double rate = reachCount == 0 ? 0d : (double) stayCount / reachCount; // O(1)
            rateMap.put(stageNum, rate);    // O(1)
        }


        // 내림차순 정렬을 위해 Double.compare 를 o2를 앞으로 배치
        // rateMap => N 수만큼 존재
        // O(N log N)
        int[] result = rateMap.entrySet()                                           // O(N) // 내부적으로 Map의 모든 entry(key, value 한 쌍을 나타내는 객체)를 반환함 => 따라서 O(N)
                .stream()                                                           // O(1)
                .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))   // O(N log N)
                .mapToInt(Map.Entry::getKey)                                        // O(N)
                .toArray();                                                         // O(N)


        System.out.printf("[작업 이전] : N) %s / stages) %s", totalStage, Arrays.toString(stages));
        System.out.println();
        System.out.println("[작업 결과] : " + rateMap.values());
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }

    // 개선 후 코드

    /*
      시간 복잡도 : O(M + N log N)
    */
    private void createCase(int totalStage, int[] stages) {

        // totalStage가 5이면 stages는 최대 6까지 출력 가능 (+1)
        // 배열의 index는 0부터 시작하지만 stages는 1부터 시작 (+1)
        int[] challengers = new int[totalStage + 2];            // O(1)

        /*
            총 stage 수 : N
            stages의 길이 (== user 수) : M
        */

        // O(M)
        for(int stage : stages) {       // O(M)
            challengers[stage]++;       // O(1)
        }

        int users = stages.length;
        Map<Integer, Double> rateMap = new HashMap<>();

        for(int i = 1; i <= totalStage; i++) {                      // O(N)
            if(challengers[i] == 0) {                               // O(1)
                rateMap.put(i, 0d);                                 // O(1) // Map이라서
            } else {
                rateMap.put(i, (double) challengers[i] / users);    // O(1)
                users -= challengers[i];                            // O(1)
            }
        }


        // 내림차순 정렬을 위해 Double.compare 를 o2를 앞으로 배치
        // rateMap => N 수만큼 존재
        // O(N log N)
        int[] result = rateMap.entrySet()                                           // O(N) // 내부적으로 Map의 모든 entry(key, value 한 쌍을 나타내는 객체)를 반환함 => 따라서 O(N)
                .stream()                                                           // O(1)
                .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))   // O(N log N)
                .mapToInt(Map.Entry::getKey)                                        // O(N)
                .toArray();                                                         // O(N)


        System.out.printf("[작업 이전] : N) %s / stages) %s", totalStage, Arrays.toString(stages));
        System.out.println();
        System.out.println("[작업 결과] : " + rateMap.values());
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }

}
