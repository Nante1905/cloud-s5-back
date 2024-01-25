FROM openjdk:17
WORKDIR /app
COPY . .
RUN nvmw clean pakcage
ENTRYPOINT [ "java", "-Xmx32m", "-Xss256k", "-jar", "target/voiture-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080