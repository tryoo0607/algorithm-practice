package lecture.section03;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

// 06. 뒤집은 소수
public class Section03Test06 extends CodingTest {

    private final int N = 9;
    private final int[] NUM_ARR = {32, 55, 62, 20, 250, 370, 200, 30, 100};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, NUM_ARR));
        testCases.add(() -> createCase2(N, NUM_ARR));
    }


    /*
        시간복잡도 : O(n log log n)
        공간복잡도 : O(n)
    */
    private void createCase(int n, int[] numArr) {

        int[] reversedArr = new int[n];
        for(int i=0; i < n; i++) {
            String numStr = String.valueOf(numArr[i]);
            StringBuilder builder = new StringBuilder(numStr).reverse();
            reversedArr[i] = Integer.parseInt(builder.toString());
        }

        int max = Arrays.stream(reversedArr).max().getAsInt();

        boolean[] isPrime = new boolean[max + 1];

        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for(int i=2; i * i <= max; i++) {
            if(isPrime[i]) {

                for(int j = i * i; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        ArrayList<Integer> primeArr = new ArrayList<>();

        for(int reversedNum : reversedArr) {
            if(isPrime[reversedNum]) {
                primeArr.add(reversedNum);
            }
        }

        String result = primeArr.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(n log log n)
        공간복잡도 : O(n)
    */
    // StringBuilder를 사용하지 않고 뒤집는 방법
    private void createCase2(int n, int[] numArr) {

        ArrayList<Integer> primeArr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int reversed = reverse(numArr[i]);
            if (isPrime(reversed)) {
                primeArr.add(reversed);
            }
        }

        String result = primeArr.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[작업 결과] : " + result);
    }

    private int reverse(int num) {
        int res = 0;
        while (num > 0) {
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res;
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
