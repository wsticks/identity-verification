FROM openjdk:17-jdk-alpine

EXPOSE 8082

ADD target/identity-verification-0.0.1-SNAPSHOT.jar identity-verification-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","identity-verification-0.0.1-SNAPSHOT.jar"]