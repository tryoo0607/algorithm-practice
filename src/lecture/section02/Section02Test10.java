package lecture.section02;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

// 10. 가장 짧은 문자거리
public class Section02Test10 extends CodingTest {

    private final String STR = "teachermode e";

    @Override
    public void addCases() {

//        testCases.add(() -> createCase(STR));
        testCases.add(() -> createCase2(STR));
    }

    /*
        시간복잡도: O(N^2)
        공간복잡도: O(N)
    */
    private void createCase(String input) {

        String str = input.split(" ")[0];
        String target = input.split(" ")[1];

        ArrayList<Integer> targetIndexes = new ArrayList<>();

        String[] strArr = str.split("");
        ArrayList<Integer> arr = new ArrayList<>();

        for(int i=0; i < strArr.length; i++) {
            if(strArr[i].equals(target)) {
                targetIndexes.add(i);
            }
        }

        for(int i=0; i < strArr.length;i++) {
            int min = strArr.length;

            for(int index : targetIndexes) {
                min = Math.min(min, Math.abs(index - i));
            }
            arr.add(min);
        }

        String result = arr.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[target] : " + target);
        System.out.println("[작업 결과] : " + result);
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    // 투 포인터 방식
    private void createCase2(String input) {

        String str = input.split(" ")[0];
        char target = input.split(" ")[1].charAt(0);

        int[] indexes = new int[str.length()];

        // 충분히 큰 값, 임시로 넣는 값
        int p = 1000;

        // 왼쪽 -> 오른쪽 순회
        // target 보다 오른쪽에 있는 문자들은 이 때 거리 값이 결정 됨
        for(int i=0; i<str.length();i++) {

            if(str.charAt(i) == target) {
                p = 0;
                indexes[i] = p;
            } else {
                p++;
                indexes[i] = p;
            }
        }

        // 오른쪽 -> 왼쪽 순회
        // target 보다 왼쪽에 있는 문자들은 이 때 거리 값이 결정 됨
        for(int i=str.length() -1; i>=0; i--) {

            if(str.charAt(i) == target) {
                p = 0;
                indexes[i] = p;
            } else {
                p++;
                indexes[i] = Math.min(indexes[i], p);
            }
        }

        String result = Arrays.stream(indexes)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[target] : " + target);
        System.out.println("[작업 결과] : " + result);
    }
}
