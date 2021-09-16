# Spring A조 포트폴리오
* spring framework 기반의 웹사이트 개발<br/>
* 2021.09.23~2021.10.31

<hr/>

## Project Setup
![setup01](https://user-images.githubusercontent.com/28214971/133531373-1f241b30-e7ac-4a0a-8ba1-98d302235574.png)
리포지토리와 일치하는 폴더를 워크스페이스로 설정합니다.
![setup02](https://user-images.githubusercontent.com/28214971/133531586-5378cd10-96bb-41b5-9676-85113251a28d.png)
기본 설정된 서버는 삭제합니다. - 1
![setup03](https://user-images.githubusercontent.com/28214971/133531588-24410bb2-3c78-4830-9ede-02f6dd3479f0.png)
기본 설정된 서버는 삭제합니다. - 2
![setup04](https://user-images.githubusercontent.com/28214971/133531589-1c4f4545-2eae-443a-894a-8a58983e5123.png)
프로젝트를 임포트합니다.
![setup05](https://user-images.githubusercontent.com/28214971/133531590-a68d311c-a6ab-452c-b8fd-14ced0b55278.png)
웹프로젝트로 동작하도록 설정합니다 - 1
![setup06](https://user-images.githubusercontent.com/28214971/133531591-af3d80cc-ffc3-42f9-b3de-72d261097441.png)
웹프로젝트로 동작하도록 설정합니다 - 2
![setup07](https://user-images.githubusercontent.com/28214971/133531592-2a79abc9-879e-48de-ae8d-438cc1ed441f.png)
실행과 동시에 톰캣 서버를 설정합니다. - 1
![setup08](https://user-images.githubusercontent.com/28214971/133531593-61ca62f1-8178-47ee-99dd-e83be2c93d95.png)
실행과 동시에 톰캣 서버를 설정합니다. - 2

## Coding-Convention
* 명명 규칙
  * Java 
    * 변수, 함수 : 카멜 케이스
    ```java
    private TestService testService;
    private String tableName;
    public String getTableName() { return tableName; }
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
    
