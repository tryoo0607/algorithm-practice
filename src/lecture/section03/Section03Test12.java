package lecture.section03;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;

// 12. 멘토링
public class Section03Test12 extends CodingTest {
    private final int N = 4;
    private final int M = 3;
    private final int[][] GRID = {
            {3, 4, 1, 2},
            {4, 3, 2, 1},
            {3, 1, 4, 2}
    };

    @Override
    public void addCases() {

        testCases.add(() -> createCase2(N, M, GRID));
    }

    // 잘못된 풀이
    // 이럴 경우 양 옆에 있는 경우만 비교하게 됨
    // 그리고 주어진 배열은 등수가 아니라 점수가 높은 학생 순서임
    private void createCase(int n, int m, int[][] grid){

        int result = 0;
        for(int col=0; col<n-1; col++) {
            int[] higher = new int[m];

            for(int row=0; row<m; row++) {
                higher[row] = grid[row][col] < grid[row][col + 1] ? col : col+1;
            }

            boolean isMentor = true;
            for(int i=0; i < m - 1; i++) {
                if (higher[i] != higher[i + 1]) {
                    isMentor = false;
                    break;
                }
            }

            if(isMentor) result++;
        }
    }

    /*
        시간복잡도: O(N^2 * M)
        공간 복잡도 : O(N * M)
    */
    private void createCase2(int n, int m, int[][] grid){

        int result = 0;

        int[][] rank = new int[m][n];
        // 등수 테이블로 바꾸기
        for(int k=0; k<m; k++) {
            for(int i=0; i<n; i++) {
                int studentNumber = grid[k][i];
                // i == grid에서의 index == 실제 등수
                rank[k][studentNumber -1] = i;
            }
        }


        // 멘토 후보
        for(int i=0; i<n; i++) {
            // 멘티 후보
            for(int j=0; j<n; j++) {
                if(i == j) continue;

                boolean isMentor = true;

                // 시험마다 비교
                for(int k=0; k<m; k++) {
                    if(rank[k][i] > rank[k][j]) {
                        isMentor = false;
                        break;
                    }
                }

                if(isMentor) result++;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[n] : " + m);
        System.out.println("[grid] : ");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("[작업 결과] : " + result);
    }
}
