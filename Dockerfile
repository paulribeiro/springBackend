FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/microcommerce-0.0.1-SNAPSHOT.jar target/app.jar
EXPOSE 9090
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/app.jar"]
