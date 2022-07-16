FROM openjdk:8-jdk-alpine

COPY target/ProjectStarWars.jar ProjectStarWars.jar

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "ProjectStarWars.jar"]