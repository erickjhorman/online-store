FROM openjdk:11
EXPOSE 8080
ADD target/online-store.jar online-store.jar
ENTRYPOINT ["java", "-jar", "/online-store.jar"]