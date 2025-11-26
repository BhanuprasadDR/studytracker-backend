# ====== Stage 1: Build the JAR with Maven ======
FROM maven:3.9-eclipse-temurin-21 AS build

# Set working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies first (better caching)
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Now copy the source code
COPY src ./src

# Build the application (this creates target/*.jar)
RUN mvn -q -DskipTests package


# ====== Stage 2: Run the built JAR ======
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Use the port your discovery-service actually runs on
EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]
