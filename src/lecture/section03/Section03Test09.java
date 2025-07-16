package lecture.section03;

import common.CodingTest;

import java.util.Arrays;

// 09. 격자판 최대합
public class Section03Test09 extends CodingTest {

    private final int N = 5;
    private final int[][] GRID = {
        {10, 13, 10, 12, 15},
        {12, 39, 30, 23, 11},
        {11, 25, 50, 53, 15},
        {19, 27, 29, 37, 27},
        {19, 13, 30, 13, 19}
    };

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, GRID));
    }

    /*
        시간복잡도 : O(N^2)
        공간복잡도 : O(N)
    */
    // 대각선은 따로 분리하는 편이 좋을 듯
    private void createCase(int n, int[][] grid) {

        int max = 0;
        int diagonalSum = 0;
        int reverseDiagonalSum = 0;

        for(int i=0; i < n; i++){

            int rowSum = 0;
            int columnSum = 0;
            for(int j=0; j <n; j++) {
                rowSum+=grid[i][j];
                columnSum+=grid[j][i];
            }

            diagonalSum += grid[i][i];
            reverseDiagonalSum += grid[(n - 1) - i][i];

            max = Math.max(max, Math.max(rowSum, columnSum));
        }

        max = Math.max(max, Math.max(diagonalSum, reverseDiagonalSum));


        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[grid] : ");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("[작업 결과] : " + max);
    }
}
