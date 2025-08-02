package lecture.section07;

import common.CodingTest;

import java.util.Arrays;
import java.util.stream.Collectors;

// 01. 선택정렬
/*
    1번째 순서, 2번째 순서 ... 차례대로 해당 자리에 넣을 원소를 찾아가는 알고리즘
    나머지 원소 중 가장 작은(혹은 가장 큰) 대상을 해당 위치의 원소와 바꾼다.
*/
public class Section07Test01 extends CodingTest {

    private final int N = 6;
    private final int[] ARR = {13, 5, 11, 7, 23, 15};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, ARR));
    }

    /*
        시간복잡도 : O(N^2)
        공간복잡도 : O(N)
    */
    private void createCase(int n, int[] arr) {

        int[] originalArr = Arrays.copyOf(arr, n);

        for(int i=0; i<n; i++){
            int minIdx  = i;

            for(int j=i+1; j<n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            int tmp = arr[i];
            arr[i] = arr[minIdx ];
            arr[minIdx ] = tmp;
        }

        String result = Arrays.stream(arr)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[int] : " + n);
        System.out.println("[arr] : " + Arrays.toString(originalArr));
        System.out.println("[작업 결과] : " + result);
    }
}
