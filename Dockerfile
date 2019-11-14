FROM maven:3-jdk-13 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:13-alpine
COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar
CMD ["java","-jar","/usr/app/app.jar"]