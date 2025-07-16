package lecture.section03;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

// 01. 큰 수 출력하기
public class Section03Test01 extends CodingTest {

    private final int NUM = 6;
    private final int[] NUM_ARR = {7, 3, 9, 5, 6, 12};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(NUM, NUM_ARR));

        // 권장 방법
        testCases.add(() -> createCase2(NUM, NUM_ARR));
    }

    /*
        시간복잡도: O(N)
        공간복잡도: O(1)
    */
    private void createCase(int num, int[] numArr) {

        ArrayList<Integer> targetArr = new ArrayList<>();

        targetArr.add(numArr[0] -1);
        for (int n : numArr) {
            targetArr.add(n);
        }

        ArrayList<Integer> resultArr = new ArrayList<>();
        for(int i=0; i < targetArr.size() - 1; i++) {
            if(targetArr.get(i) < targetArr.get(i+1)) {
                resultArr.add(targetArr.get(i+1));
            }
        }

        /*
            map -> resultArr(int[])를 Stream<String>으로 바꿔줌 (각 요소를 담는 변수의 자료형을 바꿈)
            String::valueOf -> 자료형이 int인 각 요소를 바꾸기 위해 String.valueOf()를 사용
        */
        String result = resultArr.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[num] : " + num);
        System.out.println("[numArr] : " + Arrays.toString(numArr));
        System.out.println("[작업 결과] : " + result);
    }


    /*
        시간복잡도: O(N)
        공간복잡도: O(1)
    */
    // 보다 깔끔한 풀이
    private void createCase2(int n, int[] numArr) {

        ArrayList<Integer> resultArr = new ArrayList<>();
        resultArr.add(numArr[0]);

        for(int i=0; i < n - 1; i++) {
            if(numArr[i] < numArr[i+1]) {
                resultArr.add(numArr[i+1]);
            }
        }

        /*
            map -> resultArr(int[])를 Stream<String>으로 바꿔줌 (각 요소를 담는 변수의 자료형을 바꿈)
            String::valueOf -> 자료형이 int인 각 요소를 바꾸기 위해 String.valueOf()를 사용
        */
        String result = resultArr.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[num] : " + n);
        System.out.println("[numArr] : " + Arrays.toString(numArr));
        System.out.println("[작업 결과] : " + result);
    }
}
