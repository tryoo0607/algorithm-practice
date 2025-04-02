package chapter05;

import model.CodingTest;

import java.util.Arrays;
import java.util.HashSet;

public class Chapter05Test03 extends CodingTest {

    private final int[] numbers1 = {2, 1, 3, 4, 1};
    private final int[] numbers2 = {5, 0, 2, 7};

    @Override
    public void addCases() {
        testCases.add(() -> createCase(numbers1));
        testCases.add(() -> createCase(numbers2));
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

        System.out.println("[정렬 이전] : " + Arrays.toString(numbers));
        System.out.println("[정렬 결과] : " + Arrays.toString(result));
    }
}
