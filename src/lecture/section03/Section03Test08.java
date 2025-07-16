package lecture.section03;

import common.CodingTest;

import java.util.Arrays;
import java.util.stream.Collectors;

// 08. 등수구하기
public class Section03Test08 extends CodingTest {

    private final int N = 5;
    private final int[] NUM_ARR = {87, 89, 92, 100, 76};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, NUM_ARR));
        testCases.add(() -> createCase2(N, NUM_ARR));
    }

    /*
        시간복잡도 : O(N^2)
        공간복잡도 : O(N)
    */
    // 브루투포스(완전 탐색) 알고리즘
    // => "일단 다 해보는 거"
    private void createCase(int n, int[] numArr){

        int[] resultArr = new int[n];
        Arrays.fill(resultArr, 1);

        for(int i=0; i < n; i++) {

            for(int j=0; j <n; j++) {
                if(numArr[i] < numArr[j]) {
                    resultArr[i]++;
                }
            }
        }

        // 기본형 스트림(IntStream) 등은 int -> 객체 변환 시 => mapToObj() 사용
        // 단, int -> int 변환은 => map() 사용
        // 객체형 스트림(Stream)은 객체 -> 객체 변환 시 => map() 사용
        // boolean의 경우 별도의 기본형 스트림이 없음 => 따라서 map() 사용
        String result = Arrays.stream(resultArr)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[numArr] : " + Arrays.toString(numArr));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    // node와 유사한 패턴 활용한 방식
    private void createCase2(int n, int[] numArr){

        int[] ranks = new int[n];

        // 점수와 인덱스를 같이 저장할 객체 생성
        class Score {
            int idx;
            int value;

            Score(int idx, int value) {
                this.idx = idx;
                this.value = value;
            }
        }

        Score[] scores = new Score[n];
        for (int i = 0; i < n; i++) {
            scores[i] = new Score(i, numArr[i]);
        }

        // 내림차순 정렬
        Arrays.sort(scores, (a, b) -> b.value - a.value);

        // 등수 매기기
        int rank = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0 && scores[i].value == scores[i - 1].value) {
                // 동점자 처리 : 같은 등수 유지
                ranks[scores[i].idx] = ranks[scores[i - 1].idx];
            } else {
                ranks[scores[i].idx] = rank;
            }
            rank++;
        }

        String result = Arrays.stream(ranks)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[numArr] : " + Arrays.toString(numArr));
        System.out.println("[작업 결과] : " + result);
    }
}
