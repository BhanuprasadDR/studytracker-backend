# ====== Stage 1: Build the JAR with Maven ======
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Copy source code
COPY src ./src

# Build the application (creates target/*.jar)
RUN mvn -q -DskipTests package


# ====== Stage 2: Run the built JAR ======
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Use the port your study-service uses locally (often 8082)
EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
