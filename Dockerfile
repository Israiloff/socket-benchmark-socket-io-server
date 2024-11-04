FROM maven:3.9.6-eclipse-temurin-21-alpine as build

RUN mkdir -p $HOME/.m2 && mkdir $HOME/.m2/repository
WORKDIR $HOME/.m2

COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src

RUN mvn package

FROM eclipse-temurin:21-alpine
RUN apk add --no-cache tzdata
ENV TZ=Asia/Tashkent
RUN mkdir -p $HOME/local/bin/app/
COPY --from=build $HOME/.m2/target/*.jar $HOME/local/bin/app/app.jar
WORKDIR $HOME/local/bin/app/
ENTRYPOINT ["java", "-jar", "app.jar"]
