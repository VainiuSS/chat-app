FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/chat-app-0.0.1.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]