# Spring A조 포트폴리오
* spring framework 기반의 웹사이트 개발<br/>
* 2021.09.23~2021.10.31

<hr/>

## coding-convention
* 명명 규칙
  * Java 
    * 변수, 함수 : 카멜 케이스
    ```java
    private TestService testService;
    private String tableName;
    public void getTableName() { return tableName; }
    ```
    * 상수 : 대문자 스네이크 케이스
    ```java
    private final int MAX_COUNT = 10;
    ```
  * Database
    * 테이블, 뷰, 컬럼 : 소문자 스네이크 케이스
    ```
    table : free_board
    view : free_board_view
    column : no;
    column : member_no;
    ```
    * 컬럼의 접두어는 외부 테이블의 경우에만 사용하되, 줄여쓰지 않습니다.
    ```
    ex) table : board
    board_no (X) -> no (O)
    mem_no (X) -> member_no (O)
    ```
* 상대경로, 절대경로 규칙
  * href, link등 상대경로 설정 시 './'를 사용하지 않습니다.
  * Spring Annotation을 사용할 때도 마찬가지입니다.
  ```java
  @GetMapping("./write.do") X -> @GetMapping("write.do") O
  ```
* Spring Annotation 규칙
  * 기본적으로 @Controller, @Service, @Resource의 name을 설정하지 않습니다.
    * 스프링 프레임워크가 자동 생성해주는 name을 사용합니다.
    * 클래스명이 카멜 케이스로 변환되어 자동 생성됩니다.
    ```java
    ex) TestService -> testService
    ```
    
