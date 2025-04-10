package chapter05;

import model.CodingTest;

import java.util.*;

// 방문 길이
public class Chapter05Test07 extends CodingTest {

    private final String DIRS1 = "ULURRDLLU";
    private final String DIRS2 = "LULLLLLLU";

    @Override
    public void addCases() {
        testCases.add(() -> createCase(DIRS1));
        testCases.add(() -> createCase(DIRS2));
    }

    // 잘못된 풀이
    // x,y 좌표를 묶어서 고려하지 않아서 중복이 아닌 경우도 중복처리됨
    private void createCaseBefore(String dirs) {

        char[] charArr = dirs.toCharArray();

        int x = 0;
        int y = 0;
        Set<List<Integer>> moveX = new HashSet<>();
        Set<List<Integer>> moveY = new HashSet<>();

        for(char dir : charArr) {
            int beforeX = x;
            int beforeY = y;

            switch (dir) {
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
                case 'R':
                    x++;
                    break;
                case 'L':
                    x--;
                    break;
            }

            List<Integer> pathX = Arrays.asList(x, beforeX);
            Collections.sort(pathX);
            moveX.add(pathX);

            List<Integer> pathY = Arrays.asList(y, beforeY);
            Collections.sort(pathY);
            moveY.add(pathY);
        }

        int result = moveX.size() + moveY.size();

        System.out.println("[작업 이전] : " + Arrays.toString(charArr));

        for (List<Integer> move : moveX) {
            System.out.println("moveX: " + move);
        }

        for (List<Integer> move : moveY) {
            System.out.println("moveY: " + move);
        }

        System.out.println("[작업 결과] : " + result);
    }


    /*
        개선된 코드
    */

    private boolean isValidMove(int nx, int ny) {
        return -5<= nx && nx <= 5 && -5<=ny && ny <=5;
    }

    private int[] getLocation(char locationChar) {
        return switch (locationChar) {
            case 'U' -> new int[]{0, 1};
            case 'D' -> new int[]{0, -1};
            case 'R' -> new int[]{1, 0};
            case 'L' -> new int[]{-1, 0};
            default -> new int[]{0, 0};
        };
    }

    /*
      시간 복잡도 : O(N)
    */
    private void createCase(String dirs) {
        int x = 0;
        int y = 0;

        Set<String> paths = new HashSet<>();

        char[] charArr = dirs.toCharArray();        // O(N)

        for(char dir : charArr) {                   // O(N)
            int[] offset = getLocation(dir);        // O(1)
            int nx = x + offset[0];                 // O(1)
            int ny = y + offset[1];                 // O(1)

            if(!isValidMove(nx, ny)) {              // O(1)
                continue;
            }

            paths.add(nx + " " + ny + " " + x + " " + y);       // O(1)
            paths.add(x + " " + y + " " + nx + " " + ny);       // O(1)

            x = nx;
            y = ny;
        }

        int result = paths.size() / 2;                          // O(1)


        System.out.println("[작업 이전] : " + Arrays.toString(charArr));
        System.out.println("[paths] : " + Arrays.toString(paths.toArray()));
        System.out.println("[작업 결과] : " + result);
    }
}
