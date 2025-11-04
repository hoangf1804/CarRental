FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/rental-service-0.0.1-SNAPSHOT.jar /app/rental-service.jar
ENTRYPOINT ["java", "-jar", "/app/rental-service.jar"]