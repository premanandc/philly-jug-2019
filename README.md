## Philly JUG July 2019
This is the companion source repository for the presentation on **[DevOps for the Database](https://www.meetup.com/PhillyJUG/events/261410387/)** at the Philly JUG - July 16th, 2019.

### Introduction
The project contains two Maven projects. Both use [liquibase](https://www.liquibase.org) to perform database migrations.
* migrations-standalone - suitable to use when the database is shared among multiple applications and services with no clear owner.
* migrations-composite - suitable to use when the database is owned by a single service.

### Pre-requisites
* Java 12 SDK
* Apache Maven 3.x to build and compile
* Docker 1.16+ to run tests

### How to Use
* Clone this repository
* Descend into any one of the projects and run `mvn clean install`


