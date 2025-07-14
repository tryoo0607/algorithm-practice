```java
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public String[] solution(int n, int[] inputA, int[] inputB) {
    Map<Integer, Integer> winMap = new HashMap<Integer, Integer>(){{
      put(1,2);
      put(2,3);
      put(3,1);
    }};

    String[] result = new String[n];

    for(int i=0; i < n; i++) {
      if(inputA[i] == inputB[i]) {
        result[i] = "D";
      } else {
        if(winMap.get(inputA[i]) == inputB[i]) {
          result[i] = "B";
        } else {
          result[i] = "A";
        }
      }
    }
    
    return result;
  }
  
  public static void main(String[] args){
    Scanner in=new Scanner(System.in);
    int n = in.nextInt();
    int[] inputA = new int[n];
    int[] inputB = new int[n];
    for(int i=0; i < n; i++) {
      inputA[i] = in.nextInt();
    }
    
    for(int i=0; i < n; i++) {
      inputB[i] = in.nextInt();
    }
    
    Main T = new Main();
    
	String[] resultArr = T.solution(n, inputA, inputB);
    
    for(String result : resultArr) {
    	System.out.println(result);
    }

    return ;
  }
}
```