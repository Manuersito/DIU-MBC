FROM openjdk:21

LABEL authors="ManuelBC"

WORKDIR /app

COPY target/Tutorials-0.0.1-SNAPSHOT.jar tutorialsapp2.jar

EXPOSE 8098

ENTRYPOINT ["java", "-jar", "tutorialsapp2.jar"]
