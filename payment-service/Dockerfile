FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/payment-service-0.0.1-SNAPSHOT.jar /app/payment-service.jar
ENTRYPOINT ["java", "-jar", "/app/payment-service.jar"]
