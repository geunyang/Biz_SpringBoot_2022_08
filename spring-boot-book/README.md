# Spring-boot Data Project
## 도서정보 관리 프로젝트

## Spring-Data Project
* spring-data project 는 DBMS 와 연동하여 CRUD 를 구현하는 Project

### spring-JPA(Java Persistence API, Architecture) : ORM(Object Relation Mapping)
* 일반적으로 DBMS 와 연동하기 위해서는 SQL 문법, 명령구조 등을 필수로 익혀야 하나
SQL 문법에 어려움을 느끼는 개발자가 많아, SQL 을 사용하지 않고 JAVA 의 객체(클래스) 만을 
디자인하여 DBMS 와 관련된 CRUD 구현을 목표로 탄생한 도구

## Spring-data-JPA Project 시작
```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
<!-- MySQL 연동 JDBC 설정 -->
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<scope>runtime</scope>
	</dependency>
```
* Spring-data-jpa project 는 반드시 초기에 DB 설정이 있어야한다
* DB 설정이 없을 경우 프로젝트 시작이 되지 않는다
* template 이름 html 로 변경
* logback
* application.properties
```
logging.config=classpath:logback-local.xml
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
    <logger name="com.callor.book" level="DEBUG"  additivity="false">
        <appender-ref ref="STDOUT"/>
    </logger>

    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>

</configuration>
```
* save method 사용
* mybatis 에서는 insert 를 수행한 후 int 값을 return 한다
이때 return 하는 값은 몇개의 데이터가 추가되었는지 알려주는 지표이다
spring-data(JPA) 에서는 Save 를 실행한 후
다시 자신(save 를 실행한 데이터) 를 return 한다
* spring-dataq 에서는 CRUD 에서 INSERT 와 UPDATE 를 별도로 구분하지 않는다
Save() method 에 VO 를 전달하면
                1. PK 를 기준으로 데이터를 select 하여 테이블에 데이터가 있는지 검사
                2. 테이블에 PK 에 해당하는 데이터가 없으면 insert 수행
                3. 테이블에 PK 에 해당하는 데이터가 있으면 update 수행

```
        BookVO result = bookDao.save(bookVO);
```

### ddl-auto 의 update 속성
```
spring.jpa.hibernate.ddl-auto=update
```
* 원칙은 기존의 테이블이 있으면 Alter table 을 실행하여 테이블 구조를 변경한다
* 기존 VO 변수의 이름을 잘못 작성하여 변경하였을 경우 기존의 칼럼을 그대로 두고 
변경된 칼럼을 추가해 dao.save() method 에 오류를 발생시킨다
* VO 클래스의 속성 변수 이름 변경시 테이블 드롭 후 다시 만드는것을 권장한다

### 서버 자동 재시작
* pom.xml 확인
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
```
* application.properties 에 입력
```
spring.devtools.restart.enabled=true
spring.devtools.remote.restart.enabled=true
```
* file-settings-build, Execution...-compiler-Build project Auto...체크-Advanced Settings-Allow auto-make to...체크