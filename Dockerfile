FROM openjdk:11

ADD target/bobo-ats.jar bobo-ats.jar
ENTRYPOINT ["java", "-jar","bobo-ats.jar"]
#EXPOSE 8080