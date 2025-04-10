    package chapter05;

    import model.CodingTest;

    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.Map;


    /*
        시간 복잡도 : O(N^3)
    */
    public class Chapter05Test05 extends CodingTest {

        private final String ARR1 = "arr1";
        private final String ARR2 = "arr2";

        Map<String, int[][]> arrays1 = new HashMap<>() {{
            put(ARR1, new int[][]{
                    {1, 4},
                    {3, 2},
                    {4, 1}
            });
            put(ARR2, new int[][]{
                    {3, 3},
                    {3, 3}
            });
        }};

        Map<String, int[][]> arrays2 = new HashMap<>() {{
            put(ARR1, new int[][]{
                    {2, 3, 2},
                    {4, 2, 4},
                    {3, 1, 4}
            });
            put(ARR2, new int[][]{
                    {5, 4, 3},
                    {2, 4, 1},
                    {3, 1, 1}
            });
        }};

        @Override
        public void addCases() {

            testCases.add(() -> createCase(arrays1));
            testCases.add(() -> createCase(arrays2));
        }

        public void createCase(Map<String, int[][]> arrayMap) {

            int[][] arr1 = arrayMap.get(ARR1);
            int[][] arr2 = arrayMap.get(ARR2);

            int r1 = arr1.length;
            int c1 = arr1[0].length;
            int r2 = arr2.length;
            int c2 = arr2[0].length;

            int[][] results = new int[r1][c2];

            // O(N^3)
            for(int i = 0; i < r1; i++) {                           // O(N)
                for(int j=0; j < c2; j++) {                         // O(N)
                    for(int k=0; k < c1; k++ ) {                    // O(N)
                        results[i][j] += arr1[i][k] * arr2[k][j];   // O(1)
                    }
                }
            }



            System.out.println("[작업 이전] : ");
            arrayMap.forEach((key, value) -> {
                System.out.println(key + " : " + Arrays.deepToString(value));
            });
            System.out.println("[작업 결과] : " + Arrays.deepToString(results));
        }
    }
