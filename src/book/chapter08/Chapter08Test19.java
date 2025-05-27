package book.chapter08;

import common.CodingTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// 문제 19. 완주하지 못한 선수 *
public class Chapter08Test19 extends CodingTest {

    private final String PARTICIPANT = "participant";
    private final String COMPLETION = "completion";

    private final HashMap<String, String[]> MAP1 = new HashMap<>(){{
        put(PARTICIPANT, new String[]{"leo", "kiki", "eden"});
        put(COMPLETION, new String[]{"eden", "kiki"});
    }};

    private final HashMap<String, String[]> MAP2 = new HashMap<>(){{
        put(PARTICIPANT, new String[]{"marina", "josipa", "nikola", "vinko", "filipa"});
        put(COMPLETION, new String[]{"josipa", "filipa", "marina", "nikola"});
    }};

    private final HashMap<String, String[]> MAP3 = new HashMap<>(){{
        put(PARTICIPANT, new String[]{"mislav", "stanko", "mislav", "ana"});
        put(COMPLETION, new String[]{"stanko", "ana", "mislav"});
    }};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(MAP1));
        testCases.add(() -> createCase(MAP2));
        testCases.add(() -> createCase(MAP3));
    }

    /* 동명 이인에 대한 처리를 하지 못한 예제*/
    private void createCaseBefore(HashMap<String, String[]> map) {
        String[] participant = map.get(PARTICIPANT);
        String[] completion = map.get(COMPLETION);


        int expected = participant.length;
        float factor = 0.75f;
        int cap = (int) (expected / factor) + 1;

        HashSet<String> hashSet = new HashSet<>(cap, factor);
        HashSet<String> result = new HashSet<>(cap, factor);


        hashSet.addAll(List.of(completion));

        for(String part : participant) {

            if(!hashSet.contains(part)) {
                result.add(part);
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[participant] : " + Arrays.toString(participant));
        System.out.println("[completion] : " + Arrays.toString(completion));
        System.out.println("[작업 결과] : " + result);
    }


    /*
        시간 복잡도 : O(N) + O(N) => O(N)
        공간 복잡도 : O(N)
    */
    private void createCase(HashMap<String, String[]> map) {
        String[] participant = map.get(PARTICIPANT);
        String[] completion = map.get(COMPLETION);


        int expected = participant.length;
        float factor = 0.75f;
        int cap = (int) (expected / factor) + 1;
        HashMap<String, Integer> hashMap = new HashMap<>(cap, factor);          // 공간복잡도 : O(N)

        for(String comp : completion) {                                         // O(K) => O(N -1) => O(N)
            hashMap.put(comp, hashMap.getOrDefault(comp, 0) + 1);   // O(1)
        }

        String result = null;
        for(String part : participant) {                                        // O(N)
            if(hashMap.getOrDefault(part, 0) == 0) {                // O(1)
                result = part;
                break;
            }

            hashMap.put(part, hashMap.get(part) - 1);                           // O(1)
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[participant] : " + Arrays.toString(participant));
        System.out.println("[completion] : " + Arrays.toString(completion));
        System.out.println("[작업 결과] : " + result);
    }
}
