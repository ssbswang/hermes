FROM csanchez/maven:adoptopenjdk-11-openj9

USER root

COPY . /hermes

RUN cd hermes && mvn clean install

ENTRYPOINT ["java","-jar","/hermes/target/hermes-0.0.1-SNAPSHOT.jar"]