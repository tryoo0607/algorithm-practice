package book.chapter06;

import common.CodingTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

// 문제 13. 크레인 인형 뽑기 게임 **
public class Chapter06Test13 extends CodingTest {

    private final int[][] BOARD1 = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 3},
            {0, 2, 5, 0, 1},
            {4, 2, 4, 4, 2},
            {3, 5, 1, 3, 1}
    };

    private final int[] MOVES1 = {1, 5, 3, 5, 1, 2, 1, 4};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(BOARD1, MOVES1));
    }

    /*
        시간 복잡도 : O(N^2 + M)
        공간 복잡도 : O(N^2 + M)
    */
    private void createCase(int[][] board, int[] moves) {

        HashMap<Integer, Stack<Integer>> boardMap = new HashMap<>();  // 공간: O(N) (key 수 만큼)

        for(int i = 0; i < 5; i++) {                                   // O(N)
            Stack<Integer> boardStack = new Stack<>();                 // 최대 O(N) 공간 사용 (행별 stack)
            for(int j = 4; j >= 0; j--) {                              // O(N)
                int target = board[j][i];                              // O(1)
                if(target != 0) {
                    boardStack.push(target);                           // O(1)
                }
            }
            boardMap.put(i + 1, boardStack);                           // 총 N개의 Stack → 공간 O(N^2)
        }

        Stack<Integer> catchStack = new Stack<>();                     // 공간: O(M) (최악 시 M개 쌓임)
        int result = 0;

        for (int move : moves) {                                       // O(M)
            Stack<Integer> targetRow = boardMap.get(move);             // O(1)

            if (!targetRow.isEmpty()) {
                int catchItem = targetRow.pop();                       // O(1)

                if (!catchStack.isEmpty() && catchStack.peek().equals(catchItem)) {
                    catchStack.pop();                                  // O(1)
                    result += 2;                                       // O(1)
                } else {
                    catchStack.push(catchItem);                        // O(1)
                }
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("board : " + Arrays.deepToString(board));
        System.out.println("moves : " + Arrays.toString(moves));
        System.out.println("[작업 결과] : " + result);
    }
}
