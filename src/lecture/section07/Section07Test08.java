package lecture.section07;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 08. 이분검색
public class Section07Test08 extends CodingTest {

    private final int N = 8;
    private final int M = 32;
    private final int[] ARR = {23, 87, 65, 12, 57, 32, 99, 81};

    @Override
    public void addCases() {

        // 잘못된 풀이
        testCases.add(() -> createCase(N, M, ARR));

        // Arrays.copyOfRange 이용한 방법 -> 속도 느림
        testCases.add(() -> createCase2(N, M, ARR));

        // ArrayList 이용한 방법 -> 속도 개선됨
        testCases.add(() -> createCase3(N, M, ARR));

        // 최적의 풀이 => 추천하는 방식
        testCases.add(() -> createCase4(N, M, ARR));

        // 내장 메서드(Arrays.binarySearch) 사용한 방법
        testCases.add(() -> createCase5(N, M, ARR));
    }

    // 문제점 -> 원본의 Index를 계산할 수없음
    private void createCase(int n, int m, int[] arr) {

        int[] sorted = arr;
        Arrays.sort(sorted);

        Integer result = null;
        while(true) {
            int len = sorted.length;

            if(len <= 1) break;

            int centerIdx = len / 2;
            int center = sorted[centerIdx];

            if(center == m) {
                result = centerIdx + 1;
                break;
            }

            boolean isLeft = center > m;
            int startIdx = isLeft ? 0 : centerIdx + 1;
            int endIdx = isLeft ? centerIdx : len;

            sorted = Arrays.copyOfRange(sorted, startIdx, endIdx);
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    private void createCase2(int n, int m, int[] arr) {

        int[] sorted = arr;
        Arrays.sort(sorted);        // O(N log N)

        Integer result = null;
        int totalIdx = 0;

        // 최악의 경우 O(N log N)
        /*
            이진 탐색 로직은 O(log N)이지만,
            배열을 복사하는 copyOfRange가 루프마다 최대 O(N) 시간 소요.
            전체 루프 횟수 log N × 각 루프당 최대 N 길이 복사 = 최악 O(N log N)
            정렬도 O(N log N) 소요되므로 전체 프로그램 시간 복잡도는 O(N log N)
        */
        while(true) {
            int len = sorted.length;

            if(len == 1) {
                if(sorted[0] == m) {
                    result = totalIdx + 1;
                }
                break;
            }

            int centerIdx = len / 2;
            int center = sorted[centerIdx];

            if(center == m) {
                result = totalIdx + centerIdx + 1;
                break;
            }

            boolean isLeft = center > m;
            int startIdx = isLeft ? 0 : centerIdx + 1;
            int endIdx = isLeft ? centerIdx : len;

            totalIdx +=startIdx;

            sorted = Arrays.copyOfRange(sorted, startIdx, endIdx);      // O(N)
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    // ArrayList를 이용한 방법
    private void createCase3(int n, int m, int[] arr) {

        List<Integer> sorted = Arrays.stream(arr)
                .sorted()                                               // O(N log N)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        Integer result = null;
        int totalIdx = 0;

        // O(log N) => 이진 분석 알고리즘 복잡도가 O(log N)
        while(true) {
            int len = sorted.size();

            if(len == 1) {
                if(sorted.get(0) == m) {
                    result = totalIdx + 1;
                }
                break;
            }

            int centerIdx = len / 2;
            int center = sorted.get(centerIdx);

            if(center == m) {
                result = totalIdx + centerIdx + 1;
                break;
            }

            boolean isLeft = center > m;
            int startIdx = isLeft ? 0 : centerIdx + 1;
            int endIdx = isLeft ? centerIdx : len;

            totalIdx +=startIdx;

            sorted = sorted.subList(startIdx, endIdx);     // O(1)
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    private void createCase4(int n, int m, int[] arr) {

        int[] sorted = arr;
        Arrays.sort(sorted);        // O(N log N)

        Integer result = null;
        int lt = 0;
        int rt = arr.length - 1;


        while(lt <= rt) {
            int centerIdx = lt + ((rt - lt) / 2);
            int center = sorted[centerIdx];

            if(center == m) {
                result = centerIdx + 1;
                break;
            }

            boolean isLeft = center > m;
            lt = isLeft ? lt : centerIdx + 1;
            rt = isLeft ? centerIdx : rt;
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    // 내장 메소드를 이용한 이진 분석
    private void createCase5(int n, int m, int[] arr) {

        int[] sorted = arr;
        Arrays.sort(sorted);                               // O(N log N)

        int index = Arrays.binarySearch(sorted, m);        // O(log N)

        Integer result = index >= 0 ? index + 1 : null;

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[m] : " + m);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }
}
