FROM openjdk:11
EXPOSE 8080
ADD target/Online-store-0.0.1-SNAPSHOT.jar Online-store-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/Online-store-0.0.1-SNAPSHOT.jar"]