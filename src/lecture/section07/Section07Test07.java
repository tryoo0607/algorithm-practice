package lecture.section07;

import common.CodingTest;

import java.util.*;
import java.util.stream.Collectors;

// 07. 좌표 정렬(compareTo)
public class Section07Test07 extends CodingTest {

    private final int N = 5;
    private final List<int[]> LIST = Arrays.asList(
        new int[]{2, 7},
        new int[]{1, 3},
        new int[]{1, 2},
        new int[]{2, 5},
        new int[]{3, 6}
    );

    @Override
    public void addCases() {

        testCases.add(() -> createCase(N, LIST));

        // 시간복잡도는 동일 -> 알고리즘 상 보다 속도 나올 수도 있음
        testCases.add(() -> createCase2(N, LIST));

        // Comparable 사용한 방식
        testCases.add(() -> createCase3(N, LIST));
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    private void createCase(int n, List<int[]> list) {

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });

        queue.addAll(list);

        StringBuilder builder = new StringBuilder();
        for(int i=0; i<n; i++) {

            int[] coordinate = queue.poll();

            if (coordinate == null) break;

            builder.append(coordinate[0])
                    .append(" ")
                    .append(coordinate[1]);

            if(i != n - 1) {
                builder.append("\n");
            }
        }

        String result = builder.toString();

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[list] : ");
        for(int[] coordinate : list) {
            System.out.println(Arrays.toString(coordinate));
        }
        System.out.println("[작업 결과] : \n" + result);
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(1)
    */
    // List.sort() 를 활용한 방식
    /*
        ### PriorityQueue 보다 List.sort() 방식이 더 나은 이유
        PriorityQueue는 내부적으로 Heap 자료 구조 사용 => 삽입/삭제 시마다 log N의 비용 발생
        List.sort는 내부적으로 TimeSort 사용 => 정렬에 최적화된 알고리즘, 일반적으로 선형 속도
        ArrayList =>  삽입/삭제 시마다 O(1)
        LinkedList => 삽입/삭제 시마다 O(N)
    */
    private void createCase2(int n, List<int[]> list) {

        List<int[]> original = new ArrayList<>();
        for (int[] arr : list) {
            original.add(Arrays.copyOf(arr, arr.length));
        }

        list.sort((a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(a[0], b[0]);
            }
        });

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int[] coordinate = list.get(i);
            builder.append(coordinate[0]).append(" ").append(coordinate[1]);
            if (i != n - 1) builder.append("\n");
        }

        String result = builder.toString();

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[list] : ");
        for(int[] coordinate : original) {
            System.out.println(Arrays.toString(coordinate));
        }
        System.out.println("[작업 결과] : \n" + result);
    }

    // Comparable => 객체들끼리의 기본 정렬 기준을 정의"**할 수 있게 해주는 인터페이스(interface)
    private class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point other) {
            if(this.x == other.x) {
                return y - other.y;
            } else {

                return x - other.x;
            }
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    /*
        시간복잡도 : O(N log N)
        공간복잡도 : O(N)
    */
    private void createCase3(int n, List<int[]> list) {

        List<Point> points = new ArrayList<>();
        for (int[] arr : list) {
            points.add(new Point(arr[0], arr[1]));
        }

        Collections.sort(points);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Point coordinate = points.get(i);
            builder.append(coordinate.toString());
            if (i != n - 1) builder.append("\n");
        }

        String result = builder.toString();

        System.out.println("[작업 이전] : ");
        System.out.println("[n] : " + n);
        System.out.println("[list] : ");
        for(int[] coordinate : list) {
            System.out.println(Arrays.toString(coordinate));
        }
        System.out.println("[작업 결과] : \n" + result);
    }
}
