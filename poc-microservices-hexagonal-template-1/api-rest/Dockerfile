FROM openjdk:8-jdk-alpine
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ./api-rest.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/api-rest.jar"]