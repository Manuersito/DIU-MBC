FROM openjdk:21

WORKDIR /app

COPY target/agenda-0.0.1-SNAPSHOT.jar agenda.jar

EXPOSE 8099

ENTRYPOINT ["java", "-jar", "agenda.jar"]
