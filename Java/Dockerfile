FROM maven:alpine as build
COPY /Java .
WORKDIR /Java
RUN mvn package

FROM openjdk:11

COPY --build ../Java /Java
WORKDIR /Java/src/main/java/com.example.linebot/

EXPOSE 8080
CMD ["java", "LinebotApplication.java"]