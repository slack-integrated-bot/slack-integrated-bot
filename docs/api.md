### All header
- 해당 어플리케이션의 모든 요청은 인증키값이 필수적으로 필요합니다.
- 따라서 제공되는 키값을 항상 포함하여야 인증이 통과됩니다.
```http
Key: woowateams-secret-key
```
#### error
- header에 키값이 존재하지 않는 경우
```http
HTTP/1.1 401 Unauthorized
```
- header의 키값이 제공된 키가 아닌 경우
```http
HTTP/1.1 401 Unauthorized
```

---

### 채널 메시지 전달
`POST /api/send`
- request body
    - request body의 경우 slack에서 전송가능한 모든 케이스를 제공하기 위해 slack에서 제공되는 `SlackPostMessageRequest` 의 json format을 그대로 따라가고 있습니다.
    - json format의 경우 [링크](https://api.slack.com/messaging/composing/layouts#building-attachments) 참조 부탁드립니다.

- response body
```http
HTTP/1.1 200 OK
```
#### error
- 채널이 존재하지 않는 경우
```http
HTTP/1.1 404 not found
```
- 서버와 슬랙의 연결이 원할하지 않는 경우
```http
HTTP/1.1 500 internal server error
```

---

### 모든 유저 정보
`GET /api/users`
- request body
```
none
```
- response body
```http
HTTP/1.1 200 OK
{
  "members": [
    {
      "id": "USLACKBOT",
      "profile": {
        "email": null
      }
    },
    {
      "id": "U03U987DB0W",
      "profile": {
        "email": "heonga2@gmail.com"
      }
    },
    {
      "id": "U03V5H72L1W",
      "profile": {
        "email": "jinyoungchoi95@gmail.com"
      }
    }
  ]
}
```
workspace에 추가되어있는 bot들도 모두 포함이 되며, bot들은 email이 null로 표기될 수 있습니다.

#### error
- 서버와 슬랙의 연결이 원할하지 않는 경우
```http
HTTP/1.1 500 internal server error
```
