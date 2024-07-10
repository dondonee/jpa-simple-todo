<p align="center">
  <img src="https://github.com/dondonee/jpa-simple-todo/assets/119798748/92df7dcc-4de8-49cc-a2d4-3f3049a2481a" width="500">
</p>

## 소개
- 인프런 김영한 님의 Spring Boot + JPA 강의를 들으며 만든 테이블 1개짜리 간단한 투두리스트
- 강의는 부스트코스 웹 풀스택 강의의 투두리스트를 참고

<br>

### 사용 기술
- Spring Boot 2.7.17
- Java 11
- JUnit4
- Gradle
- MySQL 8.0 (Docker)
- Thymeleaf

<br>

### 요구사항
- 할 일을 등록하면 카드가 추가된다.
- [Edit] 버튼을 누르면 수정 폼으로 이동한다.
  - 수정 폼에서는 현재 값을 보여준다.
  - [제출]을 누르면 수정이 완료되고 할 일 목록 페이지로 돌아간다.
- 카드에서 [ → ] 버튼을 누르면 진행 상태가 한 단계씩 업데이트 된다.
- 상태가 DONE인 경우 카드에 삭제 버튼 [X]가 나타나고 클릭하면 삭제된다.
- 기타 :
  - 등록 폼에서 내용지우기를 누르면 입력한 값이 지워진다.
  - 수정 폼에서 되돌리기를 누르면 입력한 값이 지워지고 원래 값이 보여진다.
  - 등록 폼이나 수정 폼에서 [제출]을 눌렀을 때 유효성을 통과하지 못하면 안내 메세지를 보여준다.

 
