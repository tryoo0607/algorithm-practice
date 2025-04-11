package book.chapter05;

import common.CodingTest;

import java.util.Arrays;
import java.util.HashSet;

// 문제 03. 두 개 뽑아서 더하기 *
public class Chapter05Test03 extends CodingTest {

    private final int[] NUMBERS1 = {2, 1, 3, 4, 1};
    private final int[] NUMBERS2 = {5, 0, 2, 7};

    @Override
    public void addCases() {
        testCases.add(() -> createCase(NUMBERS1));
        testCases.add(() -> createCase(NUMBERS2));
    }

    /*
        시간 복잡도 : O(N^2)
    */
    private void createCase(int[] numbers) {
        HashSet<Integer> numberSet = new HashSet<>();

        // O(N^2)
        for (int i = 0; i < numbers.length -1 ; i++) {

            for (int j = i + 1; j<numbers.length; j++) {
                numberSet.add(numbers[i] + numbers[j]);     // O(1)
            }
        }

        int[] result = numberSet.stream()                   // O(N)
                        .sorted()                           // O(N log N)
                        .mapToInt(Integer::intValue)        // O(N)
                        .toArray();                         // O(N)

        System.out.println("[작업 이전] : " + Arrays.toString(numbers));
        System.out.println("[작업 결과] : " + Arrays.toString(result));
    }
}
