FROM openjdk:17
WORKDIR /app
COPY . .
ENTRYPOINT [ "sh", "mvnw", "spring-boot:run" ]
EXPOSE 8080