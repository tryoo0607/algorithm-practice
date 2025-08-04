package lecture.section07;

import common.CodingTest;

import java.util.*;
import java.util.stream.Collectors;

// 04. LRU(캐시, 카카오 변형)
// LRU : Least Recently Used
public class Section07Test04 extends CodingTest {

    private final int S = 5;
    private final int N = 9;
    private final int[] ARR = {1, 2, 3, 2, 6, 2, 3, 5, 7};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(S, N, ARR));

        // 보다 최적의 풀이
        testCases.add(() -> createCase2(S, N, ARR));
    }

    /*
         시간복잡도 : O(N * S)
         공간복잡도 : O(N)
    */
    private void createCase(int s, int n, int[] arr) {

        Deque<Integer> caches = new LinkedList<>();

        for(int i=0; i<n; i++) {
            int target = arr[i];

            if(caches.contains(target)) {   // O(S)
                caches.remove(target);      // O(S)
                caches.addFirst(target);    // O(1)
                continue;
            }

            if(caches.size() >= s) {
                caches.removeLast();
            }

            caches.addFirst(target);
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[s] : " + s);
        System.out.println("[n] : " + n);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + caches);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    // 중복 x 처리하므로 속도 빠른 LinkedHashSet 사용
    // H
    private void createCase2(int s, int n, int[] arr) {

        LinkedHashSet<Integer> caches = new LinkedHashSet<>();

        for(int i=0; i<n; i++) {
            int target = arr[i];

            if(caches.contains(target)) {                           // O(1) -> HashSet이라서
                caches.remove(target);                              // O(1)
                caches.add(target);                                 // O(1)
                continue;
            }

            if(caches.size() >= s) {
                // LinkedHashSet -> getFirst는 java 21 이상부터 사용 가능
                int oldest = caches.iterator().next();              // O(1)
                caches.remove(oldest);                              // O(1)
            }

            caches.add(target);
        }

        List<Integer> resultList = new ArrayList<>(caches);
        Collections.reverse(resultList);

        String result = resultList.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[s] : " + s);
        System.out.println("[n] : " + n);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }
}
