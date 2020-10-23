FROM openjdk:16-jdk-alpine3.12
# "working directory" fuer die Docker-Kommandos RUN, CMD, ENTRYPOINT, COPY und ADD
WORKDIR application
ARG JAR_FILE=target/controlcenter-*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]