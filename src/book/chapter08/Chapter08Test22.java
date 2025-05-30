package book.chapter08;

import common.CodingTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 문제 22. 베스트 앨범 **
public class Chapter08Test22 extends CodingTest {

    private final String[] GENRES = {"classic", "pop", "classic", "classic", "pop"};
    private final Integer[] PLAYS = {500, 600, 150, 800, 2500};

    @Override
    public void addCases() {

        testCases.add(() -> createCase(GENRES, PLAYS));
    }

    /*
        시간 복잡도 : O(N log N)
        공간 복잡도 : O(N)
    */
    private void createCase(String[] genres, Integer[] plays) {

        int expected = genres.length;
        float factor = 0.75f;
        int cap = (int) (expected / factor) + 1;

        HashMap<String, List<int[]>> genreMap = new HashMap<>(cap, factor);  // 공간복잡도 : O(N) : key의 공간복잡도 : O(1), value의 공간 복잡도 : O(N) 이기 때문
        HashMap<String, Integer> playMap = new HashMap<>(cap, factor);  // 공간복잡도 : O(1)

        for(int i=0; i< genres.length; i++) {               // O(N)
            String genre = genres[i];
            Integer play = plays[i];

            if(!genreMap.containsKey(genre)) {                  // O(1)
                genreMap.put(genre, new ArrayList<>());         // O(1) // 공간복잡도 O(N)
                playMap.put(genre, 0);                          // O(1)
            }

            genreMap.get(genre).add(new int[]{i, play});        // O(1) // 공간복잡도 O(1)
            playMap.put(genre, playMap.get(genre) + play);      // O(1)
        }

        List<Integer> result = new ArrayList<>();

        Stream<Map.Entry<String, Integer>> sortedGenre = playMap.entrySet().stream()
                .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));     // O(N log N)

        sortedGenre.forEach(entry -> {                                                  // O(1) : genre는 최대 100종류이기 떄문
            Stream<int[]> sortedSongs = genreMap.get(entry.getKey()).stream()
                    .sorted((o1, o2) -> Integer.compare(o2[1], o1[1]))                  // O(N log N)
                    .limit(2);                                                  // O(1)

            sortedSongs.forEach(song -> result.add(song[0]));                           // O(1)
        });

        System.out.println("[작업 이전] : ");
        System.out.println("[genres] : " + Arrays.toString(genres));
        System.out.println("[genres] : " + Arrays.toString(plays));
        System.out.println("[작업 결과] : " + result);
    }
}
