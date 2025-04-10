## 규칙
- 테스트 케이스는 CodingTest.java를 반드시 extend할 것

- override한 addCases 내부에서 반드시 다음과 같은 형태로 케이스 추가할 것
  1. testCases.add(() -> createCase())

- 테스트 케이스 클래스 작성 후 반드시 Main.java에 반영할 것

- 케이스 생성 위한 method 명 규칙은 다음을 따를 것
  1. 개선 전 케이스 생성시 사용한 method 명 => createCaseBefore
  2. 개선 후 최종적인 케이스 생성시 사용되는 method 명 => createCase