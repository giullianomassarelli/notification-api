# Notification API
Project developed to import data from an Excel spreadsheet and notify the user by email when has a negative value in some month in Excel sheet.

### Pre-requisites
What do you need to install to run the project?
* [Docker](https://www.docker.com/)
* [Docker-Compose](https://docs.docker.com/compose/)
* [Gradle](https://gradle.org/)
* [JDK-17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [JRE-17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

### Installation
git clone git@github.com:giullianomassarelli/notification-api.git

### Docker Compose Build and Run with dev profile
```
sh docker-compose-dev.sh
```

### 
To access Swagger documentation:
```
http://localhost:8080/swagger-ui.html#/
```
### Technologies used

* [Gradle](https://gradle.org/) - From mobile apps to microservices, from small businesses to large enterprises, Gradle helps teams build, automate, and deliver better software, faster.
* [Spring Boot Web Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web) - Starter for building web, including RESTful apps, using Spring MVC. Uses Tomcat as the default embedded container
* [Spring Boot Test Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test) - Starter for testing Spring Boot applications with libraries including JUnit, Hamcrest and Mockito.
* [Lombok](https://projectlombok.org/) - Project Lombok is a java library that automatically plugs into your editor and builds tools, spicing up your java. Never write another getter or equals method again, with an annotation your class has a full-featured constructor, automate your registry variables, and more.
* [Apache POI](https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml)  - Project Responsible for reading Excel files and importing them into JAVA
* [Swagger](https://swagger.io/) - Simplify API development for users, teams, and enterprises with the professional, open source Swagger toolset.
* [Commons Email](https://commons.apache.org) - Commons Email aims to provide a API for sending email. It is built on top of the Java Mail API, which it aims to simplify.