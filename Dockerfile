# Use OpenJDK 21 como base
FROM eclipse-temurin:21-jdk-alpine

# Instalar Maven
RUN apk add --no-cache maven

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar todo el contenido del proyecto
COPY . .

# Construir la aplicación
RUN mvn clean package -DskipTests

# Puerto que expone la aplicación
EXPOSE 8000

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
