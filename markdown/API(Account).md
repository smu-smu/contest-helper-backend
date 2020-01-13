## 공모전 도우미 API 



#### Account 관련 API

- GET

| 요청 URL                             | 메서드 | 응답 형식 (JSON) | 설명                                       |
| ------------------------------------ | ------ | ---------------- | ------------------------------------------ |
| /account                             | GET    | List<Account>    | 전체 사용자 조회                           |
| /account/{userId}                    | GET    | Account          | 특정 id를 가진 사용자 정보 조회            |
| /account/{userId}/tags               | GET    | List<String>     | 특정 id를 가진 사용자의 즐겨찾기 목록 조회 |
| /account/tag/{tag}                   | GET    | List<Account>    | 특정 tag를 즐겨찾기한 user 목록 조회       |
| /account/profile/{profile}           | GET    | List<Account>    | 특정 경력이 있는 user 목록                 |
| /account/{userId}/profile            | GET    | List<String>     | 특정 user의 경력 목록 조회                 |
| /account/{userId}/message            | GET    | List<Message>    | 특정 user의 메시지 목록 조회               |
| /account/{userId}/team               | GET    | List<String>     | 특정 user의 팀 목록 조회                   |
| /account/{userId}/tagScore           | GET    | List<TagScore>   | 특정 user의 tag 스코어 목록 조회           |
| /account/{userId}/tagScore/{tagName} | GET    | Double           | 특정 user의 특정 tag 스코어 점수 조회      |
| /account/team/{userId}               | GET    | List<String>     | 사용자가 포함되어 있는 team 목록 반환      |

- POST

| 요청 URL                   | 메서드 | 응답 형식 (JSON) | 설명                                           |
| -------------------------- | ------ | ---------------- | ---------------------------------------------- |
| /account/signin            | POST   | Account          | 사용자 인증<br /> (id, pw) 모두 일치해야 한다. |
| /account/signup            | POST   | Account          | 사용자 회원가입                                |
| /account/tag               | POST   | Account          | 사용자 계정에 선호 tag 추가                    |
| /account/profiles          | POST   | Account          | 사용자 계정에 이력(profile) 추가               |
| /account/message           | POST   | Account          | 사용자 계정에 message 추가                     |
| /account/tagScore/{userId} | POST   | Account          | 사용자의 tagScore에 새로운 tagScore 추가       |

- DELETE

| 요청 URL                              | 메서드 | 응답 형식 (JSON) | 설명                       |
| ------------------------------------- | ------ | ---------------- | -------------------------- |
| /account/{userId}                     | DELETE | String           | 사용자 삭제                |
| /account/message/{userId}/{messageId} | DELETE | Account          | 사용자 계정의 message 삭제 |