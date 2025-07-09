- 주의 : 공백이 포함된 문자열이므로 nextLine 사용해야함

```java

import java.util.Scanner;
  
public class Main {
  public String solution(String str) {
    
    int lt = 0;
    int rt = str.length() - 1;

    str = str.toLowerCase();

    boolean isTrue = true;
    while(lt < rt) {
      char strLt = str.charAt(lt);
      char strRt = str.charAt(rt);

      if(!Character.isAlphabetic(str.charAt(lt))) {
        lt++;
      } else if(!Character.isAlphabetic(str.charAt(rt))) {
        rt--;
      } else {
        if(strLt != strRt) {
          isTrue = false;
          break;
        }

        lt++;
        rt--;
      }
    }
    
    String result = isTrue ? "YES" : "NO";
    return  result;
  }
  
  public static void main(String[] args){
    Scanner in=new Scanner(System.in);
    String str = in.nextLine();
    
    Main T = new Main();
    System.out.println(T.solution(str));
    return ;
  }
}
```