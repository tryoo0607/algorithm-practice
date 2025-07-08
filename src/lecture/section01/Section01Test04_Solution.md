```java
import java.util.Scanner;

public class Main {
    public void solution(int num, String[] strArr) {

        for (String str : strArr) {
            StringBuilder builder = new StringBuilder(str.length());

            System.out.println(builder.reverse());
        }
    }

    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        int num = in.nextInt();
        String[] strArr = new String[num];

        for(int i=0; i < num; i++) {
            strArr[i] = in.next();
        }

        Main T = new Main();
        T.solution(num, strArr);
        return ;
    }
}
```