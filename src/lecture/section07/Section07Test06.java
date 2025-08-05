package lecture.section07;

import common.CodingTest;

import java.util.*;
import java.util.stream.Collectors;

// 06. 장난꾸러기
public class Section07Test06 extends CodingTest {

    private final int N = 9;
    private final int[] ARR = {120, 125, 152, 130, 135, 135, 143, 127, 160};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, ARR));
        testCases.add(() -> createCase2(N, ARR));

        // 보다 최적의 방법
        testCases.add(() -> createCase3(N, ARR));
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    private void createCase(int n, int[] arr) {

        Queue<Integer> queue = new PriorityQueue<>();
        ArrayList<Integer> resultArr = new ArrayList<>();

        for(int height : arr) {             // O(N)
            queue.add(height);              // O(1)
        }

        // O(N log N)
        for(int i=0; i <n; i++) {               // O(N)

            if(queue.isEmpty()) break;

            if(arr[i] != queue.poll()) {        // O(log N) => PriorityQueue

                // index = 0 => 키 순서 = 1
                resultArr.add(i + 1);
            }
        }

        String result = resultArr.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    private void createCase2(int n, int[] arr) {

        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> resultArr = new ArrayList<>();

        for(int height : arr) {             // O(N)
            queue.add(height);              // O(1)
        }

        queue = queue.stream()              // O(n log n)
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));

        // O(N)
        for(int i=0; i <n; i++) {           // O(N)

            if(queue.isEmpty()) break;

            if(arr[i] != queue.poll()) {    // O(1)

                // index = 0 => 키 순서 = 1
                resultArr.add(i + 1);
            }
        }

        String result = resultArr.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    private void createCase3(int n, int[] arr) {

        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);             // O(N log N) -> 상수 계수 작다고함 (원시타입에 대한 sort라서)

        ArrayList<Integer> resultArr = new ArrayList<>();

        for(int i=0; i <n; i++) {

            if(arr[i] != sortedArr[i]) {

                // index = 0 => 키 순서 = 1
                resultArr.add(i + 1);
            }
        }

        String result = resultArr.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }
}
