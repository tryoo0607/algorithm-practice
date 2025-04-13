package book.chapter06;

import common.CodingTest;

import java.util.Arrays;
import java.util.Stack;

// 문제 14. 표 편집 *****
public class Chapter06Test14 extends CodingTest {

    private final int N1 = 8;
    private final int K1 = 2;
    private final String[] CMD1 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};

    private final int N2 = 8;
    private final int K2 = 2;
    private final String[] CMD2 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N1, K1, CMD1));
        testCases.add(() -> createCase(N2, K2, CMD2));
    }

    private void createCaseBefore(int n, int k, String[] cmd) {
        int currentRow = k;
        Stack<Integer> stack = new Stack<>();

        for(String commend : cmd) {

            if(commend.startsWith("U")) {
                String[] str = commend.split(" ");

                int targetRows = currentRow - Integer.parseInt(str[1]);

                if(targetRows < 0) {
                    currentRow = 0;
                } else {
                    currentRow = targetRows;
                }
            } else if (commend.startsWith("D")) {
                String[] str = commend.split(" ");

                int targetRows = currentRow + Integer.parseInt(str[1]);

                if(targetRows > n) {
                    currentRow = n;
                } else {
                    currentRow = targetRows;
                }
            } else if (commend.equals("C")) {
                stack.push(currentRow);
            } else if (commend.equals("Z")) {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }

        String[] arr = new String[n];
        Arrays.fill(arr, "O");

        for(Integer idx : stack) {
            arr[idx] = "X";
        }

        String result = String.join("", arr);

        System.out.println("[작업 이전] : ");
        System.out.println("n : " + n);
        System.out.println("k : " + k);
        System.out.println("cmd : " + Arrays.toString(cmd));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간 복잡도 : O(N)
        공간 복잡도 : O(N)
    */
    private void createCase(int n, int k, String[] cmd) {
        int[] up = new int[n + 2];                  // 공간 복잡도 : O(N)
        int[] down = new int[n + 2];                // 공간 복잡도 : O(N)

        for(int i = 0; i < n + 2; i++) {            // O(N)
            up[i] = i - 1;                          // O(1)
            down[i] = i + 1;                        // O(1)
        }

        // 현재 up / down의 범위는 -1 ~ n + 1;
        // 위 아래로 임시 공간 생김
        // k 위로 -1이라는 임시 공간이 생겼으므로 초기 위치를 조정하기 위해 + 1을 해줌
        // 즉, k = 행 + 1;
        k++;

        // 삭제 행
        Stack<Integer> deletedRow = new Stack<>();      // 공간 복잡도 : O(M)

        for(String commend : cmd) {                     // O(M)
            if (commend.equals("C")) {
                // k = 현재 행 위치 -> 삭제
                deletedRow.push(k);                     // O(1)

                // 실제 삭제하는 대신 상대적 위치를 조정

                // 현재 행 삭제 시  -> 현재 행의 위(up[k])의 down값은 현재 행의 down 값이 됨 (한 행이 지워졌으므로)
                down[up[k]] = down[k];                  // O(1)

                // 현재 행 삭제 시  -> 현재 행의 아래(down[k])의 up값은 현재 행의 up 값이 됨 (한 행이 지워졌으므로)
                up[down[k]] = up[k];                    // O(1)

                // 현재 행을 삭제한 행 아래로 내림
                // 이 때, 삭제한 행이 마지막행 보다 아래면 아래가 아닌 위로 올림
                k = n < down[k] ? up[k] : down[k];      // O(1)
            } else if (commend.equals("Z")) {
                int restoreIdx = deletedRow.pop();      // O(1)

                // 직전 행만 복구함 -> 따라서 직전 행의 위, 아래에 해당되는 행의 up, down 값만 다시 복구하면 됨

                // 복구 행의 위(up[restoreIdx)는 복구 행이 돌아왔으니 down 값이 다시 복구 행(restoreIdx)가 됨
                down[up[restoreIdx]] = restoreIdx;      // O(1)

                // 복구 행의 아래(down[restoreIdx)는 복구 행이 돌아왔으니 up 값이 다시 복구 행(restoreIdx)가 됨
                up[down[restoreIdx]] = restoreIdx;      // O(1)
            } else {
                String[] s = commend.split(" ");  // O(1) // 공간복잡도 : O(1)
                int x = Integer.parseInt(s[1]);
                String cmdStr = s[0];

                // 이동하기 위해선 그 행의 위, 아래 값의 현재 index로 이동해야함
                // 따라서 한번에 up[k + x] 형태로 이동할 수는 없고 up / down에서 하나씩 찾아가면서 이동해야함
                for(int i=0; i < x; i++) {                      // 최대 : O(10000) => O(1) => 문제 조건에 모든 X의 합이 1,000,000을 넘지 않는다고 했음
                    k = cmdStr.equals("U") ? up[k] : down[k];
                }
            }
        }

        char[] arr = new char[n];
        Arrays.fill(arr, 'O');

        for(Integer idx : deletedRow) {     // O(N) (최악의 경우)
            // 현재 deletedRow에 저장된 것은 행 + 1 (임시 공간 추가 반영한 수치);
            // + 1 된 값에 대한 제거가 필요함
            arr[idx - 1] = 'X';
        }

        String result = new String(arr);            // 공간 복잡도 : O(N) (최악의 경우)

        System.out.println("[작업 이전] : ");
        System.out.println("n : " + n);
        System.out.println("k : " + k);
        System.out.println("cmd : " + Arrays.toString(cmd));
        System.out.println("[작업 결과] : " + result);
    }
}
