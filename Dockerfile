
FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
WORKDIR /build


COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine


RUN apk add --no-cache openjdk17-jre && \
    adduser -D myuser && \
    mkdir -p /home/myuser/app && \
    chown -R myuser:myuser /home/myuser

WORKDIR /home/myuser/app


COPY --from=build /build/target/improving-the-group-s-life.jar app.jar


EXPOSE 8080
USER myuser
ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-jar", "app.jar"]