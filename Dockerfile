FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/tpAchatAeroDev-1.0.6-RELEASE.jar tpAchatAeroDev-1.0.6-RELEASE.jar
ENTRYPOINT ["java","-jar","/tpAchatAeroDev-1.0.6-RELEASE.jar"]