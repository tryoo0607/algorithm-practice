package chapter05;

import model.CodingTest;

import java.util.Arrays;
import java.util.Collections;

// 문제 02. 배열 제어하기
public class Chapter05Test02 extends CodingTest {

    private final int[] arr1 = {4, 2, 2, 1, 3, 4};
    private final int[] arr2 = {2, 1, 1, 3, 2, 5, 4};

    @Override
    public void addCases() {
        testCases.add(() -> createCase(arr1));
        testCases.add(() -> createCase(arr2));
    }

    /*
        전체 흐름에서 가장 느린(=복잡도가 높은) 연산이 전체 시간 복잡도를 결정함
        - Big-O 표기법은 정확한 시간 측정이 아니라, "입력 크기 N이 커질 때 알고리즘이 얼마나 빨리 느려지는가"를 나타내는 개념적 도구
        - 모든 연산의 시간 복잡도 중 가장 큰 것을 전체 복잡도로 봄

        => 아래에선 O(n log n)이 가장 크므로 시간 복잡도는 O(n log n)
    */
    private void createCase(int[] arr) {

        // Stream -> 참조타입에서만 사용 가능
        // Stream<Integer> -> 이런 식으로 제네릭을 응용하는 클래스 => 제네릭은 원시 타입을 대상으로는 사용할 수 없기 때문에 Boxing 필요
        // int[]는 IntStream이 별도로 존재하긴 함
        Integer[] distinct = Arrays.stream(arr)     // O(1)
                .boxed()                            // O(n) // 기본형을 Boxing (int -> Integer)
                .distinct()                         // O(N) // Stream의 메소드 -> Stream<Integer>에서 중복 제거
                .toArray(Integer[]::new);           // O(N) // toArray() 만 쓰면 => Object[] 타입이 되버림 => 따라서 타입 힌트 필요

        /*
        - toArray(Integer[]::new) == toArray(size -> new Integer[size])
          => toArray 생성 시에 배열을 생성하는 메소드를 전달해야 함
        */

        /*
        - IntStream -> sort는 가능하지만 "오름차순"만 가능함
        - 때문에 위에서 Boxing 작업을 통해 Stream<Integer>로 변환한 것

        - Arrays.sort(T[] a, Comparator<? super T> c)
            - 두번째 인자 : 정렬 기준을 나타내는 Comparator 객체 (비교 로직을 가진 객체)

        - Collections.reverseOrder()
            - 정렬 기준을 반대로 만들어주는 Comparator를 반환하는 정적 메소드
            - 무언가를 실행하는 메서드라기보단, 비교기(Comparator)를 만들어 반환하는 팩토리 메서드

        - Comparator
            - 함수형 인터페이스
            - 비교 메서드 int compare(T o1, T o2)를 가지고 있음
            - 즉, T를 비교 가능하게 해주는 기능을 가짐
                - 어떤 기준에 따라 어떤 값을 우위로 둘지를 비교해줌 (어떤 두 값을 어떻게 비교할지를 정의하는 객체)
            - 이 Comparator 안에는 Comparator를 반환하는 도우미 메소드들이 존재함
                - 그 중 하나가 reverseOrder
                - 이 reverseOrder를 통해 정의된 Comparator의 기준은 더 큰 값을 앞으로 오게끔 함
            - Collections는 실제 구현된 Util (인터페이스 아닌, 유틸리티 클래스) => 모든 메서드가 Static임
                - 내림차순 Comparator 반환
        */

        // 대신 Comparator.reverseOrder()를 써도 됨 -> 여기서 내부적으로 Collections.reverseOrder()를 호출하기 때문
        Arrays.sort(distinct, Collections.reverseOrder());      // O(n log n)

        /*
        - mapToInt
            - 매개변수로 전달하는 메소드를 이용해서 Stream<T>를 IntStream으로 바꾸는 메소드
                - 매개변수로 IntStream으로 바꾸기 위한 메소드를 전달해야함 (즉, return이 int인 메소드면 가능함)
            - Steram<T>에만 존재
            - Stream<String>도 사용 가능
                - ex) IntStream intStream = stream.mapToInt(String::length);
        */

        /*
        - Stream<T>의 .toArray()의 기본 return type => Object[]
        - IntStream의 .toArray()의 기본 return type => int[]
            - IntStream은 매개변수를 받지 않음
        */
        int[] result = Arrays.stream(distinct)      // O(1) // Integer[] -> Stream<Integer>
                .mapToInt(Integer::intValue)        // O(N) // unboxing 실행
                .toArray();                         // O(N)

        System.out.println("[정렬 결과] : " + Arrays.toString(result));
    }
}
