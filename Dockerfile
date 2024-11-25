# Step 1: Use an official Java runtime as the base image
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the JAR file from the target folder
# The JAR file will be generated in the `target` directory after the Maven build
COPY target/*.jar app.jar

# Step 4: Expose the port your Spring Boot application listens on (default is 8080)
EXPOSE 8080

# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
