package book.chapter07;

import common.CodingTest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

// 문제 17. 카드 뭉치 **
public class Chapter07Test17 extends CodingTest {

    private final String CARDS1 = "cards1";
    private final String CARDS2 = "cards2";
    private final String GOAL = "goal";

    private final HashMap<String, String[]> MAP1 = new HashMap<>(){{
        put(CARDS1, new String[]{"i", "drink", "water"});
        put(CARDS2, new String[]{"want", "to"});
        put(GOAL, new String[]{"i", "want", "to", "drink", "water"});
    }};

    private final HashMap<String, String[]> MAP2 = new HashMap<>(){{
        put(CARDS1, new String[]{"i", "water", "drink"});
        put(CARDS2, new String[]{"want", "to"});
        put(GOAL, new String[]{"i", "want", "to", "drink", "water"});
    }};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(MAP1));
        testCases.add(() -> createCase(MAP2));
    }

    /*
        시간 복잡도 : O(M + N)
        공간 복잡도 : O(M + N)
    */
    private void createCase(HashMap<String, String[]> map) {

        String[] cards1 = map.get(CARDS1);
        String[] cards2 = map.get(CARDS2);
        String[] goal = map.get(GOAL);

        ArrayDeque<String> cards1Deque = new ArrayDeque<>(Arrays.asList(cards1)); // 공간 복잡도 O(M)
        ArrayDeque<String> cards2Deque = new ArrayDeque<>(Arrays.asList(cards2)); // 공간 복잡도 O(N)

        for(String target : goal) { // O(M + N) // goal.length == M + N이라는 조건 하에
            String drawCard1 = !cards1Deque.isEmpty() ? cards1Deque.peekFirst() : "";   // O(1)
            String drawCard2 = !cards2Deque.isEmpty() ? cards2Deque.peekFirst() : "";   // O(1)

            if(target.equals(drawCard1)) {
                cards1Deque.poll();                 // O(1)
            } else if(target.equals(drawCard2)) {
                cards2Deque.poll();                 // O(1)
            }
        }

        String result = cards1Deque.isEmpty() && cards2Deque.isEmpty() ? "YeS" : "No";

        System.out.println("[작업 이전] : ");
        System.out.println("[cards1] : " + Arrays.toString(cards1));
        System.out.println("[cards2] : " + Arrays.toString(cards2));
        System.out.println("[goal] : " + Arrays.toString(goal));
        System.out.println("[작업 결과] : " + result);
    }
}
