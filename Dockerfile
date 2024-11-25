# Use an official JDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the Spring Boot JAR file
COPY target/lorabackend-*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Specify the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
