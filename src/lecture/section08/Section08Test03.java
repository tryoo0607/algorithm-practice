package lecture.section08;

import common.CodingTest;

// 03. 팩토리얼
public class Section08Test03 extends CodingTest {
    private final int N = 5;

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N));
    }

    private void createCase(int n) {

        int result = DFS(n);
        System.out.println(result);
    }

    private int DFS(int n) {

        if(n > 1) {
            return n * DFS(n-1);
        } else {
            return 1;
        }
    }
}
