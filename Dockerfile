FROM amazoncorretto:11
MAINTAINER FELG
COPY target/SpringBoot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
