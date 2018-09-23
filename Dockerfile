FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/parser.jar target/parser.jar
ENTRYPOINT ["java","-jar","target/parser.jar"]