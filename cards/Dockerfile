# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

#Information around who maintains the image
MAINTAINER fernandofuentesfullstack.com

# Refer to Maven build -> finalName
ARG JAR_FILE=target/cards-0.0.2-SNAPSHOT.jar

WORKDIR /opt/app

# Add the application's jar to the container
COPY ${JAR_FILE} cards-0.0.2-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","cards-0.0.2-SNAPSHOT.jar"]