package lecture.section01;

import common.CodingTest;

// 05. 특정 문자 뒤집기
public class Section01Test05 extends CodingTest {

    private final String STR = "S#T!EG*b@a";

    @Override
    public void addCases() {

//        testCases.add(() -> createCase(STR));
        testCases.add(() -> createCase2(STR));
    }

    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    // 권장되지 않음 -> 양쪽이 알파벳일 때만 교환되어야 함
    private void createCase(String str) {

        char[] arr = str.toCharArray();

        int last = (int) Math.floor((double) str.length() / 2);
        for(int i=0; i < last; i++) {
            if(Character.isAlphabetic(arr[i])) {
                char tmp = arr[i];
                arr[i] = arr[arr.length - 1 - i];
                arr[arr.length - 1 - i] = tmp;
            }
        }

        String result = new String(arr);
        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }


    /*
        시간복잡도 : O(N)
        공간복잡도 : O(N)
    */
    // 투 포인터 방식 채용
    // 투 포인터 -> 양쪽이 모두 알파벳일 경우만 교환하도록 만듦
    private void createCase2(String str) {
        char[] arr = str.toCharArray();

        int lt = 0;
        int rt = arr.length -1;

        while(lt < rt) {
            if(!Character.isAlphabetic(arr[lt])) lt++;
            else if (!Character.isAlphabetic(arr[rt])) rt--;
            else {
                char tmp = arr[lt];
                arr[lt] = arr[rt];
                arr[rt] = tmp;
                lt++;
                rt--;
            }
        }

        String result = new String(arr);
        System.out.println("[작업 이전] : ");
        System.out.println("[str] : " + str);
        System.out.println("[작업 결과] : " + result);
    }
}
