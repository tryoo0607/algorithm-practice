package lecture.section07;

import common.CodingTest;

import java.util.Arrays;
import java.util.stream.Collectors;

// 01. 선택정렬
/*
    첫 번째 원소부터 차례대로 해당 자리에 올 가장 작은(또는 가장 큰) 원소를 찾아 정렬하는 알고리즘입니다.
    현재 위치 이후의 원소들 중에서 최소값(또는 최대값)을 찾아 현재 위치의 원소와 교환합니다.
    이를 반복하여 전체 배열을 정렬합니다.
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
        공간복잡도 : O(1)
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
