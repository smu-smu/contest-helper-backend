## 공모전 도우미 API 



#### Competition 객체

```java
public class Competition {
  @Id
  private String id;
  private String name;
  private List<String> category = new ArrayList<>();
  private String group;
  private Date startDate;
  private Date endDate;
}
```



#### Competition 관련 API

- GET

| 요청 URL      | 메서드 | 응답 형식 (JSON)  | 설명                  |
| ------------- | ------ | ----------------- | --------------------- |
| /contest/list | GET    | List<Competition> | 전체 공모전 목록 조회 |
