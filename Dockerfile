# Use Maven image to build the application
FROM maven:3.9.9-eclipse-temurin-17 AS build
# Set working directory
WORKDIR /app

# Copy source code and pom.xml
COPY . .

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Use JDK to run the application
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Specify the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
