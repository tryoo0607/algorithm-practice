package lecture.section03;

import common.CodingTest;

import java.util.Arrays;

// 10. 봉우리
public class Section03Test10 extends CodingTest {

    private final int N = 5;

    private final int[][] GRID = {
            {5, 3, 7, 2, 3},
            {3, 7, 1, 6, 1},
            {7, 2, 5, 3, 4},
            {4, 3, 6, 4, 1},
            {8, 7, 3, 5, 2}
    };

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, GRID));

        // 권장 풀이
        testCases.add(() -> createCase2(N, GRID));
    }

    private int getGridValue(int[][] grid, int n, int i, int j) {
        if( i < 0 || j < 0 || i > n-1 || j > n-1) {
            return 0;
        } else {

            return grid[i][j];
        }
    }

    /*
        시간복잡도 : O(N^2)
        공간복잡도 : O(1)
    */
    private void createCase(int n, int[][] grid){

        int count=0;
        boolean isPeak;
        for(int i=0; i < n; i++) {
            for(int j=0; j < n; j++) {
                isPeak = true;
                int target = grid[i][j];
                if(target <= getGridValue(grid, n, i, j-1)
                || target <= getGridValue(grid, n, i, j+1)
                || target <= getGridValue(grid, n, i-1, j)
                || target <= getGridValue(grid, n,i+1, j)) {
                    isPeak = false;
                }

                if(isPeak) count++;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[grid] : ");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("[작업 결과] : " + count);
    }

    // 권장 풀이
    private void createCase2(int n, int[][] grid){

        // 상하좌우
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {-1, 0, 1, 0};

        int count=0;

        for(int i=0; i < n; i++){
            for(int j=0; j < n; j++){
                boolean isPeak = true;
                int current = grid[i][j];

                for(int d=0; d < 4; d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    // 가장자리는 0으로 간주
                    int neighbor = 0;
                    if(nx >= 0 && ny >=0 && nx<n && ny <= 0) {
                        neighbor = grid[nx][ny];
                    }

                    if(neighbor >= current) {
                        isPeak = false;
                        break;
                    }
                }

                if(isPeak) count++;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[grid] : ");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("[작업 결과] : " + count);
    }
}
