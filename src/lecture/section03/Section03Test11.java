package lecture.section03;

import common.CodingTest;

import java.util.Arrays;

// 11.임시반장 정하기
public class Section03Test11 extends CodingTest {

    private final int N = 5;
    private final int[][] GRID = {
            {2, 3, 1, 7, 3},
            {4, 1, 9, 6, 8},
            {5, 5, 2, 4, 4},
            {6, 5, 2, 6, 7},
            {8, 4, 2, 2, 2}
    };

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, GRID));

        // 보다 정확한 풀이
        testCases.add(() -> createCase2(N, GRID));
    }

    /*
        문제 조건과 맞지않음
        예를들어 3번과 4번이 2학년때, 3학년 때도 같은 반이었다면 -> 한번만 카운트 됨
        그런데 이 풀이는 2번 카운트 된다는 점이 문제임
    */
    private void createCase(int n, int[][] grid) {
        int[][] resultGrid = new int[n][n];

        for(int col=0; col<n; col++) {
            int[] countArr = new int[10];
            Arrays.fill(countArr, 0);

            for(int row=0; row<n; row++) {
                countArr[grid[row][col]]++;
            }

            for(int row=0; row<n; row++) {
                resultGrid[row][col] = countArr[grid[row][col]];
            }
        }

        int max = 0;
        int result = 0;
        for(int row=0; row<n; row++) {
            int sum = 0;
            for(int col=0; col<n; col++) {
                sum += resultGrid[row][col];
            }

            if(sum > max) {
                max = sum;
                result = row + 1;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[grid] : ");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N^2)
        공간복잡도 : O(1)
    */
    private void createCase2(int n, int[][] grid){


        int maxCount = 0;
        int result = 0;

        // 학생 A(비교 대상)
        for(int i=0; i < n; i++) {
            int count=0;

            // 학생 B(다른 비교대상)
            for(int j=0; j < n; j++) {
                if(i == j) continue;

                // 학년을 나타내는 index
                for(int k=0; k<5; k++) {
                    if(grid[i][k] == grid[j][k]) {
                        count++;

                        // 즉, 학생  A랑 학생 B는 같은 반 이었으므로 이 이후는 더 이상 비교해볼 필요 없음
                        break;
                    }
                }
            }

            if(count > maxCount) {
                maxCount = count;
                result = i + 1;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[grid] : ");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("[작업 결과] : " + result);
    }
}
