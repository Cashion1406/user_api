
FROM openjdk:18-jdk-alpine
EXPOSE 8080
ADD /build/libs/testing-0.0.1-SNAPSHOT-plain.jar testing.jar
ENTRYPOINT ["java", "-jar", "testing.jar"]