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
}
