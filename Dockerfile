# Etapa 1: Construcción con Maven
FROM maven:3.9-amazoncorretto-17 AS mvn
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -e -B package -DskipTests

# Etapa 2: Ejecución con Java
FROM openjdk:17
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=mvn /app/${JAR_FILE} report-app-service.jar
CMD ["java", "-jar", "report-app-service.jar"]