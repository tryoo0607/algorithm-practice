package lecture.section08;

import common.CodingTest;

public class Section08Test02 extends CodingTest {

    private final int N = 11;

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N));
    }

    private void createCase(int n) {

        StringBuilder builder = new StringBuilder();

        DFS(n, builder);

        System.out.println(builder.toString());
    }

    private void DFS(int n, StringBuilder builder) {
        if(n == 0) {
            return;
        }

        int quotient = n / 2;
        int remain = n % 2;

        builder.insert(0, remain);
        DFS(quotient, builder);
    }
}
