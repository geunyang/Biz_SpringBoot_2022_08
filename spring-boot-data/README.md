# Spring Data Project
* Spring Data Project Dependencies
* starter-data-jdbc 는 Spring Data(JPA) 를 사용시 호환성 때문에 사용하며 현재 사용하는 Spring-boot 버전에서는 선택사항이다
```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
```

* application.properties 설정
* *.properties 파일에서 한글 주석 입력하면 encode 오류
* ```spring.jpa.hibernate.ddl-auto=create``` table 생성하고 새로고침하면 기존 데이터 삭제후 table 재생성
* ```spring.jpa.hibernate.ddl-auto=create-drop``` table 생성후 프로젝트 종료시 clear
* ```spring.jpa.hibernate.ddl-auto=none``` 데이터 그대로 유지
* ```spring.jpa.hibernate.ddl-auto=update``` table 생성후 사용하면 alter table 실행
* ```spring.jpa.hibernate.ddl-auto=validate``` 물리데이터와 column 불일치시 오류 발생

* UserRepository 설정
* Spring-data(JPA)에서는 Genericdao 대신 JpaRepository 를 상속받아 사용한다
* 자동 import : 빨간색으로 나타나는 클래스, method 에 커서를 두고 ALT+ENTER
* import 최적화 : Ctrl + ALT + O

* css, js, image 등을 공개할 디렉토리 선언
```
  spring.web.resources.static-locations=classpath:static/
  spring.mvc.static-path-pattern=/static/**
  spring.web.resources.add-mappings=true
```

* HomeController 설정
* ```
  @RequestMapping(value={"","/"}, RequestMethod=GET)
  @GetMapping(value = {"","/"})
  ```
* logback-local.xml
```
<?xml version="1.0" encoding="UTF-8" ?>
<configuration scan="true" scanPeriod="3 seconds">

   <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <charset>UTF-8</charset>
            <Pattern>  [%-5level] %C{5} - %msg  %n</Pattern>
        </encoder>
    </appender>

    <logger name="org.springframework" level="INFO" />
    <logger name="com.callor.hello" level="DEBUG"  additivity="false">
        <appender-ref ref="STDOUT"/>
    </logger>

    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>

</configuration>
```
* logback-local.xml 파일을 설정파일로 지정하기
```
application.properties
logging.config=classpath:log/logback-local.xml
```