FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/rental-service-0.0.1-SNAPSHOT.jar /app/rental-service.jar
ENTRYPOINT ["java", "-jar", "/app/rental-service.jar"]