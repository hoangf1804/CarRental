FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/filter-service-0.0.1-SNAPSHOT.jar /app/filter-service.jar
ENTRYPOINT ["java", "-jar", "/app/filter-service.jar"]
