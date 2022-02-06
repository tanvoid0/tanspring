FROM openjdk:11-jdk-alpine

WORKDIR /opt/app

ARG JAR_FILE=target/spring-boot-web.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]