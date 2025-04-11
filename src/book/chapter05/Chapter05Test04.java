package book.chapter05;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;

// 문제 04. 모의고사
public class Chapter05Test04 extends CodingTest {
    private final int[] ANSWERS1 = {1, 2, 3, 4, 5};
    private final int[] ANSWERS2 = {1, 3, 2, 4, 2};

    @Override
    public void addCases() {
        testCases.add(() -> createCase(ANSWERS1));
        testCases.add(() -> createCase(ANSWERS2));
    }


    /*
        시간 복잡도 : O(N)
    */
    private void createCase(int[] answers) {
        int[][] patterns = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        int[] scores = new int[patterns.length];

        /*
        - answer 수는 N이라고 가정 (1~10000)

        - 만약 pattern[j]의 길이가 answer보다 길면 그대로 answer의 길이만큼만 검증하면 됨
        - ex) pattern[j].length = 6 / answer.length = 5 => 5개만 검증하면 됨

        - 만약 pattern[j]의 길이가 answer보다 짧으면 나머지 길이만큼 pattern[j]를 반복해야 함
        - ex) pattern[j].length = 5 / answer.length = 10 => pattern[j][6]은 없으므로 pattern[j][1]과 비교해야 함
        */

        // N => answer의 길이 / K => 수포자 수 (patterns의 길이, 만약 고정이  아니라고 가정하는 경우)
        // 현재 해당 이중 포문의 전체 시간 복잡도는 O(N)
        for(int i = 0; i < answers.length; i++) {                       // O(N)
            for(int j = 0; j < patterns.length; j++) {                  // O(1) => Pattern 고정이라고 가정(문제에서 수험자 수에 따라 달라짐 입력 가능하면 O(K)
                if(answers[i] == patterns[j][i % patterns[j].length]) { // O(1)
                    scores[j]++;                                        // O(1) => scores의 특정 요소에 접근(O(1)) 하여 정수 증가 (O(1)) 시키기 때문
                }
            }
        }

        int maxScore = Arrays.stream(scores)        // O(1)
                .max()                              // O(1) => patterns 길이가 고정이 아니라 가정할 경우 O(K)
                .getAsInt();                        // O(1)

        ArrayList<Integer> targets = new ArrayList<>();

        // O(1) (현재 문제의 경우 patterns의 길이가 고정되어 있기 때문)
        // O(K) (만약 patterns의 길이를 K로 가정하는 경우)
        for(int i = 0; i < scores.length; i++) {    // O(1) => patterns의 길이를 K로 가정하면 O(K)
            if(scores[i] == maxScore) {             // O(1)
                targets.add(i + 1);                 // O(1) => 지금 코드에선 중간 삽입이 일어나지 않음 (항상 맨 끝에 추가됨) => 따라서 항상 O(1)
            }
        }

        // 시간 복잡도 : O(1)
        int[] results = targets.stream()        // O(1)
                .sorted()                       // O(1) => 만약 patterns의 길이를 K로 가정하면 O(K log K)
                .mapToInt(Integer::intValue)    // O(1)
                .toArray();                     // O(1)

        System.out.println("[작업 이전] : " + Arrays.toString(answers));
        System.out.println("[작업 결과] : " + Arrays.toString(results));
    }
}
