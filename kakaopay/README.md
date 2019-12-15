# URLShortening Service

### 소개
URL을 입력받아 짧게 줄여주고, Shortening 된 URL 을 입력하면 원래 URL 로 리다이렉트하는 URLShortening Service

### 요구사항
* webapp 으로 개발하고 URL 입력폼 제공 및 결과 출력
* URL Shortening Key 는 8 Character 이내
* 동일한 URL에 대한 요청은 동일한 Shortening Key
* Shortening 된 URL 을 요청받으면 원래 URL로 리다이렉트

### 구현
* URL 입력폼 제공 및 결과 출력은 단일 페이지 사용
* 시스템 시간 + 난수 를 활용하여 base62로 encoding(8 Chracter 이내)
* 동일 URL인 경우 동일한  Shortening Key 조회
* Shortening URL 호출 시 원래 URL로 REDIRECT

### 실행
* java 1.8 이상 설치
* target 아래의 kakaopay-0.0.1-SNAPSHOT.war 파일을 다음의 명령으로 실행
* java -jar kakaopay-0.0.1-SNAPSHOT.war

### 사용기술
* Java 1.8
* Spring Boot 2.2.2
* H2 embedded DB 1.4.193
* junit 5
