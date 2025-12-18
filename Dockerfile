Phase 3: Create the Dockerfile

You need a Dockerfile in the root directory of your project to tell Render how to build and run your Java app. We will use a "Multi-stage build" to keep the image small.

File: Dockerfile
Dockerfile

# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]