FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY build/libs/bff-agendador-0.0.1-SNAPSHOT.jar /app/bff-agendador.jar

EXPOSE 8083

CMD ["java", "-jar", "/app/bff-agendador.jar"]
