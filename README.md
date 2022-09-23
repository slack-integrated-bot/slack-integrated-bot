# Slack 통합 봇 서비스
## 제작 배경
- 현재 우아한테크코스 슬랙 내에서 봇의 사용빈도가 매우 많음 (편의 기능 + 각 팀의 프로젝트 봇)
- 추가적으로 더 많은 봇을 사용하게 된다면 무료 플랜 상태에서는 봇을 추가할 수 없음
- 봇을 추가한다고 하더라도 무분별하게 봇이 증가한다면 많은 봇을 관리하기가 힘듦
<br></br>
- 따라서 여러 봇을 사용하고, 관리하기 편하도록 하나의 통합 봇을 구축하고자 함

## 요청 흐름
![untitled@2x (11)](https://user-images.githubusercontent.com/69106910/191897590-987758b9-16f8-44b3-895d-d86bd78db46b.png)

## 기능 목록
- 원하는 채널로 메시지를 전달할 수 있다. (유저 dm 또한 유저 채널로 전달하여야한다.)
- 현재 workspace의 모든 유저의 정보를 받을 수 있다.

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
[api 문서 링크](https://github.com/slack-integrated-bot/slack-integration-bot/wiki/API-Documentation)

## 기술 스택
- Java 11
- Spring FrameWork (Spring Boot, Spring MVC, Spring JDBC)
- Gradle
- MySQL

## Required Slack bot Scopes
- 해당 어플리케이션의 기능을 사용하기 위해서는 slack bot에서 scope 권한을 추가해주어야 합니다.
- Bot Token Scopes
  - `chat:write`
  - `chat:write.public`
  - `users:read`
  - `users:read.email`
  
## developer
| [루키](https://github.com/Wishoon) | [오리](https://github.com/jinyoungchoi95) |
| :-: | :-: |
| ![](https://github.com/Wishoon.png?size=100) | ![](https://github.com/jinyoungchoi95.png?size=100) |
