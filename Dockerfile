# Step 1: Use an official Maven image to build the app
FROM maven:3.8.6-openjdk-17-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml /app
COPY src /app/src

# Build the Spring Boot application using Maven
RUN mvn clean package -DskipTests

# Step 2: Create a new stage for the final image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built .jar file from the build stage
COPY --from=build /app/target/lorabackend-*.jar app.jar

# Expose the port the app runs on (default for Spring Boot is 8080)
EXPOSE 8080

# Specify the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
