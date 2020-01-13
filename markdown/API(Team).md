## 공모전 도우미 API 



#### Team 객체

```java
public class Team {
  @Id @NotNull
  private String name;
  private String contestId;
  private String comment;
  private String address;
  @NotNull
  private String createdUser;
  private String state = "running..";
  private List<String> members = new ArrayList<>();
  private List<Participant> participants = new ArrayList<>();
}
```



#### Participant 객체

```java
public class Participant {
  @Id @NotNull
  private String accountId;
  @NotNull
  private String teamId;
  private String status = "waiting";
}
```



#### Team 관련 API

- GET

| 요청 URL                    | 메서드 | 응답 형식 (JSON)  | 설명                                            |
| --------------------------- | ------ | ----------------- | ----------------------------------------------- |
| /team                       | GET    | List<Team>        | team 목록 조회                                  |
| /team/{teamId}              | GET    | List<Participant> | team 가입 신청한 참가자들 목록을 조회           |
| /team/contestId/{contestId} | GET    | List<Team>        | 특정 공모전 주제에 등록되어 있는 team 목록 조회 |

- POST

| 요청 URL      | 메서드 | 응답 형식 (JSON) | 설명                                                   |
| ------------- | ------ | ---------------- | ------------------------------------------------------ |
| /team         | POST   | Team             | 팀 생성                                                |
| /team/request | POST   | Team             | 팀 가입 신청 기능                                      |
| /team/permit  | POST   | Team             | team 가입 신청 승낙 승낙 후 해당 user에게 message 전송 |
| /team/reject  | POST   | Team             | team 가입 신청 거절                                    |

- PUT

| 요청 URL      | 메서드 | 응답 형식 (JSON) | 설명                |
| ------------- | ------ | ---------------- | ------------------- |
| /team/comment | PUT    | Team             | team 소개 내용 수정 |

- DELETE

| 요청 URL       | 메서드 | 응답 형식 (JSON) | 설명                                          |
| -------------- | ------ | ---------------- | --------------------------------------------- |
| /team/{teamId} | DELETE | String           | team 제거, 평가 항목 collection에 사용자 추가 |