# spring-advanced-training

[![Java CI with Maven][github-image]][github-url-main] [![Sonar quality gate][sonar-quality-gate]][sonar-url] [![Sonar coverage][sonar-coverage]][sonar-url] [![Sonar bugs][sonar-bugs]][sonar-url] [![Sonar vulnerabilities][sonar-vulnerabilities]][sonar-url] [![MIT licensed][mit-badge]](./LICENSE)

Sample projects (source codes) for my training [Spring - Advanced](http://arnosthavelka.github.io/presentation/spring-advanced/index.html) and [Spring Boot](http://arnosthavelka.github.io/presentation/spring-boot/index.html). 
All these projects are based on Spring Boot 3.1.x & Java 21.

#Agenda
* Spring Core - some necessary basics and hints
* Testing with Spring
* Spring MVC
* Persistence
* REST, JMX and AOP

#Projects
## sat-core
Set of examples of Spring. It contains examples e.g. for: 
* Smart dependency injection
* @Conditional & notifications
* Spring profiles and proxying
* BFPP & BPP
* Testing
* JSR and SpEL

## sat-elk
Usage of Elasticsearch 7 via Spring Data & Docker image.

## sat-jdbc
Usage of JDBC via Spring Boot and HSQL in memory DB.

## sat-jpa
Usage of JPA via Spring Boot and H2 in memory DB (including web console).
* Usage of `spring-data-jpa`
* Generated JPA static metamodel
* QueryDsl
* Liquibase
* H2 database (console is accessible as: http://localhost:8080/h2-console/ -> JDBC_URL=`jdbc:h2:mem:testdb`)

## sat-liquibase
Usage of JDBC & [Liquibase](http://www.liquibase.org/) via Spring Boot and HSQL in memory DB.

## sat-rest
Accessing JPA data via REST sevice, including:
* Actuator & Modified Health service
* Spring Data & HATEOAS
* Spring Boot Admin Client
* Advanced metrics (Micrometer via JMX)
* [Springdoc Openapi](http://localhost:8080/swagger-ui.html) (REST documentation)

## sat-elk
Accessing ELK data via Spring Data Elasticsearch

## sat-spring-boot-admin
Usage of Spring Boot Admin to monitor SB applications.

http://localhost:8085/

[github-url-main]: https://github.com/arnosthavelka/spring-advanced-training/actions/workflows/maven.yml
[github-image]: https://github.com/arnosthavelka/spring-advanced-training/actions/workflows/maven.yml/badge.svg

[sonar-url]: https://sonarcloud.io/dashboard?id=arnosthavelka_spring-advanced-training
[sonar-quality-gate]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_spring-advanced-training&metric=alert_status
[sonar-coverage]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_spring-advanced-training&metric=coverage
[sonar-bugs]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_spring-advanced-training&metric=bugs
[sonar-vulnerabilities]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_spring-advanced-training&metric=vulnerabilities
[mit-badge]: https://img.shields.io/badge/license-MIT-maroon.svg
