# spring-advanced-training

[![Travis Build Status][travis-image]][travis-url-main] [![Sonar quality gate][sonar-quality-gate]][sonar-url] [![Sonar coverage][sonar-coverage]][sonar-url] [![Sonar bugs][sonar-bugs]][sonar-url] [![Sonar vulnerabilities][sonar-vulnerabilities]][sonar-url] [![MIT licensed][mit-badge]](./LICENSE.txt)

Sample projects (source codes) for my training [Spring - Advanced](http://arnosthavelka.github.io/presentation/spring-advanced/index.html) and [Spring Boot](http://arnosthavelka.github.io/presentation/spring-boot/index.html). 
All these projects are based on Spring Boot 2.4.1 & Java 15.

#Agenda
* Spring Core - some necessary basics and hints
* Testing with Spring
* Spring MVC
* Persistence
* REST, JMX and AOP

#Projects
## sat-core
Set of examples of Spring. It covers basics (e.g. BFPP & BPP, JSR and SpEL), hints (e.g. @Conditional, notifications, Spring profiles and proxying), testing, etc.

## sat-jdbc
Usage of JDBC via Spring Boot and HSQL in memory DB.

## sat-jpa
Usage of JPA via Spring Boot and H2 in memory DB (including web console).

## sat-liquibase
Usage of JDBC & [Liquibase](http://www.liquibase.org/) via Spring Boot and HSQL in memory DB.

## sat-rest
Accessing JPA data via REST sevice, including:
* Spring Data
* [Swagger](http://swagger.io/) (REST documentation)
* [Jolokia](https://jolokia.org/) (JMX over HTTP)

## sat-spring-boot
Usage of Spring Boot, including:
* Actuator
* Remote Shell
* Advanced metrics (from dropwizard)
* Modified Health service

_Note: Requires running spring-spring-boot-admin_

## sat-spring-boot-admin
Usage of Spring Boot Admin to monitor SB applications.

_Note: Not working now (on SB 1.5.7)

[travis-url-main]: https://github.com/arnosthavelka/spring-advanced-training
[travis-image]: https://travis-ci.org/arnosthavelka/spring-advanced-training.svg?branch=master

[sonar-url]: https://sonarcloud.io/dashboard?id=arnosthavelka_spring-advanced-training
[sonar-quality-gate]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_spring-advanced-training&metric=alert_status
[sonar-coverage]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_spring-advanced-training&metric=coverage
[sonar-bugs]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_spring-advanced-training&metric=bugs
[sonar-vulnerabilities]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_spring-advanced-training&metric=vulnerabilities
[mit-badge]: https://img.shields.io/badge/license-MIT-maroon.svg