# Usa una imagen más ligera de OpenJDK para reducir el tamaño del contenedor
FROM openjdk:21

# Copia el JAR generado a la carpeta de trabajo
COPY target/agenda-0.0.1-SNAPSHOT.jar app.jar

# Especifica el punto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
