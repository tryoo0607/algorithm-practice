```java

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
  public String solution(int n, int[] numArr) {
    ArrayList<Integer> resultArr = new ArrayList<>();
    resultArr.add(numArr[0]);

    for(int i=0; i < n - 1; i++) {
      if(numArr[i] < numArr[i+1]) {
        resultArr.add(numArr[i+1]);
      }
    }

    String result = resultArr.stream()
      .map(String::valueOf)
      .collect(Collectors.joining(" "));
    
    return result;
  }
  
  public static void main(String[] args){
    Scanner in=new Scanner(System.in);
    int n = in.nextInt();
    int[] numArr = new int[n];
    for (int i = 0; i < n; i++) {
      numArr[i] = in.nextInt(); 
    }

    Main T = new Main();
    System.out.println(T.solution(n, numArr));
    return ;
  }
}
```