답안 제출 시 아래와 같이 main 메소드를 작성해야 함
- 입력 값은 Scanner로 입력받을 수 있도록 할 것
  - int -> scanner.nextInt();
  - long -> scanner.nextLong();
  - double -> scanner.nextDouble();
  - String (공백 기준 한 단어)-> scanner.next();
  - String (한줄 전체) -> scanner.nextLine();
  - char -> scanner.next().charAt(0);
  - boolean -> scanner.nextBoolean();
- 정답만 System.out.println()으로 출력할 것
- solution Method가 static이 아닌 경우 별도 인스턴스 생성할 것
  - main 메소드는 정적 메소드(static method)
  - 정적 메소드 내부에서는 인스턴스 메소드를 직접 호출할 수 없음
  - 따라서 인스턴스를 생성해 주어야 함


```java
import java.util.Scanner;
  
public class Main {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String input1 = scanner.next();
        char input2 = scanner.next().charAt(0);
        
        Main T = new Main();
        System.out.println(T.solution(input1, input2));
    }

    public int solution(String str, char targetChar) {
        int count = 0;
        for(Character c : str.toCharArray()) {
            Character testChar = Character.toLowerCase(c);
            Character target = Character.toLowerCase(targetChar);
    
            if(target.equals(testChar)) {
                count++;
            }
        }
    
        return count;
    }
}
```