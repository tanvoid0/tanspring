FROM openjdk:11

WORKDIR /app

## APP 1
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#RUN ./mvnw clean
#COPY src .src


## APP 2
COPY . /app
EXPOSE 8080

RUN ./mvnw dependency:go-offline
ENTRYPOINT ["./mvnw", "spring-boot:run"]