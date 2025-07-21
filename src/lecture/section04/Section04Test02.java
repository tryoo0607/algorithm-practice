package lecture.section04;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

// 02. 공통 원소 구하기
public class Section04Test02 extends CodingTest {

    private final int N = 5;
    private final int[] ARR_N = {1, 3, 9, 5, 2};

    private final int M = 5;
    private final int[] ARR_M = {3, 2, 5, 7, 8};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, ARR_N, M, ARR_M));
        // 보다 효율적인 방식
        testCases.add(() -> createCase(N, ARR_N, M, ARR_M));
    }

    /*
        시간복잡도 : O(N + M + K log K)
        공간복잡도 : O(M + K)
    */
    private void createCase(int n, int[] arrN, int m, int[] arrM) {

        // 공간복잡도 : O(K) => K는 공통 원소 개수
        ArrayList<Integer> resultArr = new ArrayList<>();

        // boxed() -> IntStream을 Stream<Integer> 로 변환
        // ArrayList는 Integer를 담아야하지만 int[]는 기본형인 int이기 때문에 Integer로 변환이 필요함
        // 공간복잡도 : O(M)
        ArrayList<Integer> convertedM = Arrays.stream(arrM)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));                      // O(M)


        for(int num : arrN) {                                                           // O(N)
            if(convertedM.contains(num)) {                                              // O(M)
                resultArr.add(num);                                                     // O(1)
            }
        }

        // O(K log K)
        String result = resultArr.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[arrN] : " + Arrays.toString(arrN));
        System.out.println("[n] : " + n);
        System.out.println("[arrM] : " + Arrays.toString(arrM));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N + M + K log K)
        공간복잡도 : O(M + K)
    */
    // 보다 나은 방법
    // 참고로 입력 값이 정렬되어있지 않으면 아래 방식이 낫고 / 입력이 정렬되어 있으면 투포인터 방식으로 작성하는 편이 좋음
    private void createCase2(int n, int[] arrN, int m, int[] arrM) {
        // 탐색 대상은 ArrayList보다는 Set으로 만들어서 탐색하는 것이 빠름
        // Array -> 선형 탐색 (contains 복잡도 O(N))
        // Set -> 해쉬 탐색 (contains 복잡도 O(1))
        Set<Integer> setM = Arrays.stream(arrM)
                .boxed()
                .collect(Collectors.toSet()); // HashSet 사용

        ArrayList<Integer> resultArr = new ArrayList<>();

        for (int num : arrN) {                  // O(M)
            if (setM.contains(num)) {           // O(1)
                resultArr.add(num);             // O(1)
            }
        }

        // O(K log K)
        String result = resultArr.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[arrN] : " + Arrays.toString(arrN));
        System.out.println("[n] : " + n);
        System.out.println("[arrM] : " + Arrays.toString(arrM));
        System.out.println("[작업 결과] : " + result);
    }
}
