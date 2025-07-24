# Use OpenJDK 21 como base
FROM eclipse-temurin:21-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar dependencias
COPY pom.xml .
COPY src ./src

# Construir la aplicación
RUN ./mvnw package -DskipTests

# Puerto que expone la aplicación
EXPOSE 8000

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
