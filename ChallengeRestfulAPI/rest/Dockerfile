FROM openjdk:17
WORKDIR /app
COPY target/rest-0.0.1-SNAPSHOT.jar restapp.jar
ENTRYPOINT ["java", "-jar", "restapp.jar"]