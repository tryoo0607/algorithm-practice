package lecture.section05;

import common.CodingTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 01. 학급 회장(해쉬)
public class Section05Test01 extends CodingTest {

    private final int N = 15;
    private final String STR = "BACBACCACCBDEDE";

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, STR));
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(1) (후보자는 5명이라는 상수라서)
    */
    private void createCase(int n, String str) {

        HashMap<Character, Integer> candidate = new HashMap<>();

        for(Character c : str.toCharArray()) {

            Integer count = candidate.getOrDefault(c, 0);

            candidate.put(c, count + 1);
        }

        // O(1) -> 후보자가 5명이기 때문
        Character result = Objects.requireNonNull(candidate.entrySet().stream()
                        .max(Map.Entry.comparingByValue())  // O(K) -> K = 후보자 수 = 5 = O(1)
                        .orElse(null))
                .getKey();

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
