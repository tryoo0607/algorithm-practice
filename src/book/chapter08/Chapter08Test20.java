package book.chapter08;

import common.CodingTest;

import java.util.Arrays;
import java.util.HashMap;

// 문제 20. 할인 행사 **
public class Chapter08Test20 extends CodingTest {

    private final String[] WANT1 = {"banana", "apple", "rice", "pork", "pot"};
    private final Integer[] NUMBER1 = {3, 2, 2, 2, 1};
    private final String[] DISCOUNT1 = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

    private final String[] WANT2 = {"apple"};
    private final Integer[] NUMBER2 = {10};
    private final String[] DISCOUNT2 = {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"};


    @Override
    public void addCases() {

        testCases.add(() -> createCase(WANT1, NUMBER1, DISCOUNT1));
        testCases.add(() -> createCase(WANT2, NUMBER2, DISCOUNT2));
    }

    // 잘못된 풀이 (10일 고려 못함)
    private void createCaseBefore(String[] want, Integer[] number, String[] discount) {

        int expected = want.length;
        float factor = 0.75f;
        int cap = (int) (expected / factor) + 1;

        HashMap<String, Integer> hashMap = new HashMap<>(cap, factor);

        for(int i = 0; i < want.length; i++) {
            hashMap.put(want[i], number[i]);
        }

        int result = 0;
        for(int i=0; i < discount.length; i++) {

            String item = discount[i];

            if(hashMap.containsKey(item)) {
                hashMap.put(item, hashMap.get(item) - 1);

                if(hashMap.get(item) == 0) {
                    hashMap.remove(item);
                }
            }

            if(hashMap.isEmpty()) {
                result = discount.length - i;
                break;
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[want] : " + Arrays.toString(want));
        System.out.println("[number] : " + Arrays.toString(number));
        System.out.println("[discount] : " + Arrays.toString(discount));
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간 복잡도 : O(N) -> want의 length는 최대 10 => 상수로 취급해도 됨
        공간 복잡도 : O(1)
    */
    private void createCase(String[] want, Integer[] number, String[] discount) {

        int expected = want.length;
        float factor = 0.75f;
        int cap = (int) (expected / factor) + 1;

        HashMap<String, Integer> wantMap = new HashMap<>(cap, factor);      // 공간복잡도 : O(1) => want의 length가 10 이하라 상수취급하기 때문

        for(int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        int result = 0;

        int discountExpected = 10;
        float discountFactor = 0.75f;
        int discountCap = (int) (discountExpected / discountFactor) + 1;

        /*
            - discount.length - 10까지 순회해야 함
            - 즉, discount.length - 10 + 1 보다 작을 때까지 실행되야 함
            - 즉, discount.length - 9가 됨
        */
        for(int i = 0; i < discount.length - 9; i++) {                                                      // O(N)
            HashMap<String, Integer> discountMap = new HashMap<>(discountCap, discountFactor);              // 공간복잡도 : O(1) -> discountCap이 몇인지 확인하면 됨

            for(int j = i; j < i + 10; j++) {                                                               // O(1)
                discountMap.put(discount[j], discountMap.getOrDefault(discount[j], 0) + 1);     // O(1)
            }

            if(wantMap.equals(discountMap)) {                                                               // O(1)
                result++;
            }
        }


        System.out.println("[작업 이전] : ");
        System.out.println("[want] : " + Arrays.toString(want));
        System.out.println("[number] : " + Arrays.toString(number));
        System.out.println("[discount] : " + Arrays.toString(discount));
        System.out.println("[작업 결과] : " + result);
    }
}
