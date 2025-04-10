package chapter05;

import model.CodingTest;

import java.util.*;

// 문제 06. 실패율
public class Chapter05Test06 extends CodingTest {

    private final int N1 = 5;
    private final int[] STAGES1 = {2, 1, 2, 6, 4, 3, 3};

    private final int N2 = 4;
    private final int[] STAGES2 = {4, 4, 4, 4, 4};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N1, STAGES1));
        testCases.add(() -> createCase(N2, STAGES2));
    }

    public void createCase(int totalStage, int[] stages) {

        HashMap<Integer, Double> rateMap = new HashMap<>();

        /*
         - 람다식 내부에서 외부 변수를 사용하려면 해당 변수는 final 또는 effectively final이어야 함
         - for문 내의 i는 변하기 때문에 람다 내부에서 직접 사용할 수 없음
         - 따라서 i 값을 final 변수로 한 번 더 감싸서 사용해야 함
        */
        for(int i = 0; i < totalStage; i++) {
            final int stageNum = i + 1;
            long reachCount = Arrays.stream(stages).filter(stage -> stage >= stageNum).count();
            long stayCount = Arrays.stream(stages).filter(stage -> stage == stageNum).count();

            Double rate = reachCount == 0 ? 0d : (double) stayCount / reachCount;
            rateMap.put(stageNum, rate);
        }


        int[] result = rateMap.entrySet()
                .stream()
                .sorted((o1, o2) -> Double.compare(o1.getValue(), o2.getValue()))
                .mapToInt(Map.Entry::getKey)
                .toArray();


        System.out.printf("[작업 이전] : N) %s / stages) %s", totalStage, Arrays.toString(stages));
        System.out.println();
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }
}
