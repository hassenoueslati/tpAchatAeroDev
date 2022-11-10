FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/tpAchatAeroDev-1.0.jar tpAchatAeroDev.jar
ENTRYPOINT ["java","-jar","/tpAchatAeroDev.jar"]