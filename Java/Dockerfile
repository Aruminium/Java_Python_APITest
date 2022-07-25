FROM maven:3.6.3-jdk-11 AS builder

WORKDIR /Java
COPY ./Java /Java
COPY ./Java/pom.xml /Java
RUN mvn package

FROM adoptopenjdk/openjdk11:debianslim-jre
COPY --from=builder /Java/target/app.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
# CMD ["./mvnw", "package", "&&", "java",  "-jar",  "target/gs-spring-boot-docker-0.1.0.jar"]