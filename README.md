# 인프런 JPA 강의 실습
스프링부트로 JPA 강의 실습을 하기 위한 프로젝트

강의에서는 순수 자바 코드로 JPA를 사용하였지만, 이 프로젝트는 실제 프로젝트에서 사용하는 스프링부트를 사용하여 실습을 진행한다.

### 환경 정보
 - Kotlin 1.7.22(JDK 18)
 - Spring Boot 3.0.3
 - h2 DB(테스트 코드)
 - MySQL 8.0.31(프로덕션 코드)
 - Springdoc 2.0.2

### 주의 사항
SpringBoot 3.0.X 이상부터는 Swagger UI를 사용하기 위해 추가해야 하는 의존성이 변경되었다.
```groovy
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
```
Swagger UI 사용 시 Validation을 위한 의존성도 추가해야 한다.
```groovy
implementation 'org.springframework.boot:spring-boot-starter-validation'
```

### 참고
 - [인프런 JPA 강의](https://www.inflearn.com/course/ORM-JPA-Basic/dashboard)
 - [Springdoc](https://springdoc.org/)