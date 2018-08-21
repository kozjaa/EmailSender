FROM openjdk:8-jdk
ADD target/message.jar message.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","message.jar"]