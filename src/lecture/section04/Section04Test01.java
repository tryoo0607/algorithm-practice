package lecture.section04;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;

// 01. 두 배열 합치기
public class Section04Test01 extends CodingTest {
    private final int N = 3;
    private final int[] ARR_N = {1, 3, 5};

    private final int M = 5;
    private final int[] ARR_M = {2, 3, 6, 7, 9};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, ARR_N, M, ARR_M));

        // 권장방법
        testCases.add(() -> createCase2(N, ARR_N, M, ARR_M));
    }

    /*
        시간복잡도 : O((N+M) * log(N+M))
        공간복잡도 : O(N+M)
    */
    private void createCase(int n, int[] arrN, int m, int[] arrM){

        int[] arr = new int[n+m];

        // 복사 대상, 복사 시작할 index, 붙여넣기 대상, 붙여넣기 시작할 index, 붙여넣기할 전체 길이
        System.arraycopy(arrN, 0, arr, 0, n);
        System.arraycopy(arrM, 0, arr, n, m);

        /*
            # toArray()
            - 기본형스트림 / 참조형 스트림에도 모두 있음
            - IntStream -> int[] / DoubleStream -> double[] / 참조형 스트림 -> Object[] 로 반환

            # 기본형 스트림 toArray() 예시
            - String[] arr = Stream.of("a", "b", "c").toArray(String[]::new);
        */
        // 시간복잡도 : O((N+M) * log(N+M))
        int[] result = Arrays.stream(arr)
                .sorted()                   // O((N+M) * log (N+M))
                .toArray();                 // O(N+M)

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }

    /*
        시간복잡도 : O(N+M)
        공간복잡도 : O(N+M)
    */
    // 주어진 배열들이 현재 정렬되어 있다는 조건이 있으므로 다음과 같이 풀 수 있음
    private void createCase2(int n, int[] arrN, int m, int[] arrM){

        ArrayList<Integer> result = new ArrayList<>();

        int pointN = 0;
        int pointM = 0;

        // 이렇게 되면 마지막에 어느 한쪽 배열이 남게 됨
        while(pointN < n && pointM < m) {
            if(arrN[pointN] > arrM[pointM]) result.add(arrN[pointN++]);
            else result.add(arrM[pointM++]);
        }

        while(pointN < n) {
            result.add(arrN[pointN++]);
        }

        while(pointM < m) {
            result.add(arrM[pointM++]);
        }


        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[작업 결과] : " + result);
    }
}
