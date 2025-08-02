package lecture.section06;

import common.CodingTest;

import java.util.*;

// 08. 응급실
public class Section06Test08 extends CodingTest {

    private final int N1 = 5;
    private final int M1 = 2;
    private final int[] ARR1 = {60, 50, 70, 80, 90};

    private final int N2 = 6;
    private final int M2 = 3;
    private final int[] ARR2 = {70, 60, 90, 60, 60, 60};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N1, M1, ARR1));
        testCases.add(() -> createCase(N2, M2, ARR2));

        // 잘못된 풀이
        testCases.add(() -> createCase2(N1, M1, ARR1));
        testCases.add(() -> createCase2(N2, M2, ARR2));

        // 최적의 풀이
        testCases.add(() -> createCase3(N1, M1, ARR1));
        testCases.add(() -> createCase3(N2, M2, ARR2));
    }

    /*
        시간복잡도 : O(N^2)
        공간복잡도 : O(N)
    */
    private void createCase(int n, int m, int[] arr) {

        // LinkedHashMap은 삽입 순서를 반드시 유지해줌 -> 현재 알고리즘에서 필요
        HashMap<Integer, Integer> map = new LinkedHashMap<>();

        for(int i=0; i<n; i++) {
            map.put(i, arr[i]);
        }

        Queue<Map.Entry<Integer, Integer>> queue = new LinkedList<>(map.entrySet());

        System.out.println(queue);

        int count = 0;
        while(!queue.isEmpty()) {
            Map.Entry<Integer, Integer> target = queue.poll();

            int targetValue = target.getValue();
            int max = targetValue;
            for(Map.Entry<Integer, Integer> entry : queue) {
                int value = entry.getValue();
                if(value > max) {
                    max = value;
                }
            }

            if(max > targetValue) {
                queue.add(target);
                continue;
            }

            count++;

            if(target.getKey() == m) {
                break;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[count] : " + count);
    }

    // PriorityQueue 사용하려했지만 이건 문제 위반
    // 반드시 뒤로 보내라 라는 시뮬레이션 문제
    private void createCase2(int n, int m, int[] arr) {

        HashMap<Integer, Integer> map = new LinkedHashMap<>();
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for(int i=0; i<n; i++) {
            map.put(i, arr[i]);
        }

        queue.addAll(map.entrySet());

        int count = 0;
        while(!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            count++;

            if(entry.getKey() == m) {
                break;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[count] : " + count);
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    private void createCase3(int n, int m, int[] arr) {

        Queue<int[]> queue = new LinkedList<>();
        Queue<Integer> riskQueue = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<n; i++) {
            queue.add(new int[]{i, arr[i]});
            riskQueue.add(arr[i]);
        }

        /*
            PriorityQueue에서 poll의 시간복잡도는 O(log N)
            현재 알고리즘에서는 모든 리스크를 봐야하므로 N을 곱한 O(N log N)이 이 모든 poll을 합친 복잡도가 됨

            참고로 일반 Queue는 O(1)
        */
        int count = 0;
        Integer risk = !riskQueue.isEmpty() ? riskQueue.poll() : null;
        while(!queue.isEmpty()) {
            if(risk == null) break;

            int[] target = queue.poll();

            if(target[1] != risk) {
                queue.add(target);
                continue;
            }

            count++;
            risk = riskQueue.poll();
            if(target[0] == m) {
                break;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[count] : " + count);
    }
}
