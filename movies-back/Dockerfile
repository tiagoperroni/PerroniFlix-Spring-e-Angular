FROM openjdk:11

EXPOSE 8080

WORKDIR /src

COPY /target/*.jar /src/movies.jar

ENV DB_HOST=172.17.0.2

ENTRYPOINT [ "java", "-jar", "/src/movies.jar" ]