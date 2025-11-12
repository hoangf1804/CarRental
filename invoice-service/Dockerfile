FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/invoice-service-0.0.1-SNAPSHOT.jar /app/invoice-service.jar
ENTRYPOINT ["java", "-jar", "/app/api-gateway.jar"]
