FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/TodoLists-1.0-SNAPSHOT.jar TodoLists.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","TodoLists.jar"]


#
#FROM maven:3-openjdk-17 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests

#FROM openjdk:17-slim
#WORKDIR /app
#COPY --from=build /app/target/TodoLists-1.0-SNAPSHOT.jar TodoLists.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "TodoLists.jar"]



#
## Use an OpenJDK 17 base image
#FROM openjdk:17-slim
#
#WORKDIR /app
#
## Copy the JAR file from the local machine to the container
#COPY target/TodoLists-0.0.1-SNAPSHOT.jar TodoLists.jar
#
## Expose the port your application runs on
#EXPOSE 8080
#
## Set the entry point for the container
#ENTRYPOINT ["java", "-jar", "TodoLists.jar"]






#
## Stage 1: Build the application
#FROM openjdk:21-slim AS build
#
## Install Maven
#RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*
#
## Set the working directory
#WORKDIR /app
#
## Copy the pom.xml and source code into the working directory
#COPY pom.xml .
#COPY src ./src
#
## Run the Maven build
#RUN mvn clean package -DskipTests
#
## Stage 2: Create the runtime image
#FROM openjdk:21-slim
#
## Set the working directory
#WORKDIR /app
#
## Copy the JAR file from the build stage
#COPY --from=build /app/target/TodoLists-0.0.1-SNAPSHOT.jar TodoLists.jar
#
## Expose the port your application runs on
#EXPOSE 8080
#
## Set the entry point for the container
#ENTRYPOINT ["java", "-jar", "TodoLists.jar"]
