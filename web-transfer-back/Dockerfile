FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package

FROM openjdk:11-jdk-slim
WORKDIR /app

ARG JAR_FILE=target/*.jar

RUN mkdir -p src/main/resources/

COPY --from=build /app/${JAR_FILE} app.jar

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]