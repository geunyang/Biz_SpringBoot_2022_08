# Spring Boot Project
* 2022-08-12

* https://start.spring.io/
* 
```
Spring Web WEB
Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
Thymeleaf TEMPLATE ENGINES
A modern server-side Java template engine for both web and standalone environments. Allows HTML to be correctly displayed in browsers and as static prototypes.
Spring Boot DevTools DEVELOPER TOOLS
Provides fast application restarts, LiveReload, and configurations for enhanced development experience.
Lombok DEVELOPER TOOLS
Java annotation library which helps to reduce boilerplate code.
```
* sts lombok 설치
* openFile
```
-vm
C:\Program Files\Java\jdk-11.0.14\bin
```
* ```application.properties``` servlet context 역할
* ```ServletInitializer.java``` web.xml 역할
* ctrl+alt+o import 정리
* @ComponentScan
Annotation 을 이용하여 Class 를 Scan 하여 bean 으로 생성하도록 하는 지시어
여러개의 Scan 이 필요할 경우 문자열 배열을 사용하여 주입한다
* SpringBootServletInitializer 클래스
Boot Project 에서 root-context.xml, servlet-context.xml 을 대신하는 클래스
* maven 입력하는 방법
# application.properties
# spring boot 시작하는데 필요한 설정을 하는곳