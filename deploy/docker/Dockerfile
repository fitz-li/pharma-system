# -------------------------------------------------------------------
# Stage 1: Build the Spring Boot JAR using Gradle
# -------------------------------------------------------------------
FROM gradle:8.2.1-jdk17 AS builder

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and configuration
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Ensure wrapper is executable
RUN chmod +x gradlew

# Copy the rest of the source code
COPY . .

# Build the JAR (skip tests for faster builds; remove -x test if you want tests)
RUN ./gradlew clean build -x test --no-daemon

# -------------------------------------------------------------------
# Stage 2: Run the application on a lightweight JRE image
# -------------------------------------------------------------------
FROM openjdk:17-jre-slim

# Optional: create a non-root user for security
RUN useradd --create-home appuser
USER appuser

WORKDIR /home/appuser

# Copy the built JAR from the builder stage
# Adjust the JAR file name if your project outputs a different name
COPY --from=builder /app/build/libs/pharmaflow-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Launch the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]