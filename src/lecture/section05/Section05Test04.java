package lecture.section05;

import common.CodingTest;

import java.util.HashMap;
import java.util.Map;

// 04. 모든 아나그램 찾기
public class Section05Test04 extends CodingTest {
    private final String S = "bacaAacba";
    private final String T = "abc";

    @Override
    public void addCases() {

        // 잘못된 풀이
        testCases.add(() -> createCase(S, T));
        testCases.add(() -> createCase2(S, T));

        // equals 이용한 방법 -> 가장 효율적인 풀이
        testCases.add(() -> createCase3(S, T));
    }

    // 잘못된 풀이
    private void createCase(String s, String t) {

        int lt = 0;

        int result = 0;

        HashMap<Character, Integer> tMap = new HashMap<>();

        for(Character c : t.toCharArray()) {

            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        char[] characters = s.toCharArray();
        for(int rt=0; rt < s.length(); rt++) {
            Character c = characters[rt];
            int count = tMap.getOrDefault(c, 0);
            int calc = count > 0 ? count - 1 : 0;

            if(calc > 0) {
                tMap.put(c, calc);
            } else {
                tMap.remove(c);
            }

            int anagramSize = rt - lt + 1;
            if(anagramSize < t.length()) continue;
            if(tMap.isEmpty()) {
                result++;
            } else {
              Character newC = characters[lt++];
              tMap.put(newC, tMap.getOrDefault(newC, 0) + 1);
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[s] : " + s);
        System.out.println("[t] : " + t);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O((N - K + 1) * K) == O(NK)
        공간복잡도 : O(2K) == O(K)
    */
    // 개선 -> but 복잡함
    private void createCase2(String s, String t) {

        int result = 0;

        HashMap<Character, Integer> tMap = new HashMap<>();

        for(Character c : t.toCharArray()) {

            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }


        char[] sArr = s.toCharArray();
        for(int rt=0; rt <= s.length() - t.length(); rt++) {
            HashMap<Character, Integer> tMapCopy = new HashMap<>(tMap);
            HashMap<Character, Integer> sMap = new HashMap<>();
            for(int i=rt; i<rt + t.length(); i++) {
                Character c = sArr[i];
                sMap.put(c, sMap.getOrDefault(c, 0) + 1);
            }


            for (Map.Entry<Character, Integer> map : sMap.entrySet()) {
                Character key = map.getKey();
                Integer value = map.getValue();
                int count = tMapCopy.getOrDefault(key, 0) - value;

                if (count <= 0) {
                    tMapCopy.remove(key);
                } else {
                    tMapCopy.put(key, count - 1);
                }
            }

            if(tMapCopy.isEmpty()) result++;
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[s] : " + s);
        System.out.println("[t] : " + t);
        System.out.println("[작업 결과] : " + result);
    }


    /*
        시간복잡도 : O((N - K + 1) * K) == O(NK)
        공간복잡도 : O(2K) == O(K)
    */
    // 가장 좋은 방식
    private void createCase3(String s, String t) {

        int result = 0;

        for(int i=0; i < s.length() - t.length(); i++) {
            String sub = s.substring(i, i + t.length());

            HashMap<Character, Integer> mapS = new HashMap<>();
            HashMap<Character, Integer> mapT= new HashMap<>();

            for(Character c : sub.toCharArray()) {
                mapS.put(c, mapS.getOrDefault(c, 0) + 1);
            }

            for(Character c : t.toCharArray()) {
                mapT.put(c, mapT.getOrDefault(c, 0) + 1);
            }

            if(mapS.equals(mapT)) result++;
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[s] : " + s);
        System.out.println("[t] : " + t);
        System.out.println("[작업 결과] : " + result);
    }
}
