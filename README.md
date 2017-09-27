# Hedi Apps

# Table of Content
* [Contributors](#contributors)
* [Application Architecture](#application-architecture)
* [Microservices Overview](#microservices-overview)
* [Netflix OSS](#netflix-oss)
* [Spring Boot Overview](#spring-boot-overview)
* [Spring Cloud Overview](#spring-cloud-overview)
* [Spring Cloud Netflix Overview](#spring-cloud-netflix-overview)
* [OAuth 2.0 Overview](#oauth-2.0-overview)
* [Spring OAuth 2.0 Overview](#spring-oauth-2.0-overview)


## <a name="contributors"></a>Contributors

* [Hedi Ben Ahmed](https://www.linkedin.com/in/hedi-ben-ahmed-36923925/)

## <a name="application-architecture"></a>Application Architecture

The application consists of 5 different services:

* [hediapps-eureka-server](hediapps-eureka-server/README.md) - Eureka server
* [hediapps-authentification](hediapps-authentification/README.md) - Oauth2 authorization server
* [hediapps-messaging](hediapps-messaging/README.md) - messaging micro-service
* [hediapps-apigateway](hediapps-apigateway/README.md) - API gateway that proxies all the micro-services using Zuul
* [hediapps-web](web-portal/README.md) - the web application

### Application Components
![Components](/images/Application_Components.jpg)

## <a name="microservices-overview"></a>Microservices Overview

There is a growing adoption of Microservices in today's world. Numerous SAAS Companies are moving away from building monolithical products and instead adopting Microservices.

### Focus on Component

In microservices world, a web service or a microservice is the unit of component. This unit of component delivers a complete Business functionality and could rely on other microservices to fulfill the dependent functionality. These microservices are build separately and deployed separately unlike monoliths in which components can be built separately but are deployed together.

![Microservices Overview](http://martinfowler.com/articles/microservices/images/sketch.png)

### Focus on Business Capabilities and Running a Product

Another key aspect of microservices is that the focus of a team building a component now moves away from just delivering that component to running and maintaining that business functionality given by that component.

![Focus on Business Capabilities](http://martinfowler.com/articles/microservices/images/conways-law.png)

### Focus on Decentralized Control and Decentalized Data Management

Due to the ability to build components separately and running them separately means, the notion of centralized control (governance) and data management goes away. Traditionally monoliths were built around a set of set architecture, technology and frameworks. The key architects would decide what tech was used and key DBAs would decide which and how many databases are used.
With Microservices, since each component caters to a somewhat complete business functionality, that centralized control by Key Architects and DBAs goes away. Some Components are best built using JEE and RDBMS, for some Real time Data Analytics is the key, they could use Apache Storm and Apache Kafka, for some others R is better fit, for IO Intensive systems may be Node.js and MongoDB works out. Same way User data could now go in NoSQL databases, Transaction data could go in traditional RDBMS, Recommendation systems could use Hive as their Database and so on.

**Decentralized Control**
![Decentralied Control](/images/Decentralized Goverance.png)


**Decentalized Data Management**
![Decentralied Control](http://martinfowler.com/articles/microservices/images/decentralised-data.png)

Disclaimer - While microservices is much talked about these days, make a note Microservices is not a Free lunch. There is an effort and complexity involved to building and running them, but once you do so, the benefits are plentiful.

You can read more about Microservices here - http://martinfowler.com/articles/microservices.html#CharacteristicsOfAMicroserviceArchitecture

Image References from - http://martinfowler.com/articles/microservices.html

## Netflix OSS

Netflix is one of the pioneers behind the Microservices Architecture. Not only have they successfully run Microservices in production, but they have outsourced their battle hardened framework under Netflix Open Source Software Center initiative - http://netflix.github.io/#repo

You will find implementation of numerous of Netflix's Microservices platform pieces here. Here are few for your reference
### Eureka
Microservices is somewhat like SOA platform, that there are numerous services. Each Service when it comes online registers itself with Service Registry. When some other service wants to communicate with a already registered service, they would ask the Eureka Server the base url for that service. Multiple instances of the same service could register with Eureka, in that case Eureka could help in doing Load Balancing.

### Zuul
A Microservice environment needs a gateway. A Gateway is the only entity exposed to the outside world, which allows access to Microservices and does more. A Gateway could do
* API Metering
* Centralized Authentication/Authorization
* Load Balancing
* etc
*

## Spring Boot Overview

Folks who are familiar with Spring frameworks like Spring MVC, know spring is all about Dependency Injection and Configuration Management. While Spring is an excellent framework, it still takes quite some effort to make a Spring MVC project ready for production.

Spring Boot is Spring's approach towards Convention over Configuration. Spring Boot comes with numerous Start Projects, each starter projects provides a set of conventions which ensures you have a opinionated production ready app.

You can read in detail about Spring Boot here - https://spring.io/guides/gs/spring-boot/

## Spring Cloud Overview

Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems (e.g. configuration management, service discovery, circuit breakers, intelligent routing, micro-proxy, control bus, one-time tokens, global locks, leadership election, distributed sessions, cluster state)

You can read in detail about Spring Cloud here - http://projects.spring.io/spring-cloud/

## Spring Cloud Netflix Overview

Spring Cloud Netflix provides Netflix OSS integrations for Spring Boot apps through autoconfiguration and binding to the Spring Environment and other Spring programming model idioms.

You can read in detail about Spring Cloud Netflix here - http://cloud.spring.io/spring-cloud-netflix/

## OAuth 2.0 Overview

OAuth2 is an authorization framework that specifies different ways a third-party application can obtain limited access to determined set of resources.

![OAuth2 abstract protocol](/images/OAuth2 abstract protocol flow.png)

OAuth defines four roles:

   **resource owner:**
      An entity capable of granting access to a protected resource. When the resource owner is a person, it is referred to as an end-user.

   **resource server:**
      The server hosting the protected resources, capable of accepting and responding to protected resource requests using access tokens.

   **client:**
      An application making protected resource requests on behalf of the resource owner and with its authorization.  The term "client" does not imply any particular implementation characteristics (e.g., whether the application executes on a server, a desktop, or other devices).

   **authorizationserver:**
      The server issuing access tokens to the client after successfully authenticating the resource owner and obtaining authorization.

To get more details of how differnt authorizations work in OAuth2, please refer to the readme at **[auth-server](auth-server/README.md)**

## Spring OAuth2 Overview

Spring provides nice integration between Spring security and OAuth2 providers including the ability to setup your own authorization server. Please see [Spring security with OAuth2](http://projects.spring.io/spring-security-oauth/docs/oauth2.html) for more details.
