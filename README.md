!<img src="https://github.com/ssbswang/hermes/blob/master/img/hermes1.png" width="500px" height="auto" >

## What it is
Hermes is backend server implemented using Spring framework. It focuses
on resolving the problem of handling incoming requests by channeling the traffic
into different queues via ActiveMQ Artemis, which then can be diverted.

In addition, I've added metrics monitoring the the server activity using Grafana,
by having Prometheus to scrape the data exposed via Spring actuator.

## Running core application 
#### 1. start Spring application
- `mvn clean install` to run the unit tests and build jar
- `mvn spring-boot:run` to directly execute the application
- or run the `main` method from `HermesApplication` on your IDE

### Docker
- `docker-compose up -d hermes --build; docker logs -f <container ID>`

### Usage
- cURL: `curl -X POST http://localhost:8080/endpoint?param1=123&param2=STATE1`
- Swagger: http://localhost:8080/swagger-ui.html

## Running metrics
- `docker-compose up -d prometheus grafana`
- default credential is `admin:admin`