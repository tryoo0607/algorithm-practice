package lecture.section07;

import common.CodingTest;

import java.util.Arrays;
import java.util.stream.Collectors;

// 02. 버블정렬
/*
    인접한 두 수를 비교하여 작은 수를 앞에, 큰 수를 뒤에 배치하는 작업을 배열의 끝까지 반복합니다.
    한 번 순회가 끝나면 가장 큰 수가 배열의 끝에 고정됩니다.
    이를 반복하여 배열 전체를 정렬하는 방식입니다.
*/
public class Section07Test02 extends CodingTest {

    private final int N = 6;
    private final int[] ARR = {13, 5, 11, 7, 23, 15};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, ARR));

        // 아주 살짝 더 최적화 된 로직
        testCases.add(() -> createCase2(N, ARR));
    }

    /*
         시간복잡도 : O(N^2)
         공간복잡도 : O(1)
    */
    private void createCase(int n, int[] arr) {

        int[] originalArr = Arrays.copyOf(arr, n);

        for(int i=n-1; i>=0; i--) {

            for(int j=0; j<i; j++) {
                if(arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }

        String result = Arrays.stream(arr)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[int] : " + n);
        System.out.println("[arr] : " + Arrays.toString(originalArr));
        System.out.println("[작업 결과] : " + result);
    }

    /*
         시간복잡도 : O(N^2)
         공간복잡도 : O(1)
    */
    // 이미 정렬이 완료 되었으면 빠르게 종료하는 로직
    private void createCase2(int n, int[] arr) {

        int[] originalArr = Arrays.copyOf(arr, n);

        for(int i=n-1; i>=0; i--) {
            boolean swapped = false;

            for(int j=0; j<i; j++) {
                if(arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    swapped = true;
                }
            }

            if(!swapped) break;
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
