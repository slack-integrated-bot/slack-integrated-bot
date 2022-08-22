# Slack 통합 봇 서비스
## 제작 배경
-   여러 팀에서 사용하는 봇을 무료 플랜에서는 추가할 수 없음
-   앞으로 5기 이상에서도 봇을 많이 사용하게 된다면 무료플랜 상태에서는 해결방안으로써 통합봇을 만들어서 사용하는 방법을 제안함

## 기능 목록
- 원하는 채널로 메시지를 전달할 수 있다.
- 현재 workspace의 모든 유저의 정보를 받을 수 있다.(유저 이메일, 유저 dm code)

## Quick Start
- local
```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```
- prod
```bash
./gradlew bootRun --args='--spring.profiles.active=prod'
```


## API
[api 문서 링크](https://github.com/slack-integrated-bot/slack-integrated-bot/blob/main/docs/api.md)

## Required Slack bot Scopes
- 해당 어플리케이션을 사용하기 위해서는 slack bot에서 scope 권한을 추가해주어야 합니다.
- Bot Token Scopes
  - `chat:write`
  - `chat:write.public`
  - `users:read`
  - `users:read.email`
