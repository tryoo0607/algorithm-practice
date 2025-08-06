package lecture.section07;

import common.CodingTest;

// 09. 뮤직비디오(결정알고리즘)
// 반드시 다시 풀어보기
public class Section07Test09 extends CodingTest {

    private final int N = 9;
    private final int M = 3;
    private final int[] ARR = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, M, ARR));
    }

    // chatGPT 통해서 찾은 답 => 추후 다시 풀어보기
    private void createCase(int n, int m, int[] arr) {

        int maxLength = 0;      // 가장 긴 곡
        int totalLength = 0;    // 전체 길이

        for(int song : arr) {
            maxLength = Math.max(maxLength, song);
            totalLength += song;
        }

        /*
            현재 탐색해야하는 것은 "DVD의 용량"
            때문에 탐색 범위는 DVD의 용량이 되며,
            lt와 rt도 DVD의 용량과 관련됨
            - lt : 어떤 DVD더라도 가장 긴곡은 반드시 담을 수 있어야함 => 그래서 최소 용량(lt)은 maxLength
            - rt : 한 DVD에 담개 되는 최대 용량 => 전체를 다 담으면 그게 최대 => 그래서 대 용량(rt)는 totalLength

            -> 즉 lt ~ rt => 한개의 DVD에서 담을 수 있는 최소 ~ 최대 범위의 용량
        */
        /*
            탐색 대상은 "DVD의 용량"

            - lt : 가장 긴 곡의 길이 (최소 용량)
                   어떤 DVD라도 이 길이 이상이어야 곡을 담을 수 있음

            - rt : 모든 곡을 한 DVD에 담는 경우의 용량 (최대 용량)
                   DVD 크기가 이 이상이면 무조건 가능

            따라서 lt ~ rt 범위 내에서 가능한 최소 용량을 이진 탐색으로 찾는다
        */
        int lt = maxLength;
        int rt = totalLength;
        int result = 0;

        /*
            이진 검색을 통해서 "가능한 용량"을 찾아가는 것
        */
        while(lt <= rt) {
            int mid = (lt + rt) / 2;    // 테스트 할 용량
            int dvdCount = countDVDs(arr, mid); // 최소 필요한 DVD의 개수

            /*
                만약 현재 용량(mid)에서 최소 필요한 dvd 수가 요구되는 수(m) 보다 적거나 같다면
                => 조건은 만족함 => 그러나 최소 용량을 찾아야 하기 때문에 현재 용량을 하나 줄여서 다시 탐색
                => 즉 이진 탐색에서 좌측으로 이동 (좌측 범위를 다시 탐색 범위로)
            */
            if(dvdCount <= m) {
                result = mid;
                rt = mid - 1;

            /*
               만약 현재 용량(mid)에서 최소 필요한 dvd 수가 요구되는 수(m) 보다 많아진다면
               => DVD에 담아야하는 용량을 증가시켜서 다시 탐색해야 함
               => 즉, 이진 탐색에서 우측으로 이동 (우측 범위를 다시 탐색 범위로)
            */
            } else {
                lt = mid + 1;
            }
        }
    }

    private int countDVDs(int[] songs, int capacity) {
        int count = 1;  // 일단 최소 한개의 DVD 필요
        int sum = 0;

        // 일단 용량 차기 직전만큼 DVD에 담음
        // 그리고 용량 넘어갈거 같으면 DVD의 카운트를 증가시킴
        for(int song: songs) {
            if(sum + song > capacity) {
                count++;
                sum = song;
            } else {
                sum += song;
            }
        }

        return count;
    }
}
