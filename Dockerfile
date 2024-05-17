# FROM maven:3.9.5-openjdk-21 AS build
# COPY . .
# RUN mvn clean package -DskipTests
#
# FROM openjdk:21-slim
# COPY --from=build /target/TodoLists-0.0.1-SNAPSHOT.jar TodoLists.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","TodoLists.jar"]

FROM maven:3.9.5-openjdk-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-slim

WORKDIR /app

COPY --from=build /app/target/TodoLists-0.0.1-SNAPSHOT.jar TodoLists.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "TodoLists.jar"]
