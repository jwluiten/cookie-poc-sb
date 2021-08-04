FROM openjdk:15-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080 9090
ENTRYPOINT ["java","-jar","-Dreactor.netty.http.server.accessLogEnabled=true", "/app.jar"]
