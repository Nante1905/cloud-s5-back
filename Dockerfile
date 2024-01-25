FROM openjdk:17
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package
ENTRYPOINT [ "java", "-XX:MaxRAM=250M", "-jar", "target/voiture-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080