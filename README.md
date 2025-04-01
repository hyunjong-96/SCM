# SCM

## 프로젝트 목적
- - -
Github의 OpenAPI를 이용해서 나의 github를 관리하는 웹 서비스

## 개발 목적
- - -
1. SubModule
   1. 서버 모듈과 화면모듈
2. Spring Security
   1. 구조 파악
   2. Oauth2 사용
3. OpenAPI
4. 아키텍처
   1. 다양한 아키텍처 고민해보기
5. 이론 실습
   1. Spring 3대요소
   2. Filter, Interceptor
   3. 자바함수 - stream
6. 기록
   1. 설정부터 완성까지 기록하기
7. 화면 개발
    1. Vue.js

## 구조
- - -


.SCM

├── README.md

├── front

├── scm_api

│      ├── build.gradle

│      └── src

├── scm_domain

│      ├── build.gradle

│      └── src

├── build.gradle

└── settings.gradle

---
## 기능

### [1] 사용자 레포지토리

- 레포지토리 조회
  - https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-a-user
  - 조건
    - username

### [2] commit

- 커밋 리스트 조회
  - https://docs.github.com/en/rest/commits/commits?apiVersion=2022-11-28
- 예약 커밋
  - 계획
    1. `org.eclipse.jgit.api.Git`을 이용해 커밋 푸시
    2. `github open api`를 이용해 PR 생성
    3. (1), (2) 작업을 스커줄러를 통해 실행.

### [3] Pull Request

- Pull Request 조회
  - https://docs.github.com/en/rest/pulls/pulls?apiVersion=2022-11-28
- Pull Request 생성
  - https://docs.github.com/en/rest/pulls/pulls?apiVersion=2022-11-28#create-a-pull-request
- Pull Request MR
  - https://docs.github.com/en/rest/pulls/pulls?apiVersion=2022-11-28#merge-a-pull-request
- 예약 Pull Request
  - git action? or scheduler
- 예약 Merge Pull Request
  - git action? or scheduler

### [4] 자동화 배포

- git action 또는 젠킨슨

- Pull Request 예약
  - git action 또는 스케줄러
- Commit 예약 
  - 되는지 모르겠음
