# Etapa 1: Construcción con Maven
FROM maven:3.9-amazoncorretto-17 AS mvn
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -e -B package -DskipTests

# Etapa 2: Ejecución con Java
FROM openjdk:17
WORKDIR /app

# Copiar los archivos necesarios al contenedor
COPY src/main/resources/firebase/config/push-notification-eda24-firebase-adminsdk-6rylf-b7eb150461.json /app/firebase-config.json
COPY src/main/resources/csv/comunaData.csv /app/comunaData.csv
COPY src/main/resources/csv/barrioData.csv /app/barrioData.csv
COPY src/main/resources/csv/tiporeporteData.csv /app/tiporeporteData.csv

ARG JAR_FILE=target/*.jar
COPY --from=mvn /app/${JAR_FILE} report-app-service.jar
CMD ["java", "-jar", "report-app-service.jar"]