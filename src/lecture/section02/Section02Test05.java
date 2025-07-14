package lecture.section02;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;

// 05. 소수(에라토스테네스 체)
public class Section02Test05 extends CodingTest {

    private final int N = 20;

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N));
        testCases.add(() -> createCase2(N));

        // 권장 방법
        testCases.add(() -> createCase3(N));
    }

    /*
        시간복잡도 : O(n² / log n)
        공간복잡도 : O(n / log n)
    */
    // 비효율적인 방법
    private void createCase(int n) {

        ArrayList<Integer> primes = new ArrayList<>();

        for(int i=2; i <= n; i++) {
            int count = 0;

            int num = Math.min(i, 10);
            for(int j=1; j < num; j++) {
                if(i % j == 0) count++;
            }

            if(i >= 10) {
                for(int prime : primes) {
                    if(i % prime == 0) count++;
                }
            }

            count++;

            if(count == 2) primes.add(i);
        }

        int result = primes.size();

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[작업 결과] : " + result);
    }

    // 비효율적인 방법2 -> 권장되지 않음(자기 자신을 count하는 편법 썼기 때문)
    private void createCase2(int n) {

        ArrayList<Integer> primes = new ArrayList<>();

        for(int i=2; i <= n; i++) {
            int count = 0;

            int num = Math.min(i, 10);
            for(int j=1; j < num; j++) {
                if(i % j == 0) count++;
            }

            if(i >= 10) {
                for(int prime : primes) {
                    if(i % prime == 0) count++;
                }
            }

            count++;

            if(count == 2) primes.add(i);
        }

        int result = primes.size();

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(n log log n)
        공간복잡도 : O(n)
    */
    // 에라토스테네스의 체 -> 권장 방법
    private void createCase3(int n) {

        // 1 ~ n  -> n개
        // 0 -> 1개
        // 인덱스 == 숫자 처럼 쓰기 위해서 0도 추가하는 것
        boolean[] isPrime = new boolean[n + 1];

        // 2 ~ 9를 모두 소수라고 가정
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        // i < n 까지 돌아도 되지만 불필요한 루프 반복하게 되서 i * i 까지만
        // 그 이후는 이미 작은 소수의 배수에서 다 지워지기 때문에 i * i < n을 만족할 때까지만 돌면 됨
        for(int i=2; i * i <= n; i++) {
            // 소수일 경우 -> 그 소수의 배수들을 모두 소수에서 제외함
            if(isPrime[i]) {
                /*
                     i * 2 가 아닌 i * i 부터 거르는 이유
                     => i * 2, i * 3, ... i * (i - 1) 까지는 이전 채에서 걸러졌기 때문
                */
                /*
                    j += i 인 이유 => i의 배수들을 지우기 위해
                    현재 j => i * i 부터 시작함
                    즉, i * i, i * (i + 1), i * (i + 2) ... 이런 식으로 i의 배수들을 지우기 위함
                */
                for(int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int result = 0;
        for(boolean isTrue: isPrime) {
            if(isTrue) result++;
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[작업 결과] : " + result);
    }
}
