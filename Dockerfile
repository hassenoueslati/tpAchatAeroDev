FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/tpAchatProject-1.0.war tpAchatProject-1.0.war
ENTRYPOINT ["java","-jar","tpAchatProject.jar"]