package lecture.section05;

import common.CodingTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// 02. 아나그램(해쉬)
public class Section05Test02 extends CodingTest {

    private final String STR_A1 = "AbaAeCe";
    private final String STR_B1 = "baeeACA";

    private final String STR_A2 = "abaCC";
    private final String STR_B2 = "Caaab";

    @Override
    public void addCases() {

        // createCase -> 잘못된 방식
//        testCases.add(() -> createCase(STR_A1, STR_B1));
//        testCases.add(() -> createCase(STR_A2, STR_B2));

        // createCase2 -> iterator 활용 (개선 방식)
        testCases.add(() -> createCase2(STR_A1, STR_B1));
        testCases.add(() -> createCase2(STR_A2, STR_B2));

        // createCase3 -> 가장 효율적인 방식
        testCases.add(() -> createCase3(STR_A1, STR_B1));
        testCases.add(() -> createCase3(STR_A2, STR_B2));
    }

    // 잘못된 문법 (for문 돌리는 도중 remove하는 것이 문제) -> iterator로 전환
    private void createCase(String strA, String strB) {

        HashMap<Character, Integer> aMap = new HashMap<>();
        HashMap<Character, Integer> bMap = new HashMap<>();

        for(Character c : strA.toCharArray()) {

            Integer count = aMap.getOrDefault(c, 0);
            aMap.put(c, count + 1);
        }

        for(Character c : strB.toCharArray()) {

            Integer count = bMap.getOrDefault(c, 0);
            bMap.put(c, count + 1);
        }


        for(Map.Entry<Character,Integer> a : aMap.entrySet()) {

            Character key = a.getKey();
            Integer value = a.getValue();

            if(bMap.get(key).equals(value)) {
                aMap.remove(key);
                bMap.remove(key);
            } else {
                break;
            }
        }

        String result = aMap.isEmpty() && bMap.isEmpty() ? "YES" : "NO";

        System.out.println("[작업 이전] : ");
        System.out.println("[strA] : " + strA);
        System.out.println("[strB] : " + strB);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N) => 2N
    */
    private void createCase2(String strA, String strB) {

        HashMap<Character, Integer> aMap = new HashMap<>();
        HashMap<Character, Integer> bMap = new HashMap<>();

        for(Character c : strA.toCharArray()) {

            Integer count = aMap.getOrDefault(c, 0);
            aMap.put(c, count + 1);
        }

        for(Character c : strB.toCharArray()) {

            Integer count = bMap.getOrDefault(c, 0);
            bMap.put(c, count + 1);
        }


        Iterator<Map.Entry<Character, Integer>> iterator = aMap.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            Character key = entry.getKey();
            Integer value = entry.getValue();

            if(bMap.containsKey(key) && bMap.get(key).equals(value)) {
                iterator.remove();
                bMap.remove(key);
            }

            /*
                else {
                    break;
                }

                => 사용해도 상관은 없으나 프로그램 유지보수 관점에서는 불완전한 코드
            */
        }

        String result = aMap.isEmpty() && bMap.isEmpty() ? "YES" : "NO";

        System.out.println("[작업 이전] : ");
        System.out.println("[strA] : " + strA);
        System.out.println("[strB] : " + strB);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    private void createCase3(String strA, String strB) {

        boolean isAnagram = true;

        if (strA.length() != strB.length()) {
            isAnagram = false;
        } else {
            HashMap<Character, Integer> aMap = new HashMap<>();

            for(Character c : strA.toCharArray()) {

                Integer count = aMap.getOrDefault(c, 0);
                aMap.put(c, count + 1);
            }

            for(Character c : strB.toCharArray()) {

                if(!aMap.containsKey(c)) {
                    isAnagram = false;
                    break;
                }

                aMap.put(c, aMap.get(c) - 1);

                if(aMap.get(c) < 0) {
                    isAnagram = false;
                    break;
                }
            }
        }

        String result = isAnagram ? "YES" : "NO";

        System.out.println("[작업 이전] : ");
        System.out.println("[strA] : " + strA);
        System.out.println("[strB] : " + strB);
        System.out.println("[작업 결과] : " + result);
    }
}
