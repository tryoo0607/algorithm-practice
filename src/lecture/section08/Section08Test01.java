package lecture.section08;

import common.CodingTest;

import java.util.Arrays;

// 01. 재귀함수(스택프레임)
public class Section08Test01 extends CodingTest {

    private final int N = 3;

    @Override
    public void addCases() {

        testCases.add(() -> DFS(N));

        // 올바른 풀이
        testCases.add(() -> DFS2(N));
    }


    // 이러면 n, n-1, n-2 ... 처럼 역순으로 출력됨
    private void DFS(int n) {

        System.out.print(n + " ");

        if(n > 1) {
            DFS(n - 1);
        }
    }

    private void DFS2(int n) {
        if(n == 0) {
            return;
        } else {
            DFS2(n - 1);
        }

        System.out.print(n + " ");
    }
}
