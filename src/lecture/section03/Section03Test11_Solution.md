```java
import java.util.Scanner;
  
public class Main {
  private int solution(int n, int[][] grid){
    int maxCount = 0;
    // 만약 모든 학생이 같은 반이 된 적이 없던 경우 -> 첫번째 학생이 되도록
    int result = 1;

    // 학생 A(비교 대상)
    for(int i=0; i < n; i++) {
      int count=0;

      for(int j=0; j < n; j++) {
        if(i == j) continue;

        // 학년을 나타내는 index
        for(int k=0; k<5; k++) {
          if(grid[i][k] == grid[j][k]) {
            count++;
            break;
          }
        }
      }

      if(count > maxCount) {
        maxCount = count;
        result = i + 1;
      }
    }
    
    return result;
  }
  
  public static void main(String[] args){
    Scanner in=new Scanner(System.in);
    int n = in.nextInt();
    // 5학년 까지 있으므로 -> 5
    int[][] grid = new int[n][5];
    
    for(int i=0; i<n; i++) {
    	for(int j=0; j<5; j++) {
        	grid[i][j] = in.nextInt();
        }
    }
    
    Main T = new Main();
    System.out.println(T.solution(n, grid));
    return ;
  }
}
```