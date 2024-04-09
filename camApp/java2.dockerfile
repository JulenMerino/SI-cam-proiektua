FROM asuprun/opencv-java:3.4.1

RUN apk update && apk add maven openjdk11
COPY ./vigilancesistem /usr/src/app
WORKDIR /usr/src/app
RUN mvn package 
CMD /bin/sh -c "cd /usr/src/app/target && java -jar vigilancesistem-1.0-SNAPSHOT.jar"