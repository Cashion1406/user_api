FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD /build/libs/testing-0.0.1-SNAPSHOT.jar testing.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "testing.jar"]