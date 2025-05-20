# Этап сборки
FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
WORKDIR /build

# Кэширование зависимостей
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Сборка приложения
COPY src ./src
RUN mvn clean package -DskipTests

# Этап выполнения
FROM eclipse-temurin:17-jre-alpine

# Установка утилит для здоровья БД и приложения
RUN apk add --no-cache curl postgresql-client && \
    # Создание непривилегированного пользователя
    adduser -D myuser && \
    # Создание рабочих директорий
    mkdir -p /home/myuser/app /logs/firstTry && \
    chown -R myuser:myuser /home/myuser /logs

WORKDIR /home/myuser/app

# Копирование артефактов сборки
COPY --from=build /build/target/*.jar app.jar
# Копирование скрипта проверки БД (должен существовать в проекте)
COPY wait-for-postgres.sh .
RUN chmod +x wait-for-postgres.sh

# Проверка здоровья приложения
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

EXPOSE 8080
USER myuser

# Запуск с ожиданием готовности БД
ENTRYPOINT ["./wait-for-postgres.sh", "postgres", "--", \
           "java", "-Xms512m", "-Xmx1024m", "-jar", "app.jar"]