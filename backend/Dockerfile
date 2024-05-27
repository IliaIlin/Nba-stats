FROM amazoncorretto:21-alpine-jdk as builder

WORKDIR /app

COPY backend/build.gradle.kts .
COPY backend/settings.gradle.kts .
COPY backend/gradlew .
COPY backend/gradle ./gradle
COPY backend/src ./src

RUN ./gradlew build -x test

FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

COPY --from=builder /app/build/libs/backend-0.0.1-SNAPSHOT.jar ./backend.jar

EXPOSE 8080

CMD ["java", "-jar", "backend.jar"]