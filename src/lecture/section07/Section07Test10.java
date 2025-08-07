package lecture.section07;

import common.CodingTest;

import java.util.Arrays;

// 10. 마구간 정의하기(결정알고리즘)
public class Section07Test10 extends CodingTest {

    private final int N = 5;
    private final int C = 3;
    private final int[] ARR = {1, 2, 8, 4, 9};


    /*
        # 문제 분석
        - 구해야하는 것 : 말들이 배치된 마구간에서 "가장 가까운 말들의 거리가 최대일 때의 거리"
        - 가질 수 있는 거리 범위 : 최소거리(가장 가까운 좌표의 차이) ~ 최대 거리(가장 먼 좌표의 차이)
        - 판단 기준 : 말들을 배치하면 얻게되는 length가 현재 중간값인 currentLength 보다 큰지 작은지
    */
    @Override
    public void addCases() {

        // 살짝 틀림 (방향성은 맞음)
        testCases.add(() -> createCase(N, C, ARR));

        // 올바른 풀이
        testCases.add(() -> createCase2(N, C, ARR));
    }


    private int getMaxCount(int[] arr, int minLength) {
        // 말을 배치할 수 있는 마구간 수를 센다
        // 초기 1마리는 반드시 첫 마구간(arr[0])에 배치한다고 가정
        int count = 1;

        // beforeX는 마지막으로 말을 배치한 마구간의 좌표
        // 초기에는 arr[0]에 말을 놓았으므로 시작점을 arr[0]으로 설정
        int beforeX = arr[0];

        // 모든 마구간을 순회하며 말 배치 가능 여부 판단
        for (int x : arr) {
            // 현재 마구간(x)와 마지막 배치 지점(beforeX) 사이의 거리 계산
            int length = x - beforeX;

            // minLength 이상 거리 차이가 나면 말을 배치할 수 있음
            if (length >= minLength) {
                count++;        // 말 한 마리 추가 배치
                beforeX = x;    // 배치한 마구간을 새로운 기준점으로 갱신
            }

            // else일 때는 거리가 부족하므로 넘어감 (배치하지 않음)
        }

        // 주어진 거리 조건(minLength)으로 말을 최대 몇 마리 놓을 수 있는지 반환
        return count;
    }

    // 살짝 틀림
    private void createCase(int n, int c, int[] arr) {

        int[] sorted = arr.clone();

        Arrays.sort(sorted);

        int maxLength = sorted[n - 1] - sorted[0];
        int minLength = 2;

        int lt = minLength;
        int rt = maxLength;

        int result = 0;
        while(lt < rt) {

            int currentLength = (lt + rt) / 2;
            int maxCount = getMaxCount(sorted, currentLength);

            if(maxCount <= c) {
                rt = currentLength;
                result = currentLength;

            } else {
                lt = currentLength + 1;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[c] : " + c);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }


    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    private void createCase2(int n, int c, int[] arr) {

        int[] sorted = arr.clone();

        Arrays.sort(sorted);

        int maxLength = sorted[n - 1] - sorted[0];

        // 아예 배치 못할 때도 고려해야하므로 0
        int minLength = 0;

        int lt = minLength;
        int rt = maxLength;

        int result = 0;

        // lt == rt일 때도 실행되어야 함.
        // => 이 경우는 범위가 하나로 좁혀졌다는 의미이므로, 해당 값 자체가 유효한 거리 후보인지 반드시 확인해야 함.
        while(lt <= rt) {

            int currentLength = (lt + rt) / 2;
            int maxCount = getMaxCount(sorted, currentLength);


            /*
                문제 조건: 말을 최소 c마리 배치해야 함
                따라서, currentLength 간격으로 c마리 이상 배치할 수 있는 경우는 유효함
            */
            /*
                maxCount >= c
                => 즉, currentLength 만큼 넣으면 내가 원하는 것보다 더 들어가게 됨
                => 따라서 currentLength를 늘려야함
                => 즉, 범위(min ~ max)를 가운데 기준으로 우측으로 옮겨야 함
                => 즉, lt의 범위가 currentLength 보다 우측(+1)로 옮겨져야 함
                => 참고로 currentLength는 검사했으므로 이동된 범위에 포함시키지 않아도 됨
            */
            if(maxCount >= c) {
                result = currentLength;
                lt = currentLength + 1;


            /*
                maxCount < c
                => 즉, currentLength 만큼 넣으면 내가 원하는 것보다 덜 들어감
                => 따라서 currentLength를 줄여서 최소 c개가 들어가게 해야함
                => 즉, 범위(min ~ max)를 가운데 기준으로 우측으로 옮겨야 함
                => 즉, rt의 범위가 currentLength 보다 좌측(-1)로 옮겨져야 함
                => 마찬가지로 currentLength는 검사했으므로 이동된 범위에 포함시키지 않아도 됨
            */
            } else {
                rt = currentLength - 1;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[c] : " + c);
        System.out.println("[arr] : " + Arrays.toString(arr));
        System.out.println("[작업 결과] : " + result);
    }
}
