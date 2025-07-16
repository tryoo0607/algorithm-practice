package lecture.section02;

import common.CodingTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// 06. 중복문자제거
public class Section02Test06 extends CodingTest {

    private final String STR = "ksekkset";

    @Override
    public void addCases() {
        testCases.add(() -> createCase(STR));
        testCases.add(() -> createCase2(STR));
        testCases.add(() -> createCase3(STR));
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    private void createCase(String str) {

        // 공간복잡도 : O(N) (문자열 길이)
        String result = Arrays.stream(str.split(""))        // O(1)
                .distinct()                                       // O(N)
                .collect(Collectors.joining());                   // O(N)

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    // HashSet 이용한 방식
    private void createCase2(String str) {
        StringBuilder sb = new StringBuilder();     // 공간복잡도 : O(N)
        Set<Character> seen = new HashSet<>();      // 공간복잡도 : O(N)

        for (char c : str.toCharArray()) {          // O(1)
            if (!seen.contains(c)) {                // contains: O(1) (평균)
                seen.add(c);                        // add: O(1) (평균)
                sb.append(c);                       // append: O(1)
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + sb.toString());
    }

    /*
        시간복잡도 : O(N^2)
        공간복잡도 : O(N)
    */
    // indexOf 활용
    private void createCase3(String str) {

        StringBuilder builder = new StringBuilder();    // 공간복잡도 : O(N)

        // O(N^2)
        for(String a : str.split("")) {           // O(N)
            if(builder.indexOf(a) < 0) {                // O(N)
                builder.append(a);
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + builder.toString());
    }
}
