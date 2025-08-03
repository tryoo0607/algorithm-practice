package lecture.section07;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

// 03. 삽입정렬
/*
    배열의 1번 인덱스부터 n번까지 순회하면서,
    현재 원소를 그 앞에 있는 정렬된 부분과 비교하여
    알맞은 위치에 삽입하는 방식입니다.
    비교 대상이 더 크면 한 칸씩 뒤로 밀고, 삽입할 자리를 찾아 값을 배치합니다.
*/
public class Section07Test03 extends CodingTest {
    private final int N = 6;
    private final int[] ARR = {11, 7, 5, 6, 10, 9};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, ARR));

        // 조금 더 최적화된 방식
        testCases.add(() -> createCase2(N, ARR));
    }

    /*
         시간복잡도 : O(N^2)
         공간복잡도 : O(n)
    */
    private void createCase(int n, int[] arr) {

        int[] originalArr = Arrays.copyOf(arr, n);

        ArrayList<Integer> arrayList = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        for(int i=1; i<n; i++) {

            int key = i;
            for(int j=i-1; j>=0; j--) {
                if(arr[j] > arr[i]) {
                    key = j;
                } else {
                    break;
                }
            }

            int target = arr[i];
            arrayList.remove(i);
            arrayList.add(key, target);

            arr = arrayList.stream()
                    .mapToInt(Integer::valueOf)
                    .toArray();
        }

        String result = arrayList.stream()
                .map(String::valueOf)
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
    private void createCase2(int n, int[] arr) {

        int[] originalArr = Arrays.copyOf(arr, n);


        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j = i - 1;

            // 한칸씩 뒤로 이동 시킴
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
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
