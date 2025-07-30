package lecture.section06;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

// 03. 크레인 인형뽑기(카카오)
public class Section06Test03 extends CodingTest {
    private final int N = 5;
    private final int[][] GRID = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 3},
            {0, 2, 5, 0, 1},
            {4, 2, 4, 4, 2},
            {3, 5, 1, 3, 1}
    };

    private final int M = 8;
    private final int[] MOVES = {1, 5, 3, 5, 1, 2, 1, 4};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, GRID, M, MOVES));

        // 조금 더 개선한 코드
        testCases.add(() -> createCase2(N, GRID, M, MOVES));
    }

    /*
        시간복잡도 : O(N^2 + M) 
        공간복잡도 : O(M*N)
    */
    private void createCase(int n, int[][] grid, int m, int[] moves) {

        ArrayList<Stack<Integer>> craneList = new ArrayList<>();
        int removedCount = 0;

        // O(N^2)
        for(int col=0; col < n; col++) {
            Stack<Integer> puppetStack = new Stack<>();

            for(int row=n-1; row >=0; row--) {
                int puppet = grid[row][col];
                if(puppet != 0) puppetStack.push(puppet);
            }

            craneList.add(puppetStack);
        }

        Stack<Integer> resultStack = new Stack<>();
        for(Integer move : moves) {
            int index = move - 1;

            Stack<Integer> puppetStack = craneList.get(index);
            
            if(puppetStack.isEmpty()) continue;

            Integer caught = puppetStack.pop();
            Integer last = !resultStack.isEmpty() ? resultStack.pop() : null;

            if(!caught.equals(last)) {
                resultStack.push(last);
                resultStack.push(caught);
            } else {
                removedCount+=2;
            }
        }


        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[grid] : ");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("[m] : " + m);
        System.out.println("[moves] : " + Arrays.toString(moves));
        System.out.println("[작업 결과] : " + removedCount);
    }

    /*
        시간복잡도 : O(N * M)
        공간복잡도 : O(N^2)
    */
    private void createCase2(int n, int[][] grid, int m, int[] moves) {

        int[][] originalGrid = grid;
        Stack<Integer> bucket = new Stack<>();
        int removedCount = 0;
        
        for(Integer move : moves) {
            
            int col = move - 1;

            // 위에서 아래로 순회하면서 인형 있는지 조회
            for(int row = 0; row < n; row++) {
                int puppet = grid[row][col];

                if(puppet == 0) continue;
                // 뽑은 것은 빈칸 처리
                grid[row][col] = 0;

                if(!bucket.isEmpty() && bucket.peek().equals(puppet)) {
                    bucket.pop();
                    removedCount+=2;
                } else {
                    bucket.push(puppet);
                }

                break;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[grid] : ");
        for (int[] row : originalGrid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("[m] : " + m);
        System.out.println("[moves] : " + Arrays.toString(moves));
        System.out.println("[작업 결과] : " + removedCount);
    }
}
