# Use OpenJDK 21 como base
FROM eclipse-temurin:21-jdk-alpine

# Instalar Maven
RUN apk add --no-cache maven

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml primero para aprovechar la caché de capas de Docker
COPY pom.xml .

# Descargar las dependencias
RUN mvn dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Construir la aplicación
RUN mvn package -DskipTests

# Puerto que expone la aplicación
EXPOSE 8000

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
