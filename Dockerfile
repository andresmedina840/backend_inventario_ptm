# Etapa de construcción
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
# Descarga dependencias en capa separada
RUN mvn -B dependency:resolve dependency:resolve-plugins

COPY src ./src
# Cache de test dependencies
RUN mvn -B verify -DskipTests

# Empaquetado final
RUN mvn -B clean package -Pproduccion -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Usar usuario no-root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8080

ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-XX:+UseZGC", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]