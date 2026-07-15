# syntax=docker/dockerfile:1

# ---- Etapa 1: build ----
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -q -B dependency:go-offline

COPY src ./src
RUN ./mvnw -q -B clean package -DskipTests

# ---- Etapa 2: runtime ----
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring
COPY --from=build /app/target/*.jar app.jar
USER spring

EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=20s \
  CMD wget -q --spider http://localhost:8080/ || exit 1

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
