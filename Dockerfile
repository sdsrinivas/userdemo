FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} userdemo.jar
ENTRYPOINT ["java","-jar","/userdemo.jar"]