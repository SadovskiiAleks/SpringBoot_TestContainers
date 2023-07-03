FROM openjdk:17-alpine
EXPOSE 9299
ADD target/Conditional2-0.0.1-SNAPSHOT.jar myapptesting.jar
ENTRYPOINT ["java","-jar","/myapptesting.jar"]