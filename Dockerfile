# Stage 1: Build the application using Maven and OpenJDK 21
FROM maven:3.9.2-openjdk-21 AS build
WORKDIR /app

# Copy only Maven files first for caching dependencies
COPY pom.xml .
COPY src ./src

# Build the Spring Boot jar
RUN mvn clean package -DskipTests

# Stage 2: Run the application using OpenJDK 21 slim variant
FROM openjdk:21-jdk-slim-bullseye
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
