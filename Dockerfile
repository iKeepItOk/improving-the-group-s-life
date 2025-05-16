
FROM maven:3.8.6-jdk-23 AS build
WORKDIR /build


COPY pom.xml .
COPY src ./src


RUN mvn package


FROM eclipse-temurin:23-jdk-alpine


RUN apk add --no-cache openjdk23-jre && \
    adduser -D myuser && \
    mkdir -p /home/myuser/app && \
    chown -R myuser:myuser /home/myuser

WORKDIR /home/myuser/app


COPY --from=build /build/target/improving-the-group-s-life.jar app.jar


EXPOSE 8080


USER myuser


ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-jar", "app.jar"]