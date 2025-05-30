package book.chapter08;

import common.CodingTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 문제 23. 신고 결과 받기 **
public class Chapter08Test23 extends CodingTest {

    private final String[] ID_LIST1 = {"muzi", "frodo", "apeach", "neo"};
    private final String[] REPORT1 = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
    private final int K1 = 2;

    private final String[] ID_LIST2 = {"con", "ryan"};
    private final String[] REPORT2 = {"ryan con", "ryan con", "ryan con"};
    private final int K2 = 3;


    @Override
    public void addCases() {
        testCases.add(() -> createCase(ID_LIST1, REPORT1, K1));
        testCases.add(() -> createCase(ID_LIST2, REPORT2, K2));
    }

    /*
        시간 복잡도 : O(N) // 정확히는 O(N + M) 다만 M보다 N이 월등히 커서 O(N)으로 간주한 것일 뿐
        공간 복잡도 : O(N) // 정확히는 O(N + M) 다만 M보다 N이 월등히 커서 O(N)으로 간주한 것일 뿐
    */
    private void createCase(String[] idList, String[] report, int k) {

        int expected = idList.length;
        float factor = 0.75f;
        int cap = (int) (expected / factor) + 1;

        /*
            report 길이를 N (최악 200,000)
            id list 길이를 M (최악 1,000)
        */
        HashMap<String, Integer> idMap = new HashMap<>(cap, factor);            // 공간 복잡도 : O(1) // 정확히는 O(M)
        HashMap<String, Set<String>> reportMap = new HashMap<>(cap, factor);    // 공간 복잡도  : O(N) // 정확히는 O(M + N)

        for(int i = 0; i < idList.length; i++) {                // O(1) // 정확히는 O(M) : idList는 1000개 이하이므로 N보다 한참 작아서 O(1) 취급
            idMap.put(idList[i], i);                            // O(1)
        }

        for(String log : report) {                              // O(N)
            String reportFrom = log.split(" ")[0];
            String reportTo = log.split(" ")[1];

            if(!reportMap.containsKey(reportTo)) {
                reportMap.put(reportTo, new HashSet<>());
            }

            reportMap.get(reportTo).add(reportFrom);
        }

        Stream<Map.Entry<String, Set<String>>> banSet = reportMap.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= k);                         // O(1) : 본래 O(M)이지만 1000 이하라 O(1) 취급

        int[] result = new int[idList.length];
        /*
            이 부분 정확한 시간복잡도는 O(N + M)
            banSet.forEach -> 정확히는 모든 id 수만큼 반복하므로 O(M)
            그리고 내부의 set.forEach -> 모든 set.forEach를 더해봤자 report수만큼 반복됨 => 따라서 O(N)
            즉, 내부 루프는 모두 합치면 O(N)만큼 되는거고 바깥 루프는 O(M) 인 상황
            => O(N + M)이 됨

            => 이걸 만약 O(N * M)이라 적기 위해서는
            바깥의 루프가 O(M)만큼 반복되는 상황에서
            내부 루프가 고정적으로 O(N)씩 반복되야함 (모두 합쳐서 O(N)이 아니라 각각이 O(N)이어야 함
        */
        banSet.forEach(entry -> {                                                       // O(1) : 1000보다 무조건 작은 수
           Set<String> set = entry.getValue();                                          // O(1)

            set.forEach(from -> {                                                       // O(1)
               result[idMap.get(from)] += 1;
            });
        });

        System.out.println("[작업 이전] : ");
        System.out.println("[idList] : " + Arrays.toString(idList));
        System.out.println("[report] : " + Arrays.toString(report));
        System.out.println("[k] : " + k);
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }
}
