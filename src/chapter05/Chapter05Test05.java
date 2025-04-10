package chapter05;

import model.CodingTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


// 문제 05. 행렬의 곱셈
public class Chapter05Test05 extends CodingTest {

    private final String ARR1 = "arr1";
    private final String ARR2 = "arr2";

    Map<String, int[][]> arrays1 = new HashMap<>() {{
        put(ARR1, new int[][]{
                {1, 4},
                {3, 2},
                {4, 1}
        });
        put(ARR2, new int[][]{
                {3, 3},
                {3, 3}
        });
    }};

    Map<String, int[][]> arrays2 = new HashMap<>() {{
        put(ARR1, new int[][]{
                {2, 3, 2},
                {4, 2, 4},
                {3, 1, 4}
        });
        put(ARR2, new int[][]{
                {5, 4, 3},
                {2, 4, 1},
                {3, 1, 1}
        });
    }};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(arrays1));
        testCases.add(() -> createCase(arrays2));
    }

    /*
      시간 복잡도 : O(N^3)
    */
    public void createCase(Map<String, int[][]> arrayMap) {

        int[][] arr1 = arrayMap.get(ARR1);
        int[][] arr2 = arrayMap.get(ARR2);

        int r1 = arr1.length;       // A 행의 수
        int c1 = arr1[0].length;    // A 열의 수 = B 행의 수
        int r2 = arr2.length;
        int c2 = arr2[0].length;    // B 열의 수

        int[][] results = new int[r1][c2];  // 결과 행렬: r1 x c2

        /*
          - 행렬 A의 크기: r1 x c1
          - 행렬 B의 크기: r2 x c2
          - 곱셈 조건: c1 == r2 이어야 함 (A의 열 수 == B의 행 수)
          - 결과 행렬의 크기: r1 x c2

          곱셈 방식:
            result[i][j] = A의 i행과 B의 j열의 내적
                         = A[i][0] * B[0][j] + A[i][1] * B[1][j] + ... + A[i][c1-1] * B[c1-1][j]
        */

        // 행렬 곱셈 수행
        // A의 i행과 B의 j열을 내적하여 results[i][j]에 저장
        // O(N^3)
        for(int i = 0; i < r1; i++) {                           // O(N) // A의 행 순회
            for(int j=0; j < c2; j++) {                         // O(N) // B의 열 순회
                for(int k=0; k < c1; k++ ) {                    // O(N) // A의 열 = B의 행
                    results[i][j] += arr1[i][k] * arr2[k][j];   // O(1)
                }
            }
        }

        System.out.println("[작업 이전] : ");
        arrayMap.forEach((key, value) -> {
            System.out.println(key + " : " + Arrays.deepToString(value));
        });
        System.out.println("[작업 결과] : " + Arrays.deepToString(results));
    }
}
