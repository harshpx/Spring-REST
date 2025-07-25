# JDK image
FROM openjdk:21-slim
# Set the working directory
WORKDIR /app
# Copy the JAR file into the container
COPY target/*.jar app.jar
# Expose the port the app runs on
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]