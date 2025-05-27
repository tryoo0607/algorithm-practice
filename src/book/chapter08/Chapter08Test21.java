package book.chapter08;

import common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// 문제 21. 오픈 채팅방 **
public class Chapter08Test21 extends CodingTest {

    private final String[] RECORD1 = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(RECORD1));
    }

    private void createCase(String[] record) {

        int expected = record.length;
        float factor = 0.75f;
        int cap = (int) (expected / factor) + 1;

        HashMap<String, String> uidMap = new HashMap<>(cap, factor);

        for(String str : record) {
            String[] split = str.split(" ");
            String action = split[0];
            String uid = split[1];

            if("Enter".equals(action) || "Change".equals(action)) {
              uidMap.put(uid, split[2]);
            }
        }

        List<String> result = new ArrayList<>();
        for(String str: record) {
            String[] split = str.split(" ");
            String action = split[0];
            String uid = split[1];
            String id = uidMap.get(uid);

            if("Enter".equals(action) ) {
                result.add(id+"님이 들어왔습니다.");
            } else if("Leave".equals(action)) {
                result.add(id+"님이 나갔습니다.");
            }
        }

        System.out.println("[작업 이전] : ");
        System.out.println("[record] : " + Arrays.toString(record));
        System.out.println("[작업 결과] : " + Arrays.toString(result.toArray(new String[0])));
    }
}
