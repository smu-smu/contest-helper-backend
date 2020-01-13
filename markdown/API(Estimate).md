## 공모전 도우미 API 



#### Estimate 객체

```java
public class Estimate {
  @NotNull
  private String accountId;
  @NotNull
  private String personId;
  @NotNull
  private String teamId;
  @NotNull
  private String contestId;
  @Min(0)
  private Double score;
}
```



#### Estimate 관련 API

- GET

| 요청 URL           | 메서드 | 응답 형식 (JSON) | 설명                           |
| ------------------ | ------ | ---------------- | ------------------------------ |
| /estimate/{userId} | GET    | List<Estimate>   | 해당 사용자의 평가할 목록 조회 |

- DELETE

| 요청 URL       | 메서드 | 응답 형식 (JSON) | 설명                                    |
| -------------- | ------ | ---------------- | --------------------------------------- |
| /team/{teamId} | DELETE | String           | 사용자가 해당 사용자에 대한 평가를 했음 |