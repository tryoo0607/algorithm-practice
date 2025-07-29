package lecture.section05;

import common.CodingTest;

import java.util.*;
import java.util.stream.Collectors;

// 05. K번째 큰 수
public class Section05Test05 extends CodingTest {

    private final int N = 10;
    private final int K = 3;
    private final int[] ARR = {13, 15, 34, 23, 45, 65, 33, 11, 26, 42};

    @Override
    public void addCases() {

        // 잘못된 풀이
        testCases.add(() -> createCase(N, K, ARR));

        // 정확한 풀이
        testCases.add(() -> createCase2(N, K, ARR));

        // 보다 loop 및 조건문 개선한 코드
        testCases.add(() -> createCase3(N, K, ARR));

        // TreeSet을 활용한 가장 최적의 코드
        testCases.add(() -> createCase4(N, K, ARR));
    }

    // 잘못된 풀이
    // {10, 20, 30, 40} 에서 k=2일 때 40 + 30 + 10이 두번쨰이지 30 + 20 + 10이 두번째가 되는건 아님
    // 그리고 문제 잘 보면 Set을 써야함
    private void createCase(int n, int k, int[] arr) {

        ArrayList<Integer> list = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new));

        int start = k - 1;
        int result = 0;
        for(int i = start; i < start + 3; i++) {
            result+=list.get(i);
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[k] : " + k);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N^3)
        공간복잡도 : O(N^3) -> List 의 크기 == 최대 모든 sum의 경우의 수
    */
    private void createCase2(int n, int k, int[] arr) {

        Set<Integer> resultSet = new HashSet<>();

        for(int i=0; i < n; i++) {

            for(int j=0; j < n; j++) {
                if(i==j) continue;
                for(int t=0; t < n; t++) {
                    if(i == t || j == t) continue;
                    int sum = arr[i] + arr[j] + arr[t];
                    resultSet.add(sum);
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>(resultSet);
        list = list.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new));

        int result = list.size() >= k ? list.get(k - 1) : -1;
        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[k] : " + k);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N^3)
        공간복잡도 : O(N^3) -> List 의 크기 == 최대 모든 sum의 경우의 수
    */
    // 보다 개선된 코드
    private void createCase3(int n, int k, int[] arr) {

        Set<Integer> resultSet = new HashSet<>();

        for(int i=0; i < n - 2; i++) {
            for(int j=i+1; j < n - 1; j++) {
                for(int t=j+1; t < n; t++) {

                    int sum = arr[i] + arr[j] + arr[t];
                    resultSet.add(sum);
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>(resultSet);
        list = list.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new));

        int result = list.size() >= k ? list.get(k - 1) : -1;
        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[k] : " + k);
        System.out.println("[작업 결과] : " + result);
    }


    /*
        시간복잡도 : O(N^3)
        공간복잡도 : O(N^3) -> List 의 크기 == 최대 모든 sum의 경우의 수
    */
    // 가장 깔끔하고 효율적인 풀이
    private void createCase4(int n, int k, int[] arr) {
        TreeSet<Integer> resultSet = new TreeSet<>(Comparator.reverseOrder()); // 자동 내림차순 정렬 + 중복 제거

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int t = j + 1; t < n; t++) {
                    resultSet.add(arr[i] + arr[j] + arr[t]);
                }
            }
        }

        int count = 0;
        int result = -1;
        for (int val : resultSet) {
            count++;
            if (count == k) {
                result = val;
                break;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[k] : " + k);
        System.out.println("[작업 결과] : " + result);
    }
}
